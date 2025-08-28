package ctn.infaut;

import ctn.infaut.DAO.AlumnoDAO;
import ctn.infaut.controllers.Alumno;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.net.URL;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

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
    private TableView<Alumno> StudentsTable;

    @FXML
    private TableColumn<Alumno, Integer> ColumnId;
    @FXML
    private TableColumn<Alumno, String> ColumnNombre;
    @FXML
    private TableColumn<Alumno, String> ColumnApellido;
    @FXML
    private TableColumn<Alumno, Integer> ColumnCI;
    @FXML
    private TableColumn<Alumno, Integer> ColumnIdCurso;
    @FXML
    private TableColumn<Alumno, String> ColumnCurso;

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

    private ObservableList<Alumno> studentList;

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
        Alumno selection = StudentsTable.getSelectionModel().getSelectedItem();
        if (selection != null) {
            idAlumno.setText(String.valueOf(selection.getIdAlumno()));
            Nombre.setText(selection.getNombre());
            Apellido.setText(selection.getApellido());
            CI.setText(String.valueOf(selection.getCi()));
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
            Integer num_curso = Integer.parseInt(Curso.getText());

            Alumno al;

            if (isModifying) {
                Integer id_alumno = Integer.parseInt(idAlumno.getText());
                al = new Alumno(id_alumno, nombre, apellido, num_ci, num_curso);
            } else {
                al = new Alumno(nombre, apellido, num_ci, num_curso);
            }

            return al;
            // HDM POR FIN ME FUNCIONAN LOS INLAY HINTS LA PUTA QUE LO PARIOOOOO
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

        Nuevo.setDisable(false);
        Guardar.setDisable(true);
        Eliminar.setDisable(true);
        Cancelar.setDisable(true);

        Nombre.setDisable(true);
        Apellido.setDisable(true);
        CI.setDisable(true);

    }

    private void updateTable() {
        studentList = FXCollections.observableArrayList(AlumnoSQL.consulta());

        ColumnId.setCellValueFactory(new PropertyValueFactory<>("idAlumno"));
        ColumnCI.setCellValueFactory(new PropertyValueFactory<>("ci"));
        ColumnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        ColumnApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));

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
    }
}
