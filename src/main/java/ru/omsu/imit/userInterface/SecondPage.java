package ru.omsu.imit.userInterface;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.InputStream;

public class SecondPage extends Application {
    Stage stage = new Stage();

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(SecondPage.class.getResource("/userInterface/myLayout2.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        //System.out.println(getClass().getResourceAsStream("/images/icon.png"));
        InputStream iconStream =
                getClass().getResourceAsStream("/images/icon.png");
        Image image = new Image(iconStream);
        stage.getIcons().add(image);
        stage.setTitle("Find duplicates of your folder!");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void showWindow() throws Exception {
        start(stage);
    }

}
