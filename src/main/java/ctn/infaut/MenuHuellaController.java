/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ctn.infaut;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.embed.swing.SwingFXUtils;

import javax.imageio.ImageIO;

import ctn.infaut.DAO.HuellaDAO;
import ctn.infaut.controllers.AlumnoSingleton;
import ctn.infaut.controllers.Huella;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller
 * class
 *
 * @author
 * isaacfeltes
 */
public class MenuHuellaController implements Initializable {

	@FXML
	private Button subirImagen;
	@FXML
	private Button buscadorAlumno;
	@FXML
	private Button registrarHuella;
	@FXML
	private TextField Apellido;
	@FXML
	private TextField Nombre;
	@FXML
	private TextField idAlumno;

    @FXML
    private ImageView previewHuella;

    private File seleccionado;

	/**
	 * Initializes
	 * the
	 * controller
	 * class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}	

	@FXML
	private void seleccionarImagen(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Imágenes BMP", "*.BMP"),
            new FileChooser.ExtensionFilter("Todos los archivos", "*.*")
        );

        // Abrir diálogo
        this.seleccionado = fileChooser.showOpenDialog(subirImagen.getScene().getWindow());
        if (seleccionado != null)  {
            BufferedImage bufferedImage = ImageIO.read(seleccionado);
            Image imagen = SwingFXUtils.toFXImage(bufferedImage, null);
            previewHuella.setImage(imagen);
        }
	}

	@FXML
	private void obtenerDeLector(ActionEvent event) {
		throw new UnsupportedOperationException("Aun no se implementaron lectores");
	}

    @FXML
    private void abrirPruebaEmparejamiento(ActionEvent event) {
        abrirFXML("ProbarHuella.fxml", "Probar Emparejamiento de huellas");
    }

	@FXML
	private void buscarAlumno(ActionEvent event) {
        abrirFXML("BuscarAlumno.fxml", "Buscar Alumno");

        idAlumno.setText(String.valueOf(AlumnoSingleton.getInstance().getIdAlumno()));
        Nombre.setText(AlumnoSingleton.getInstance().getNombre());
        Apellido.setText(AlumnoSingleton.getInstance().getApellido());
	}

    @FXML
    private void guardarHuella(ActionEvent event) {
        HuellaDAO HuellaSQL = new HuellaDAO();
        Huella h = new Huella(
            Integer.parseInt(idAlumno.getText()),
            seleccionado.getAbsolutePath()
        );

        if (HuellaSQL.insertar(h)) {
            Alert seInsertoLaHuella = AlertFactory.generateAlert(
                AlertType.INFORMATION, "Se registro la huella con exito"
            );
            seInsertoLaHuella.show();
        } else {
            Alert noSeInsertoLaHuella = AlertFactory.generateAlert(
                AlertType.INFORMATION, "Se registro la huella con exito"
            );
            noSeInsertoLaHuella.show();
        }
    }

    private void abrirFXML(String fxml, String title) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        try {
            Parent root = loader.load();
            Stage stage = new Stage();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(title);
            stage.setResizable(false);

            stage.show();

        } catch (IOException e) {
            System.err.println("Ocurrio un error: ");
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
