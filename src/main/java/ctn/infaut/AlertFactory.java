package ctn.infaut;

import javafx.scene.control.Alert;

public class AlertFactory {
    public static Alert generateAlert(Alert.AlertType at, String content) {
        Alert a = new Alert(at);
        a.setHeaderText("El sistema comunica:"); // Mensaje por defecto
        a.setContentText(content);

        return a;
    }

    public static Alert generateAlert(Alert.AlertType at, String header, String content) {
        Alert a = new Alert(at);
        a.setHeaderText(header);
        a.setContentText(content);

        return a;
    }
}
