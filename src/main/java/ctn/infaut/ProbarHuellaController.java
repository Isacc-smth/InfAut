/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ctn.infaut;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.machinezoo.sourceafis.FingerprintTemplate;

import ctn.infaut.DAO.AlumnoDAO;
import ctn.infaut.controllers.Alumno;
import ctn.infaut.controllers.Huella;
import ctn.infaut.huella.Emparejamiento;
import ctn.infaut.huella.HuellaUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.awt.image.BufferedImage;
/**
 * FXML Controller
 * class
 *
 * @author
 * isaacfeltes
 */
public class ProbarHuellaController implements Initializable {
	@FXML
	private Button pruebaImagen;
	@FXML
	private Button pruebaLector;
    @FXML
    private Text txtAutenticacion;

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
	private void buscarImagen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Imágenes BMP", "*.BMP"),
            new FileChooser.ExtensionFilter("Todos los archivos", "*.*")
        );

        // Abrir diálogo
        File seleccionado = fileChooser.showOpenDialog(pruebaImagen.getScene().getWindow());

        FingerprintTemplate sujeto = HuellaUtils.obtenerTemplateDesdePath(seleccionado.getAbsolutePath());
        if (sujeto == null) {
            System.err.println("No se pudo generar el template de la huella seleccionada");
            return;
        }
        Huella coincidencia = Emparejamiento.emparejar(sujeto);
        if (coincidencia != null) {
            Alumno al = AlumnoDAO.obtenerPorID(coincidencia.getIdAlumno());
            System.out.println("Mejor coincidencia: " + coincidencia.getIdAlumno() + ", " + al.getNombre() + al.getApellido());
        } else {
            System.err.println("No se encontraron coincidencias");
            txtAutenticacion.setText("No se encontraron coincidencias");
        }
	}
	
	@FXML
	private void probarLector(ActionEvent event) {
		throw new UnsupportedOperationException("Aun no se implementaron los lectores");
	}  			
}
