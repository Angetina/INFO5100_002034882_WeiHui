package com.example.finalproject_imagemanager;

import javafx.application.Application;//The starting point of JavaFX programming
import javafx.fxml.FXMLLoader;//Read .fxml UI files and create corresponding JavaFX components
import javafx.scene.Scene;//Represents a "scene", which is a container for the entire screen UI.
import javafx.stage.Stage;//Represents the program's window (the outermost screen frame).

import java.io.IOException;//Handling IOExceptions that may be thrown by FXMLLoader.load()

public class MainApplication extends Application {
    @Override
    //Defines what to display when the program starts.
    public void start(Stage stage) throws IOException {
        //Set the appearance of the UI screen and initial window
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/com/example/finalproject_imagemanager/main-view.fxml"));


        //Set the FXML screen as a Scene object with a screen size of 800x800 pixels.
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);

        //Sets the title of the window (displayed at the top of the window).
        stage.setTitle("Image management tool");

        //Place this Scene on the Stage.
        stage.setScene(scene);

        //Display Window
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }//Call the launch() method to start the JavaFX system and indirectly call the start() method.
}