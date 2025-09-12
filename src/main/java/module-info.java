module ctn.infaut {
    requires transitive javafx.controls;
    requires javafx.fxml;

    requires java.base;
    requires java.sql;

    requires com.zaxxer.hikari;
    requires com.machinezoo.sourceafis;

    opens ctn.infaut to javafx.fxml;
    opens ctn.infaut.controllers to javafx.base;

    exports ctn.infaut;
}
