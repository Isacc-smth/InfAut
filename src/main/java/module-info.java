module ctn.infaut {
    requires javafx.controls;
    requires javafx.fxml;

    opens ctn.infaut to javafx.fxml;
    exports ctn.infaut;
}
