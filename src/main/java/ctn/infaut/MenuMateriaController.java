/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ctn.infaut;

// TODO: Menu de buscar sala

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.scene.control.TableView;

import ctn.infaut.DAO.MateriaDAO;
import ctn.infaut.controllers.AulaSingleton;
import ctn.infaut.controllers.CursoSingleton;
import ctn.infaut.controllers.Materia;
import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * 
 * Controlador del menu de materias.
 *
 * @author isaacfeltes
 */
public class MenuMateriaController implements Initializable {
    @FXML
    private Button New;
    @FXML
    private Button Save;
    @FXML
    private Button Cancel;
    @FXML
    private Button Delete;
	@FXML
	private Button buscarAula;

    @FXML
    private TableView<Materia> ClassesTable;
    @FXML
    private TableColumn<Materia, String> ColumnNombre;
    @FXML
    private TableColumn<Materia, String> ColumnHoraInicio;
    @FXML
    private TableColumn<Materia, String> ColumnHoraFin;
    @FXML
    private TableColumn<Materia, String> ColumnSala;

    @FXML
    private TextField NumMateria;
    @FXML
    private TextField Nombre;
    @FXML
    private TextField HoraInicio;
    @FXML
    private TextField HoraFin;
    @FXML
    private TextField NumSala;

    ObservableList<Materia> classesList;
    private boolean isMod = false;
    private MateriaDAO MateriaSQL;

    /**
     * Habilita los botones y campos de texto
     * como para insertar una nueva materia
     *
     * @param event indica que el boton esta presionado.
     */
    @FXML
    private void newEntry(ActionEvent event) {
        New.setDisable(true);
        Save.setDisable(false);
        Cancel.setDisable(false);

        Nombre.setDisable(false);
        HoraInicio.setDisable(false);
        HoraFin.setDisable(false);
        NumSala.setDisable(false);
		buscarAula.setDisable(false);

        isMod = false;
    }

    /**
     * Interectúa con {@link ctn.infaut.DAO.MateriaDAO} para insertar
     * o modificar una fila con los datos proporcionados en los campos de texto
     * 
     * @param event indica que el boton esta presionado.
     * @see ctn.infaut.DAO.MateriaDAO
     */
    @FXML
    private void saveChanges(ActionEvent event) throws IllegalArgumentException {

        if (hayCamposVacios()) {
            Alert invalidFields = AlertFactory.generateAlert(
                    AlertType.WARNING, 
                    "Campos vacíos",
                    "Tiene que completar todos para guardar cambios"
                );
            return;
        }

        String nombre = Nombre.getText();
        String hora_incio = HoraInicio.getText();
        String hora_fin = HoraFin.getText();
        Integer num_sala = Integer.parseInt(NumSala.getText());

        Materia m = new Materia(nombre, hora_incio, hora_fin, num_sala);

        if (isMod) {
            Integer num_materia = Integer.parseInt(NumMateria.getText());
            m.setIdMateria(num_materia);

            if (MateriaSQL.modificar(m)) {
                Alert modSuccess = AlertFactory.generateAlert(Alert.AlertType.INFORMATION,
                        "Se modifico la materia con exito");
                modSuccess.show();
            } else {
                Alert modFailure = AlertFactory.generateAlert(Alert.AlertType.ERROR,
                        "Hubo un error al modificar la materia");
                modFailure.show();
            }
        } else {
            if (MateriaSQL.insertar(m)) {
                Alert insSuccess = AlertFactory.generateAlert(Alert.AlertType.INFORMATION,
                        "Se inserto la materia con exito");
                insSuccess.show();
            } else {
                Alert insFailure = AlertFactory.generateAlert(Alert.AlertType.ERROR,
                        "Hubo un error al insertar la materia");
                insFailure.show();
            }
        }
    }

    /**
     * Método para deshabilitar los campos de textos y limpiar su entrada.
     * 
     * @param event representa al botón siendo presionado
     *
     */
    @FXML
    private void cancel(ActionEvent event) {
        New.setDisable(false);
        Save.setDisable(true);
        Cancel.setDisable(true);
        Delete.setDisable(true);

        NumMateria.setDisable(true);
        Nombre.setDisable(true);
        HoraInicio.setDisable(true);
        HoraFin.setDisable(true);
        NumSala.setDisable(true);

        NumMateria.setText("");
        Nombre.setText("");
        HoraInicio.setText("");
        HoraFin.setText("");
        NumSala.setText("");

        isMod = false;
    }

