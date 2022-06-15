package userInterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class LayoutController implements Initializable {

    private String absolutePath;
    @FXML
    private AnchorPane anchorId;

    @FXML
    private TextField textField;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        Stage stage = (Stage) anchorId.getScene().getWindow();
        File file = directoryChooser.showDialog(stage);
        if (file != null) {
            System.out.println("Path : " + file.getAbsolutePath());
            textField.setText(file.getAbsolutePath());
            absolutePath = file.getAbsolutePath();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public String getAbsolutePath() {
        return "absolute" + absolutePath + "end";
    }
}
