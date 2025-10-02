module ctn.infaut {
    requires transitive javafx.controls;
    requires javafx.fxml;

    requires java.base;
    requires java.sql;

    requires com.zaxxer.hikari;
    // requires com.fazecast.jSerialComm;
    requires com.machinezoo.sourceafis;
    requires javafx.graphics;
    requires javafx.swing;
    requires java.desktop;

    opens ctn.infaut to javafx.fxml;
    opens ctn.infaut.controllers to javafx.base;

    exports ctn.infaut;
}
