package ru.omsu.imit.userInterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import ru.omsu.imit.duplicateFinder.Duplicate;
import ru.omsu.imit.duplicateFinder.DuplicateFinderException;

public class SecondLayoutController {
    private final ObservableList<Duplicate> duplicatesData = FXCollections.observableArrayList();
    @FXML
    public Button removeButton;
    @FXML
    public TextField pathField;
    @FXML
    private TableView<Duplicate> tableDuplicates;

    @FXML
    private TableColumn<Duplicate, String> digestColumn;
    @FXML
    private TableColumn<Duplicate, String> filePathColumn;

    private Duplicate selectedRow;

    @FXML
    private void initialize() {
        initData();
        digestColumn.setCellValueFactory(new PropertyValueFactory<Duplicate, String>("digest"));
        filePathColumn.setCellValueFactory(new PropertyValueFactory<Duplicate, String>("filePath"));
        tableDuplicates.setItems(duplicatesData);
        System.out.println("Duplicates " + tableDuplicates.getItems());
    }

    @FXML
    public void onMouseClickedRow(MouseEvent mouseEvent) {
        this.selectedRow = tableDuplicates.getSelectionModel().getSelectedItem();
        pathField.setText(selectedRow.getFilePath());
    }

    @FXML
    public void onMouseClickedButtonRemoveFile(MouseEvent mouseEvent) throws DuplicateFinderException {
        ClientInteraction clientInteraction = new ClientInteraction();
        clientInteraction.setDeletableDuplicate(selectedRow);
        duplicatesData.clear();
        initData();
    }
    public void onMouseClickedButtonShowDeletedFiles(MouseEvent mouseEvent) throws Exception {
        changeWindow();
    }

    private void initData() {
        ClientInteraction clientInteraction = new ClientInteraction();
        for (Duplicate duplicate : clientInteraction.getResultFiles()) {
            duplicatesData.add(new Duplicate(duplicate.getDigest(), duplicate.getFilePath()));
        }
    }
    public void changeWindow() throws Exception {
        ThirdPage thirdPage = new ThirdPage();
        thirdPage.showWindowToThirdPage();
    }

}
