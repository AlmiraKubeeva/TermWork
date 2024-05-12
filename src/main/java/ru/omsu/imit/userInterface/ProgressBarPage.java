package ru.omsu.imit.userInterface;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.InputStream;

import static javafx.application.Application.launch;

public class ProgressBarPage {

    Stage stage = new Stage();
/*
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(SecondPage.class.getResource("/userInterface/ProgressBar.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        InputStream iconStream =
                getClass().getResourceAsStream("/images/icon.png");
        Image image = new Image(iconStream);
        stage.getIcons().add(image);
        stage.setTitle("Find duplicates of your folder!");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void showWindowToProgressBarPage() throws Exception {
        start(stage);
    }*/

    public static void main(String[] args) {
        launch();
    }
}
