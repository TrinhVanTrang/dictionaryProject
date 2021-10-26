package com.example.dictinary;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class DictionaryApplication extends javafx.application.Application {

    double x,y =0;
    @Override
    public void start(Stage stage) throws IOException {
        Parent root =  FXMLLoader.load(getClass().getResource("MainView.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);

        root.setOnMousePressed(event -> {
            x=event.getSceneX();
            y=event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            stage.setX(event.getSceneX()-x);
            stage.setY(event.getSceneY()-y);
        });
        stage.setScene(new Scene(root,780,550));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}