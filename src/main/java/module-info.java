module userInterface {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.commons.codec;
    requires org.apache.commons.collections4;


    opens ru.omsu.imit.userInterface to javafx.fxml;
    exports ru.omsu.imit.userInterface;
    exports ru.omsu.imit.duplicateFinder;
    opens ru.omsu.imit.duplicateFinder to javafx.fxml;
}