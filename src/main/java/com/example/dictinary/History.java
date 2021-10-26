package com.example.dictinary;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import jcodes.CommonFunction;
import jfxcode.ConnectDatabase;
import jfxcode.Vocabulary;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

public class History implements Initializable {
    @FXML
    private ImageView voice;
    @FXML
    private ImageView back;
    @FXML
    private Button search;
    @FXML
    private TextField input;
    @FXML
    private WebView output;
    @FXML
    private AnchorPane showDetail;
    @FXML
    private ListView<String> listSearch;
    @FXML
    private ListView<String> listWord;
    private Vector<Vocabulary> list=new Vector<Vocabulary>();
    @FXML
    private AnchorPane searchPane;
    @FXML
    private ImageView isMyWord;
    boolean flag;
    Image image1 = new Image(getClass().getResourceAsStream("image/trueMyWord.png"));
    Image image2 = new Image(getClass().getResourceAsStream("image/falseMyWord.png"));
    private Vector<Vocabulary> result = new Vector<Vocabulary>();
    private String text;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list= ConnectDatabase.querySelectAllHistory();
        String[] s = new String[list.size()];
        for (int i = list.size()-1;i>=0; i--) {
            s[list.size()-1-i] = list.get(i).getWord();
        }
        listWord.getItems().clear();
        listWord.getItems().addAll(s);
        listWord.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listWord.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                input.setText(listWord.getSelectionModel().getSelectedItem());
            }
        });

        back.setOnMouseClicked(event -> {
            showDetail.setVisible(false);
            list= ConnectDatabase.querySelectAllHistory();
            String[] temp = new String[list.size()];
            for (int i = list.size()-1;i>=0; i--) {
                temp[list.size()-1-i] = list.get(i).getWord();
            }
            listWord.getItems().clear();
            listWord.getItems().addAll(temp);
        });

        showDetail.setVisible(false);
        listSearch.setVisible(false);

        voice.setOnMouseClicked(event -> {
            if (input.getText().equals("")) {
                return;
            } else {
                CommonFunction.textToSpeech(input.getText());
            }
        });

        input.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                text = newValue;
                if (text == null) {
                    text = "";
                }
                result = ConnectDatabase.querySelect(text, ConnectDatabase.LINKDB, "av");
                if (result.size() > 0) {
                    listSearch.setVisible(true);
                    String[] strings = new String[result.size()];
                    for (int i = 0; i < result.size(); i++) {
                        strings[i] = result.get(i).getWord();
                    }
                    listSearch.getItems().clear();
                    listSearch.getItems().addAll(strings);
                    listSearch.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
                }
            }
        });

        listSearch.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                input.setText(listSearch.getSelectionModel().getSelectedItem());
            }
        });

        input.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    showWord();
//                    Vocabulary vocabulary=result.get(0);
//                    if(ConnectDatabase.IsConcern(vocabulary,ConnectDatabase.LINKDB,"history")) {
//                        ConnectDatabase.queryDelete(vocabulary,ConnectDatabase.LINKDB,"history");
//                        ConnectDatabase.queryInsert(vocabulary,ConnectDatabase.LINKDB,"history");
//                    }  else {
//                        ConnectDatabase.queryInsert(vocabulary,ConnectDatabase.LINKDB,"history");
//                    }
                }
            }
        });

        input.setOnMouseClicked(event -> {
            if (result.size() > 0) {
                listSearch.setVisible(true);
            }
        });

        search.setOnMouseClicked(event -> {
            showWord();
        });

        isMyWord.setOnMouseClicked(event -> {
            Vocabulary vocabulary = result.get(0);
            flag = flag ^ true;
            if (flag) {
                if (ConnectDatabase.IsConcern(vocabulary, ConnectDatabase.LINKDB, "myword")) {
                    ConnectDatabase.queryDelete(vocabulary, ConnectDatabase.LINKDB, "myword");
                    ConnectDatabase.queryInsert(vocabulary, ConnectDatabase.LINKDB, "myword");
                } else {
                    ConnectDatabase.queryInsert(vocabulary, ConnectDatabase.LINKDB, "myword");
                }
            } else {
                ConnectDatabase.queryDelete(vocabulary, ConnectDatabase.LINKDB, "myword");
            }
            if (flag) {
                isMyWord.setImage(image1);
            } else {
                isMyWord.setImage(image2);
            }
        });
    }

    public void showWord() {
        Vocabulary vocabulary = result.get(0);
        if (ConnectDatabase.IsConcern(vocabulary, ConnectDatabase.LINKDB, "history")) {
            ConnectDatabase.queryDelete(vocabulary, ConnectDatabase.LINKDB, "history");
            ConnectDatabase.queryInsert(vocabulary, ConnectDatabase.LINKDB, "history");
        } else {
            ConnectDatabase.queryInsert(vocabulary, ConnectDatabase.LINKDB, "history");
        }
        if (result.size() > 0) {
            WebEngine webEngine = output.getEngine();
            webEngine.setUserStyleSheetLocation(getClass().getClassLoader().getResource("styleWord.css").toString());
            webEngine.loadContent("<html>" + result.get(0).getHtml() + "</html>");
        }
        flag = ConnectDatabase.IsConcern(result.get(0), ConnectDatabase.LINKDB, "myword");
        //System.out.println(flag);
        if (flag) {
            isMyWord.setImage(image1);
        } else {
            isMyWord.setImage(image2);
        }
        showDetail.setVisible(true);
        listSearch.setVisible(false);
    }
}
