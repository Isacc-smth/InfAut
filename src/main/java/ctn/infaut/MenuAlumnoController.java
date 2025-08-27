/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ctn.infaut;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller
 * class
 *
 * @author
 *         isaacfeltes
 */
public class MenuAlumnoController implements Initializable {
    @FXML
    private TableView<?> StudentsTable;
    @FXML
    private TableColumn<?, ?> ColumnId;
    @FXML
    private TableColumn<?, ?> ColumnNombre;
    @FXML
    private TableColumn<?, ?> ColumnApellido;
    @FXML
    private TableColumn<?, ?> ColumnCI;
    @FXML
    private TextField idAlumno;
    @FXML
    private TextField Nombre;
    @FXML
    private TextField Apellido;
    @FXML
    private TextField CI;
    @FXML
    private Button Nuevo;
    @FXML
    private Button Guardar;
    @FXML
    private Button Eliminar;
    @FXML
    private Button Cancelar;

    /**
     * Initializes the
     * controller
     * class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
