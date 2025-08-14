package ctn.infaut.services;

import javafx.scene.control.Alert;

public class AlertFactory {
  public static Alert generateAlert(Alert.AlertType at, String content) {
    Alert a = new Alert(at);
    a.setHeaderText("El sistema comunica:");
    a.setContentText(content);

    return a;
  }
}
