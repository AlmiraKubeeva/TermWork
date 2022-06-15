package userInterface;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("myLayout.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
    /*    LayoutController layoutController = new LayoutController();
        layoutController.getAbsolutePath();*/
        System.out.println(getClass().getResourceAsStream("/images/icon.png"));
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
}