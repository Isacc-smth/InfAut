/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ctn.infaut;

import ctn.infaut.DAO.AlumnoDAO;
import ctn.infaut.DTO.AlumnoCursoDTO;
import ctn.infaut.controllers.Alumno;
import ctn.infaut.controllers.AlumnoSingleton;
import ctn.infaut.controllers.Curso;
import ctn.infaut.controllers.CursoSingleton;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
/**
 * FXML Controller
 * class
 *
 * @author
 * isaacfeltes
 */
public class BuscarAlumnoController implements Initializable {
	@FXML
	private TableView<AlumnoCursoDTO> tablaDeBusqueda;
	@FXML
	private TableColumn<AlumnoCursoDTO, Integer> ColumnaId;
	@FXML
	private TableColumn<AlumnoCursoDTO, String> ColumnaNombre;
	@FXML
	private TableColumn<AlumnoCursoDTO, String> ColumnaApellido;
	@FXML
	private TableColumn<AlumnoCursoDTO, Integer> ColumnaCI;
	@FXML
	private TableColumn<AlumnoCursoDTO, Integer> ColumnaCurso;

    private ObservableList<AlumnoCursoDTO> alumnos;

	/**
	 * Initializes
	 * the
	 * controller
	 * class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
        AlumnoDAO AlumnoSQL = new AlumnoDAO();
        alumnos = FXCollections.observableArrayList(AlumnoSQL.consulta());

        ColumnaId.setCellValueFactory(new PropertyValueFactory<>("idAlumno"));
        ColumnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        ColumnaApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        ColumnaCI.setCellValueFactory(new PropertyValueFactory<>("ci"));
        ColumnaCurso.setCellValueFactory(new PropertyValueFactory<>("idCurso"));

        tablaDeBusqueda.setItems(alumnos);
	}
	
	@FXML
	private void completarEnSingleton(MouseEvent event) {
        // Forzar doble click para evitar completados accidentales
        if (event.getClickCount() > 1) {
            AlumnoCursoDTO seleccion = tablaDeBusqueda.getSelectionModel().getSelectedItem();
            AlumnoSingleton.getInstance().setIdAlumno(seleccion.getIdAlumno()); 
            AlumnoSingleton.getInstance().setNombre(seleccion.getNombre());
            AlumnoSingleton.getInstance().setApellido(seleccion.getApellido());
            AlumnoSingleton.getInstance().setCi(seleccion.getCi());
            AlumnoSingleton.getInstance().setIdCurso(seleccion.getIdCurso());

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource())
                .getScene().getWindow();
            stage.close();
        }
	}
}
