package ctn.infaut;

import javafx.scene.control.Alert;

public class AlertFactory {
    public static Alert generateAlert(Alert.AlertType at, String header, String content) {
        Alert a = new Alert(at);
        a.setHeaderText(header); 
        a.setContentText(content);

        return a;
    }

    public static Alert generateAlert(Alert.AlertType at, String content) {
        return generateAlert(at, "El sistema comunica:", content); // Mensaje por defecto
    }
}
