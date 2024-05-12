package ru.omsu.imit.userInterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import ru.omsu.imit.duplicateFinder.*;

import java.io.File;
import java.io.IOException;

public class ThirdLayoutController {
    private final ObservableList<Duplicate> duplicatesData = FXCollections.observableArrayList();

    @FXML
    private TableView<Duplicate> tableDeletedDuplicates;

    @FXML
    private TableColumn<Duplicate, String> typeFileColumn;
    @FXML
    private TableColumn<Duplicate, String> digestColumn;
    @FXML
    private TableColumn<Duplicate, String> filePathColumn;
    @FXML
    private TableColumn<Duplicate, String> descriptionColumn;

    @FXML
    private AnchorPane anchorId3;

    @FXML
    public TextField pathField;

    private Duplicate selectedRow;

    @FXML
    private void initialize(){
        initData();
        digestColumn.setCellValueFactory(new PropertyValueFactory<Duplicate, String>("digest"));
        filePathColumn.setCellValueFactory(new PropertyValueFactory<Duplicate, String>("filePath"));
        typeFileColumn.setCellValueFactory(new PropertyValueFactory<Duplicate, String>("typeFile"));
        tableDeletedDuplicates.setItems(duplicatesData);
        System.out.println("Duplicates " + tableDeletedDuplicates.getItems());
    }

    private void initData(){
        ClientInteraction clientInteraction = new ClientInteraction();
        for (Duplicate duplicate : clientInteraction.getResultSortedFiles()) {
            duplicatesData.add(new Duplicate(duplicate.getDigest(),duplicate.getFilePath(), duplicate.getTypeFile()));
        }
    }

    @FXML
    public void onMouseClickedRow(MouseEvent mouseEvent) {
        this.selectedRow = tableDeletedDuplicates.getSelectionModel().getSelectedItem();
        pathField.setText(selectedRow.getFilePath());
    }

    @FXML
    public void onMouseClickedDescriptionCell(MouseEvent mouseEvent) {
        
    }

    @FXML
    public void onMouseClickedButtonMoveFile(MouseEvent mouseEvent) throws DuplicateFinderException, Exception {
        if("not deleted".equals(selectedRow.getTypeFile())) {
            ClientInteraction clientInteraction = new ClientInteraction();

            String absPath = clientInteraction.setMovableFile(selectedRow, chooseDir());
            DataBase db = DataBase.getDB();
            db.setPathInRow(selectedRow.getDigest(), selectedRow.getFilePath(), absPath);
            anchorId3.getScene().getWindow().hide();
            ThirdPage thirdPage = new ThirdPage();
            thirdPage.showWindowToThirdPage();
        }
    }



    public void onMouseClickedButtonToTableOfDuplicates(MouseEvent mouseEvent) throws Exception {
        changeWindow();
    }

    public void changeWindow() throws Exception {
        tableDeletedDuplicates.getScene().getWindow().hide();
        SecondPage secondPage = new SecondPage();
       secondPage.showWindowToSecondPage();
    }

    public String chooseDir() {
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        Stage stage = (Stage) anchorId3.getScene().getWindow();
        File file = directoryChooser.showDialog(stage);
        /*if (file != null) {

            localAbsolutePath = file.getAbsolutePath();
            textField.setText(localAbsolutePath);
        }
        if (localAbsolutePath != null) {
            ClientInteraction clientInteraction = new ClientInteraction();
            clientInteraction.setAbsolutePath(localAbsolutePath);

            changeWindow();

        }*/
        return file.getAbsolutePath();
    }
}
