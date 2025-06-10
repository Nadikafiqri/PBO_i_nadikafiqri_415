package main.modul6;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainProgram extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        Image UmmIcon = new Image(getClass().getResourceAsStream("/UmmIcon.png"));
        primaryStage.getIcons().add(UmmIcon);
        SceneControl sceneControl = new SceneControl(primaryStage);
        sceneControl.getLoginScene();
    }

}