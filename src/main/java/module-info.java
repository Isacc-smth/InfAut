module ctn.infaut {
  requires javafx.controls;
  requires javafx.fxml;
  requires java.base;
  requires java.sql;

  opens ctn.infaut to javafx.fxml;

  exports ctn.infaut;
}
