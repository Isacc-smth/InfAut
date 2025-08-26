module ctn.infaut {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens ctn.infaut.fxml to javafx.fxml;
    opens ctn.infaut.controllers to javafx.base;

    exports ctn.infaut;
}
