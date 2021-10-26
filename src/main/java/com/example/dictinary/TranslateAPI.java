package com.example.dictinary;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import jcodes.CommonFunction;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

public class TranslateAPI implements Initializable {

    @FXML
    private TextArea input;
    @FXML
    private TextArea output;
    @FXML
    private ImageView inputSpeak;
    @FXML
    private ImageView outputSpeak;

    private String result, langFrom, langTo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inputSpeak.setVisible(false);
        outputSpeak.setVisible(false);
        output.setEditable(false);
        input.setOnMouseClicked(event -> {
            //input.setText("");
        });
        inputSpeak.setOnMouseClicked(event -> {
            CommonFunction.textToSpeech(input.getText());
        });
        outputSpeak.setOnMouseClicked(event -> {
           CommonFunction.textToSpeech(output.getText());
        });
        //output.setText(result);
    }

    public void EngToVi(javafx.event.ActionEvent actionEvent) {
        CommonFunction.playAudio();
        langFrom="en";
        langTo="vi";
        inputSpeak.setVisible(true);
        outputSpeak.setVisible(false);
        try {
            result=CommonFunction.translateText(input.getText(),langFrom,langTo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        output.setText(result);
    }

    public void ViToEng(javafx.event.ActionEvent actionEvent) {
        CommonFunction.playAudio();
        langFrom = "vi";
        langTo = "en";
        inputSpeak.setVisible(false);
        outputSpeak.setVisible(true);
        try {
            result= CommonFunction.translateText(input.getText(),langFrom,langTo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        output.setText(result);
    }
}
