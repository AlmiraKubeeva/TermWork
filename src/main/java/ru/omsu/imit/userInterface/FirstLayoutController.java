package ru.omsu.imit.userInterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import ru.omsu.imit.duplicateFinder.DuplicateFinderException;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class FirstLayoutController implements Initializable {
    @FXML
    private AnchorPane anchorId;
    @FXML
    private TextField textField;
    @FXML
    private Button browseButton;

    @FXML
    private void handleButtonAction(ActionEvent event) throws DuplicateFinderException, Exception {
        String localAbsolutePath = null;
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        Stage stage = (Stage) anchorId.getScene().getWindow();
        File file = directoryChooser.showDialog(stage);
        if (file != null) {
            localAbsolutePath = file.getAbsolutePath();
            textField.setText(localAbsolutePath);
        }
        if (localAbsolutePath != null) {
            ClientInteraction clientInteraction = new ClientInteraction();
            clientInteraction.setAbsolutePath(localAbsolutePath);
            clientInteraction.addDirPath(localAbsolutePath);
            changeWindow();
        }
    }

    @FXML
    private void handleButtonAction2(MouseEvent event) throws DuplicateFinderException, Exception {
        String localAbsolutePath = null;
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        Stage stage = (Stage) anchorId.getScene().getWindow();
        File file = directoryChooser.showDialog(stage);
        if (file != null) {
            localAbsolutePath = file.getAbsolutePath();
            textField.setText(localAbsolutePath);
        }
        if (localAbsolutePath != null) {
            ClientInteraction clientInteraction = new ClientInteraction();
            clientInteraction.setAbsolutePath(localAbsolutePath);
            clientInteraction.addDirPath(localAbsolutePath);
            changeWindow();
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void changeWindow() throws Exception {
        anchorId.getScene().getWindow().hide();
        //ProgressBarPage progressBarPage = new ProgressBarPage();

        SecondPage secondPage = new SecondPage();
        secondPage.showWindowToSecondPage();
    }
}
