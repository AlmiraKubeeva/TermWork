module userInterface {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.commons.codec;
    requires org.apache.commons.collections4;


    opens userInterface to javafx.fxml;
    exports userInterface;
}