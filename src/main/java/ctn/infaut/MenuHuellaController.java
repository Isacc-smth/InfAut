/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ctn.infaut;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
	private ImageView previewImagen;
	@FXML
	private TextField Apellido;
	@FXML
	private TextField Nombre;
	@FXML
	private TextField idAlumno;

	/**
	 * Initializes the
	 * controller
	 * class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}	

	@FXML
	private void seleccionarImagen(ActionEvent event) {
		FileChooser seleccionador = new FileChooser();
		seleccionador.setTitle("Elige una imagen");
		
		seleccionador.getExtensionFilters().addAll(
			new FileChooser.ExtensionFilter("Image Files", "*.png", "*.bmp", ".jpg")
		);
		
		File file = seleccionador.showOpenDialog(new Stage());
		
		if (file != null) {
			Image imagen = new Image(file.toURI().toString());
			
		}
	}

	@FXML
	private void obtenerDeLector(ActionEvent event) {
		Alert noDisponible = AlertFactory.generateAlert(
			Alert.AlertType.ERROR,
			"Lectores no disponibles",
			"Aun no se han implementado"
		);
	}

	@FXML
	private void buscarAlumno(ActionEvent event) {
		// TODO:
	}
}
