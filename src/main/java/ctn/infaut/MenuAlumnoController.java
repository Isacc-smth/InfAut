package ctn.infaut;

import ctn.infaut.DAO.AlumnoDAO;
import ctn.infaut.DTO.AlumnoCursoDTO;
import ctn.infaut.controllers.Alumno;
import ctn.infaut.controllers.CursoSingleton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Optional;
import java.util.ResourceBundle;

/**
 * FXML Controller
 * class
 *
 * @author isaacfeltes
 */
public class MenuAlumnoController implements Initializable {
    @FXML
    private TableView<AlumnoCursoDTO> StudentsTable;

    @FXML
    private TableColumn<AlumnoCursoDTO, Integer> ColumnId;
    @FXML
    private TableColumn<AlumnoCursoDTO, String> ColumnNombre;
    @FXML
    private TableColumn<AlumnoCursoDTO, String> ColumnApellido;
    @FXML
    private TableColumn<AlumnoCursoDTO, Integer> ColumnCI;
    @FXML
    private TableColumn<AlumnoCursoDTO, Integer> ColumnIdCurso;
    @FXML
    private TableColumn<AlumnoCursoDTO, String> ColumnCurso;

    @FXML
    private TextField idAlumno;
    @FXML
    private TextField Nombre;
    @FXML
    private TextField Apellido;
    @FXML
    private TextField CI;
    @FXML
    private TextField idCurso;
    @FXML
    private TextField Curso;
    @FXML
    private Button Nuevo;
    @FXML
    private Button Guardar;
    @FXML
    private Button Eliminar;
    @FXML
    private Button Cancelar;

    private AlumnoDAO AlumnoSQL;

    private boolean isModifying = false;

    private ObservableList<AlumnoCursoDTO> studentList;

    /**
     * 
     * Initializes the
     * controller
     * class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            AlumnoSQL = new AlumnoDAO();
            updateTable();
        } catch (SQLException e) {
            Alert initialQueryFailed = AlertFactory.generateAlert(Alert.AlertType.ERROR, "Error critico!!",
                    "Hubo un error al ohbtener las filas: \n" + e.getMessage());
        }
    }

    @FXML
    private void completeFields(MouseEvent event) {
        AlumnoCursoDTO selection = StudentsTable.getSelectionModel().getSelectedItem();
        if (selection != null) {
            idAlumno.setText(String.valueOf(selection.getIdAlumno()));
            Nombre.setText(selection.getNombre());
            Apellido.setText(selection.getApellido());
            CI.setText(String.valueOf(selection.getCi()));

            idCurso.setText(String.valueOf(selection.getIdCurso()));
            Curso.setText(selection.getDescripcion());

            isModifying = true;
            prepareButtonsAndFields();
        }
    }

    @FXML
    private void newEntry(ActionEvent event) {
        prepareButtonsAndFields();

        isModifying = false;
    }

    private Alumno getFromTextFields() {
        try {
            String nombre = Nombre.getText();
            String apellido = Apellido.getText();
            Integer num_ci = Integer.parseInt(CI.getText());
            Integer num_curso = Integer.parseInt(idCurso.getText());


            Alumno al;

            if (isModifying) {
                Integer id_alumno = Integer.parseInt(idAlumno.getText());
                al = new Alumno(id_alumno, nombre, apellido, num_ci, num_curso);
            } else {
                al = new Alumno(nombre, apellido, num_ci, num_curso);
            }

            return al;
        } catch (NumberFormatException e) {
            Alert modSuccess = AlertFactory.generateAlert(Alert.AlertType.INFORMATION,
                    "Formato Incorrecto!!",
                    "Verifique los campos numericos");
            modSuccess.show();

            return null;
        }
    }

    @FXML
    private void saveChanges(ActionEvent event) throws NumberFormatException {

        Alumno al = getFromTextFields();

        if (isModifying & al != null) {
            if (AlumnoSQL.modificar(al)) {
                Alert modSuccess = AlertFactory.generateAlert(Alert.AlertType.INFORMATION,
                        "Se modifico al alumno con exito");
                modSuccess.show();
            } else {
                Alert modFailure = AlertFactory.generateAlert(Alert.AlertType.ERROR,
                        "Hubo un error al modificar al alumno");
                modFailure.show();
            }
        } else {
            if (AlumnoSQL.insertar(al)) {
                Alert insSuccess = AlertFactory.generateAlert(Alert.AlertType.INFORMATION,
                        "Se inserto al alumno con exito");
                insSuccess.show();
            } else {
                Alert insFailure = AlertFactory.generateAlert(Alert.AlertType.ERROR,
                        "Hubo un error al insertar al alumno");
                insFailure.show();
            }
        }

        updateTable();
        cancel(event);
    }

    @FXML
    private void deleteEntry(ActionEvent event) {

        Alumno al = getFromTextFields();

        Alert confirmDelete = AlertFactory.generateAlert(Alert.AlertType.CONFIRMATION,
                "Seguro que deseas borrar al alumno. ESTA ACCION NO SE PUEDE REVERTIR");

        Optional<ButtonType> option = confirmDelete.showAndWait();
        if (option.get() == ButtonType.OK) {
            if (AlumnoSQL.eliminar(al)) {
                Alert deleteSucces = AlertFactory.generateAlert(Alert.AlertType.INFORMATION,
                        "Se elimino al alumno con exito");
                deleteSucces.show();
            } else {
                Alert deleteFailure = AlertFactory.generateAlert(Alert.AlertType.ERROR,
                        "Hubo un error al eliminar al alumno");
                deleteFailure.show();
            }
        }

        updateTable();
    }

    @FXML
    private void cancel(ActionEvent event) {
        idAlumno.setText("");
        Nombre.setText("");
        Apellido.setText("");
        CI.setText("");

        idCurso.setText("");
        Curso.setText("");

        Nuevo.setDisable(false);
        Guardar.setDisable(true);
        Eliminar.setDisable(true);
        Cancelar.setDisable(true);

        Nombre.setDisable(true);
        Apellido.setDisable(true);
        CI.setDisable(true);
        idCurso.setDisable(true);

    }

    @FXML
    private void abrirBuscadorCurso(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BuscarCurso.fxml"));
        try {
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setTitle("Buscar Curso");

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

    private void updateTable() {
        studentList = FXCollections.observableArrayList(AlumnoSQL.consulta());

        ColumnId.setCellValueFactory(new PropertyValueFactory<>("idAlumno"));
        ColumnCI.setCellValueFactory(new PropertyValueFactory<>("ci"));
        ColumnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        ColumnApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        ColumnIdCurso.setCellValueFactory(new PropertyValueFactory<>("idCurso"));
        ColumnCurso.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        StudentsTable.setItems(studentList);
    }

    public void prepareButtonsAndFields() {
        Nuevo.setDisable(true);
        Guardar.setDisable(false);
        Eliminar.setDisable(false);
        Cancelar.setDisable(false);

        Nombre.setDisable(false);
        Apellido.setDisable(false);
        CI.setDisable(false);
        idCurso.setDisable(false); // para que pueda cambiar la referencia del curso

    }
}
