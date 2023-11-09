package ru.omsu.imit.userInterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import ru.omsu.imit.duplicateFinder.Duplicate;
import ru.omsu.imit.duplicateFinder.SortedDuplicate;

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

    public void onMouseClickedButtonToTableOfDuplicates(MouseEvent mouseEvent) throws Exception {
        changeWindow();
    }

    public void changeWindow() throws Exception {
        SecondPage secondPage = new SecondPage();
       secondPage.showWindowToSecondPage();
    }
}
