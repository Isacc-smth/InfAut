/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ctn.infaut;

import ctn.infaut.controllers.Materia;
import ctn.infaut.controllers.MateriaSingleton;

import ctn.infaut.DAO.MateriaDAO;

import java.net.URL;

import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller
 * class
 *
 * @author
 * isaacfeltes
 */
public class BuscarMateriaController implements Initializable {

	@FXML
	private TableView<Materia> tablaDeBusqueda;
	@FXML
	private TableColumn<Materia, Integer> ColumnaId;
    @FXML
	private TableColumn<Materia, String> ColumnaMateria;

    private ObservableList<Materia> cursos;
	/**
     * Inicia el controlador, buscando de la tabla de cursos y
     * agregando las entradas a la tabla
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
            MateriaDAO MateriaSQL = new MateriaDAO();
            cursos = FXCollections.observableArrayList(MateriaSQL.consulta());

            ColumnaId.setCellValueFactory(new PropertyValueFactory<>("idMateria"));
            ColumnaMateria.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

            tablaDeBusqueda.setItems(cursos);
	}	

    /**
     * Obtiene el curso seleccionado y lo asigna a
     * {@link ctn.infaut.Controllers.MateriaSingleton}
     *
     * @param event representa informacion util de acciones realizadas con el raton como verificar si fue doble click
     * */
	@FXML
	private void completarEnSingleton(MouseEvent event) {
        // Forzar doble click para evitar completados accidentales
        if (event.getClickCount() > 1) {
            Materia seleccion = tablaDeBusqueda.getSelectionModel().getSelectedItem();
            MateriaSingleton.getInstance().setIdMateria(seleccion.getIdMateria());
            MateriaSingleton.getInstance().setNombre(seleccion.getNombre());

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource())
                .getScene().getWindow();
            stage.close();
        }
	}
}
