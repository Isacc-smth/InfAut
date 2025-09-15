/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ctn.infaut;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ctn.infaut.DTO.CursoMateriaDTO;
import ctn.infaut.controllers.CursoSingleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author veronicagaleano
 */
public class UnirMateriaCursoController implements Initializable {

    @FXML
    private Button Nuevo;
    @FXML
    private TableView<CursoMateriaDTO> TablaRelacion;
    @FXML
    private TableColumn<CursoMateriaDTO, Integer> idCurso;
    @FXML
    private TableColumn<CursoMateriaDTO, String> Curso;
    @FXML
    private TableColumn<CursoMateriaDTO, Integer> idMateria;
    @FXML
    private TableColumn<CursoMateriaDTO, String> Materia;
    @FXML
    private Button Eliminar;
    private Button Cancelar;
    @FXML
    private Button Guardar;
    @FXML
    private TextField txtMateria;
    @FXML
    private TextField txtCurso;
    private Button buscadorMateria;
    @FXML
    private Button buscadorCurso;

    private boolean isMod = false;
	@FXML
	private Button buscadorAula;
	@FXML
	private Button cancelar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void nuevaRelacion(ActionEvent event) {
        Nuevo.setDisable(true);
        Guardar.setDisable(false);
        Eliminar.setDisable(false);
        Cancelar.setDisable(false);
        Nuevo.setDisable(false);

        buscadorCurso.setDisable(false);    
        buscadorMateria.setDisable(false);
    }

    @FXML
    private void eliminarRelacion(ActionEvent event) {
        Nuevo.setDisable(false);
        Guardar.setDisable(true);
        Eliminar.setDisable(true);
        Cancelar.setDisable(true);
        Nuevo.setDisable(true);

        buscadorCurso.setDisable(true);    
        buscadorMateria.setDisable(true);

        txtCurso.setText("");
        txtMateria.setText("");
    }

    @FXML
    private void cancelarRelacion(ActionEvent event) {
    }

    private void guardarRelaciones(ActionEvent event) {
        if (HayCamposVacios()) {
            Alert camposVacios = AlertFactory.generateAlert(
                AlertType.WARNING, 
                "Campos Vacios",
                "Debe elegir un aula y curso para completar la relacion"
            );
            camposVacios.show();
            return;
        }
    }

    private boolean HayCamposVacios() {
        return (txtCurso.getText().isBlank() || txtMateria.getText().isBlank());
    }

	@FXML
	private void guardarRelacion(ActionEvent event) {
	}

	@FXML
	private void buscarMateria(ActionEvent event) {
        abrirFXMLModal("BuscarMateria.fmxml", "Buscar Materia");
	}

	@FXML
	private void buscarCurso(ActionEvent event) {
        abrirFXMLModal("BuscarCurso.fmxml", "Buscar Curso");
	}

	@FXML
	private void completarCampos(MouseEvent event) {
	}
    
    private void abrirFXMLModal(String fxml, String titulo) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        try {
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setTitle(titulo);

            stage.showAndWait();

            idCurso.setText(String.valueOf(CursoSingleton.getInstance().getIdCurso()));
            Curso.setText(String.valueOf(CursoSingleton.getInstance().getDescripcion()));

        } catch (IOException e) {
            Alert aperturaFXMLFallo = AlertFactory.generateAlert(AlertType.ERROR,
                    "CRITICO:",
                    "No se pudo abir el buscador"
                );
            aperturaFXMLFallo.showAndWait();
        }
    }
}
