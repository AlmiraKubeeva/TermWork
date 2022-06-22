package ru.omsu.imit.userInterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SecondLayoutController {
    private final ObservableList<Duplicate> duplicatesData = FXCollections.observableArrayList();

    @FXML
    private TableView<Duplicate> tableDuplicates;

    @FXML
    private TableColumn<Duplicate, Integer> hashColumn;
    @FXML
    private TableColumn<Duplicate, String> filePathColumn;

    // инициализируем форму данными
    @FXML
    private void initialize() {
        initData();
        // устанавливаем тип и значение которое должно хранится в колонке
        hashColumn.setCellValueFactory(new PropertyValueFactory<Duplicate, Integer>("hash"));
        filePathColumn.setCellValueFactory(new PropertyValueFactory<Duplicate, String>("filePath"));
        // заполняем таблицу данными
        tableDuplicates.setItems(duplicatesData);
    }

    // подготавливаем данные для таблицы
    // вы можете получать их с базы данных
    private void initData() {
        ClientInteraction clientInteraction = new ClientInteraction();
        
        duplicatesData.add(new Duplicate(4536, "путь 1"));
        duplicatesData.add(new Duplicate(3555, "путь 2"));
        duplicatesData.add(new Duplicate(9607, "путь 3"));
        duplicatesData.add(new Duplicate(697, "путь 4"));
        duplicatesData.add(new Duplicate(243, "путь 5"));
        duplicatesData.add(new Duplicate(223, "путь 6"));
        duplicatesData.add(new Duplicate(2573, "путь 7"));
        duplicatesData.add(new Duplicate(23, "путь 8"));
        duplicatesData.add(new Duplicate(2389, "путь 9"));
        duplicatesData.add(new Duplicate(2083, "путь 10"));
        duplicatesData.add(new Duplicate(213, "путь 11"));
        duplicatesData.add(new Duplicate(4623, "путь 12"));
    }

}
