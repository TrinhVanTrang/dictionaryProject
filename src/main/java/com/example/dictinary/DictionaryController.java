package com.example.dictinary;
import jcodes.CommonFunction;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DictionaryController implements Initializable {
    @FXML
    private ImageView exit=new ImageView();

    @FXML
    private StackPane contentArea;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exit.setOnMouseClicked(event -> {
            CommonFunction.playAudio("src/main/resources/com/example/dictinary/audio/back.mp3");
            System.exit(0);
        });

        try {
            Parent fxml= FXMLLoader.load(getClass().getResource("Search.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(DictionaryController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    public void Search(javafx.event.ActionEvent actionEvent) throws IOException{
        CommonFunction.playAudio("src/main/resources/com/example/dictinary/audio/clickButton.mp3");
        Parent fxml=FXMLLoader.load(getClass().getResource("Search.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    public void Dictionary(javafx.event.ActionEvent actionEvent) throws IOException{
        CommonFunction.playAudio("src/main/resources/com/example/dictinary/audio/clickButton.mp3");
        Parent fxml=FXMLLoader.load(getClass().getResource("Dictionary.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    public void MyWord(javafx.event.ActionEvent actionEvent) throws IOException{
        CommonFunction.playAudio("src/main/resources/com/example/dictinary/audio/clickButton.mp3");
        Parent fxml=FXMLLoader.load(getClass().getResource("MyWord.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    public void History(javafx.event.ActionEvent actionEvent) throws IOException{
        CommonFunction.playAudio("src/main/resources/com/example/dictinary/audio/clickButton.mp3");
        Parent fxml=FXMLLoader.load(getClass().getResource("History.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    public void TranslateAPI(javafx.event.ActionEvent actionEvent) throws IOException{
        CommonFunction.playAudio("src/main/resources/com/example/dictinary/audio/clickButton.mp3");
        Parent fxml=FXMLLoader.load(getClass().getResource("TranslateAPI.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    public void Quiz(javafx.event.ActionEvent actionEvent) throws IOException{
        CommonFunction.playAudio("src/main/resources/com/example/dictinary/audio/clickButton.mp3");
        Parent fxml=FXMLLoader.load(getClass().getResource("Quiz.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }
    public void Setting(javafx.event.ActionEvent actionEvent) throws IOException{
        CommonFunction.playAudio("src/main/resources/com/example/dictinary/audio/clickButton.mp3");
        Parent fxml=FXMLLoader.load(getClass().getResource("Setting.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

}