    /**
     * Si el usuario lo confirma, interectúa con
     * MateriaDAO para eliminar la columna
     * con los datos de los campos de texto.
     *
     * @param event representa el botón siendo accionado
     * @see {@link ctn.infaut.DAO.MateriaDAO}
     */

    @FXML
    private void delete(ActionEvent event) {
        Alert confirmDelete = AlertFactory.generateAlert(
            Alert.AlertType.CONFIRMATION,
            "Seguro que desea borrar el aula. ESTA ACCION NO SE PUEDE REVERTIR"
        );

        Integer num_materia = Integer.parseInt(NumMateria.getText());
        Integer num_sala = Integer.parseInt(NumSala.getText());

        String nombre = Nombre.getText();
        String hora_inicio = HoraInicio.getText();
        String hora_fin = HoraFin.getText();

        Materia m = new Materia(num_materia, nombre, hora_inicio, hora_fin, num_sala);

        Optional<ButtonType> option = confirmDelete.showAndWait();
        if (option.get() == ButtonType.OK) {
            if (MateriaSQL.eliminar(m)) {
                Alert deleteSuccess = AlertFactory.generateAlert(Alert.AlertType.INFORMATION,
                        "Se elimino la materia con exito");
                deleteSuccess.show();
                updateTable();
            } else {
                Alert deleteFailure = AlertFactory.generateAlert(Alert.AlertType.ERROR,
                        "Hubo un error al eliminar la materia");
                deleteFailure.show();
            }
        }
    }

    /**
     * Completa los campos de texto y cambia el estado para modificar la
     * fila seleccionada, al hacer clic en una fila de la tabla con las materias.
     *
     * @param event representa el click del mouse
     */
    @FXML
    private void completeFields(MouseEvent event) {
        Materia selection = ClassesTable.getSelectionModel().getSelectedItem();

        NumMateria.setText(String.valueOf(selection.getIdMateria()));
        NumSala.setText(String.valueOf(selection.getIdMateria()));

        Nombre.setText(selection.getNombre());
        HoraInicio.setText(selection.getHoraInicio());
        HoraFin.setText(selection.getHoraFin());

        New.setDisable(true);
        Save.setDisable(false);
        Cancel.setDisable(false);
        Delete.setDisable(false);

        Nombre.setDisable(false);
        HoraInicio.setDisable(false);
        HoraFin.setDisable(false);
        NumSala.setDisable(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MateriaSQL = new MateriaDAO();
        updateTable();
    }

    private void updateTable() {
        classesList = FXCollections.observableArrayList(MateriaSQL.consulta());
        if (classesList != null) {
            ColumnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            ColumnHoraInicio.setCellValueFactory(new PropertyValueFactory<>("horaInicio"));
            ColumnHoraFin.setCellValueFactory(new PropertyValueFactory<>("horaFin"));
            ColumnSala.setCellValueFactory(new PropertyValueFactory<>("idAula"));

            ClassesTable.setItems(classesList);

        } else {
            Alert queryFail = AlertFactory.generateAlert(Alert.AlertType.ERROR, "Hubo un error al obtener las tablas");
            queryFail.show();
        }
    }

    private boolean hayCamposVacios() {
        return (
            Nombre.getText().trim().isEmpty() ||
            HoraInicio.getText().trim().isEmpty() ||
            HoraFin.getText().trim().isEmpty() ||
            NumSala.getText().trim().isEmpty()
        );
    }

	@FXML
	private void abrirBuscadorAula(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BuscarAula.fxml"));
            System.out.println("Buscando en: " + getClass().getResource("BuscarAula.fxml"));

            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setTitle("Buscar Aula");

            stage.showAndWait();
			
			NumSala.setText(String.valueOf(AulaSingleton.getInstance().getIdAula()));

        } catch (IOException e) {
            Alert aperturaFXMLFallo = AlertFactory.generateAlert(AlertType.ERROR,
                    "CRITICO:",
                    "No se pudo abir el buscador: " + e.getMessage()
                );
            aperturaFXMLFallo.showAndWait();
        }
	}
}
