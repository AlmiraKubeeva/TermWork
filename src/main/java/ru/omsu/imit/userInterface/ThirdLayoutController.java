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
import ru.omsu.imit.duplicateFinder.DuplicateFinder;
import ru.omsu.imit.duplicateFinder.DuplicateFinderException;
import ru.omsu.imit.duplicateFinder.SortedDuplicate;

import java.io.File;
import java.io.IOException;

public class ThirdLayoutController {
    private final ObservableList<SortedDuplicate> duplicatesData = FXCollections.observableArrayList();

    @FXML
    private TableView<SortedDuplicate> tableDeletedDuplicates;

    @FXML
    private TableColumn<SortedDuplicate, String> typeFileColumn;
    @FXML
    private TableColumn<SortedDuplicate, String> digestColumn;
    @FXML
    private TableColumn<SortedDuplicate, String> filePathColumn;

    @FXML
    private AnchorPane anchorId3;

    @FXML
    public TextField pathField;

    private SortedDuplicate selectedRow;

    @FXML
    private void initialize(){
        initData();
        typeFileColumn.setCellValueFactory(new PropertyValueFactory<SortedDuplicate, String>("typeFile"));
        digestColumn.setCellValueFactory(new PropertyValueFactory<SortedDuplicate, String>("digest"));
        filePathColumn.setCellValueFactory(new PropertyValueFactory<SortedDuplicate, String>("filePath"));
        tableDeletedDuplicates.setItems(duplicatesData);
        System.out.println("Duplicates " + tableDeletedDuplicates.getItems());
    }

    private void initData(){
        ClientInteraction clientInteraction = new ClientInteraction();
        for (SortedDuplicate duplicate : clientInteraction.getResultSortedFiles()) {
            duplicatesData.add(new SortedDuplicate(duplicate.getTypeFile(),duplicate.getDigest(),duplicate.getFilePath()));
        }
    }

    @FXML
    public void onMouseClickedRow(MouseEvent mouseEvent) {
        this.selectedRow = tableDeletedDuplicates.getSelectionModel().getSelectedItem();
        pathField.setText(selectedRow.getFilePath());
    }

    @FXML
    public void onMouseClickedButtonMoveFile(MouseEvent mouseEvent) throws DuplicateFinderException, IOException {
        if(!selectedRow.getTypeFile().equals("deleted")) {
            ClientInteraction clientInteraction = new ClientInteraction();
            clientInteraction.setMovableFile(selectedRow, chooseDir());
            //duplicatesData.clear();
            initData();
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
