package ru.omsu.imit.userInterface;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.InputStream;

public class ThirdPage extends Application {
    Stage stage = new Stage();

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(ThirdPage.class.getResource("/userInterface/myLayout3.fxml"));
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

    public static void main(String[] args) {
        launch();
    }

    public void showWindowToThirdPage() throws Exception {
        start(stage);
    }

}
