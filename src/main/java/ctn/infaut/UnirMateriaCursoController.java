/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ctn.infaut;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author veronicagaleano
 */
public class UnirMateriaCursoController implements Initializable {

    @FXML
    private Button Nuevo;
    @FXML
    private TableView<?> TablaRelacion;
    @FXML
    private TableColumn<?, ?> idCurso;
    @FXML
    private TableColumn<?, ?> Curso;
    @FXML
    private TableColumn<?, ?> idMateria;
    @FXML
    private TableColumn<?, ?> Materia;
    @FXML
    private Button Eliminar;
    @FXML
    private Button cancelar;
    @FXML
    private Button Guardar;
    @FXML
    private TextField txtMateria;
    @FXML
    private TextField txtCurso;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void nuevoRelacion(ActionEvent event) {
    }

    @FXML
    private void eliminarRelacion(ActionEvent event) {
    }

    @FXML
    private void cancelarRelacion(ActionEvent event) {
    }

    @FXML
    private void guardarRelaciones(ActionEvent event) {
    }
    
}
