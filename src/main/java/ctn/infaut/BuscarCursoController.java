/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ctn.infaut;

import ctn.infaut.controllers.Curso;
import ctn.infaut.controllers.CursoSingleton;

import ctn.infaut.DAO.CursoDAO;

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
public class BuscarCursoController implements Initializable {

	@FXML
	private TableView<Curso> tablaDeBusqueda;
	@FXML
	private TableColumn<Curso, Integer> ColumnaId;
    @FXML
	private TableColumn<Curso, String> ColumnaCurso;

    private ObservableList<Curso> cursos;
	/**
     * Inicia el controlador, buscando de la tabla de cursos y
     * agregando las entradas a la tabla
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
        try {
            CursoDAO CursoSQL = new CursoDAO();
            cursos = FXCollections.observableArrayList(CursoSQL.consulta());

            ColumnaId.setCellValueFactory(new PropertyValueFactory<>("idCurso"));
            ColumnaCurso.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

            tablaDeBusqueda.setItems(cursos);

        } catch (SQLException e) {
            Alert consultaInicialFallo = AlertFactory.generateAlert(AlertType.ERROR, 
                    "Hubo un error al obtener los cursos: " + 
                    e.getMessage()
                );
            consultaInicialFallo.show();
        }
	}	

    /**
     * Obtiene el curso seleccionado y lo asigna a
     * {@link ctn.infaut.Controllers.CursoSingleton}
     *
     * @param event representa informacion util de acciones realizadas con el raton como verificar si fue doble click
     * */
	@FXML
	private void completarEnSingleton(MouseEvent event) {
        // Forzar doble click para evitar completados accidentales
        if (event.getClickCount() > 1) {
            Curso seleccion = tablaDeBusqueda.getSelectionModel().getSelectedItem();
            CursoSingleton.getInstance().setIdCurso(seleccion.getIdCurso());
            CursoSingleton.getInstance().setDescripcion(seleccion.getDescripcion());

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource())
                .getScene().getWindow();
            stage.close();
        }
	}
	
}
