package ctn.infaut;

import ctn.infaut.connection.Conexion;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConfigDBController {

    @FXML
    private TextField hostField;
    @FXML
    private TextField puertoField;
    @FXML
    private TextField nombreBDField;
    @FXML
    private TextField usuarioField;
    @FXML
    private PasswordField contrasenaField;

    @FXML
    private void initialize() {
        // Opcional: cargar valores previos si existen.
    }

    @FXML
    private void guardarConfiguracion() {
        String host = hostField.getText().trim();
        String puerto = puertoField.getText().trim();
        String nombreBD = nombreBDField.getText().trim();
        String usuario = usuarioField.getText().trim();
        String contrasena = contrasenaField.getText();

        if (host.isEmpty() || puerto.isEmpty() || nombreBD.isEmpty() || usuario.isEmpty()) {
            mostrarAlerta("Todos los campos son obligatorios (excepto contraseña).");
            return;
        }

        String url = String.format("jdbc:mysql://%s:%s/%s", host, puerto, nombreBD);

        Conexion.guardarConfig(url, usuario, contrasena);

        mostrarAlerta("¡Configuración guardada exitosamente!");

        // Cerrar la ventana de configuración
        Stage stage = (Stage) hostField.getScene().getWindow();
        stage.close();
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Configuración");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
