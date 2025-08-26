package ctn.infaut;

import ctn.infaut.controllers.Docente;
import ctn.infaut.DAO.DocenteDAO;
import ctn.infaut.AlertFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
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

public class MenuProfesoresController implements Initializable {

    Docente d = new Docente();
    DocenteDAO DocenteSQL;

    boolean isUpdatingRow = false;

    ObservableList<Docente> rows;

    @FXML
    private TableView<Docente> TeachersList;
    @FXML
    private TableColumn<Docente, Integer> idDocente;
    @FXML
    private TableColumn<Docente, String> Nombre;
    @FXML
    private TableColumn<Docente, String> Apellido;
    @FXML
    private TableColumn<Docente, Integer> CI;
    @FXML
    private TextField NameField;
    @FXML
    private TextField LastNameField;
    @FXML
    private TextField IDNumberField;
    @FXML
    private TextField TeacherIDField;
    @FXML
    private Button New;
    @FXML
    private Button Delete;
    @FXML
    private Button Cancel;
    @FXML
    private Button Save;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            DocenteSQL = new DocenteDAO();
            updateTable();
        } catch (SQLException e) {
            Alert TableRetrieveFailure = AlertFactory.generateAlert(Alert.AlertType.ERROR,
                    "Error al obtener a los docentes: \n" + e.getMessage());
            TableRetrieveFailure.show();
        }
    }

    private void updateTable() throws SQLException {
        rows = FXCollections.observableArrayList(DocenteSQL.consulta());

        idDocente.setCellValueFactory(new PropertyValueFactory<>("idDocente"));
        Nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        Apellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        CI.setCellValueFactory(new PropertyValueFactory<>("CI"));

        TeachersList.setItems(rows);
    }

    @FXML
    private void newEntry(ActionEvent event) {
        New.setDisable(true);
        Cancel.setDisable(false);
        Save.setDisable(false);

        NameField.setDisable(false);
        LastNameField.setDisable(false);
        IDNumberField.setDisable(false);
    }

    @FXML
    private void deleteEntry(ActionEvent event) throws SQLException {
        Alert ConfirmDelete = AlertFactory.generateAlert(Alert.AlertType.CONFIRMATION,
                "Seguro que desea borrar al docente." + "ESTA ACCION NO SE PUEDE REVERTIR");

        Optional<ButtonType> optional = ConfirmDelete.showAndWait();

        if (optional.get() == ButtonType.OK) {
            String name = NameField.getText();
            String lastName = LastNameField.getText();
            int idNumber = Integer.parseInt(IDNumberField.getText());
            int teacherId = Integer.parseInt(TeacherIDField.getText());

            d = new Docente(teacherId, name, lastName, idNumber);
            if (DocenteSQL.eliminar(d)) {
                Alert DeleteSuccess = AlertFactory.generateAlert(Alert.AlertType.INFORMATION,
                        "Se borro al docente con extio. Fuiste advertido");
                cleanFields();
                DeleteSuccess.show();
            }
        }

        updateTable();
    }

    private Docente getSelectedRow() {
        return TeachersList.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void completeFields(MouseEvent event) {
        d = getSelectedRow();

        if (d != null) {
            TeacherIDField.setText(String.valueOf(d.getIdDocente()));
            NameField.setText(d.getNombre());
            LastNameField.setText(d.getApellido());
            IDNumberField.setText(String.valueOf(d.getCI()));

            NameField.setDisable(false);
            LastNameField.setDisable(false);
            IDNumberField.setDisable(false);

            Cancel.setDisable(false);
            Save.setDisable(false);
            New.setDisable(true);
            Delete.setDisable(false);

            isUpdatingRow = true;
        }
    }

    @FXML
    private void cancelOperation(ActionEvent event) {
        New.setDisable(false);
        Cancel.setDisable(true);

        Save.setDisable(true);
        NameField.setDisable(true);
        LastNameField.setDisable(true);
        IDNumberField.setDisable(true);

        isUpdatingRow = false;
    }

    private void cleanFields() {
        TeacherIDField.setText("");
        NameField.setText("");
        LastNameField.setText("");
        IDNumberField.setText("");
    }

    @FXML
    private void saveChanges(ActionEvent event) throws NumberFormatException, SQLException {

        String name = NameField.getText();
        String lastName = LastNameField.getText();

        if (name.isBlank() || lastName.isBlank() || IDNumberField.getText().isBlank()) {
            Alert InvalidInput = AlertFactory.generateAlert(Alert.AlertType.ERROR, "Los campos no pueden estar vacios");
            InvalidInput.show();
        }

        int idNumber = Integer.parseInt(IDNumberField.getText());

        d = new Docente(name, lastName, idNumber);
        if (this.isUpdatingRow) {
            d.setIdDocente(Integer.parseInt(TeacherIDField.getText()));

            if (DocenteSQL.modificar(d)) {
                Alert ModifySuccess = AlertFactory.generateAlert(Alert.AlertType.INFORMATION,
                        "Se modifico con exito los datos del docente");
                ModifySuccess.show();
            } else {
                Alert ModifyFailure = AlertFactory.generateAlert(Alert.AlertType.ERROR,
                        "Ocurrio un error al modificar los datos");
                ModifyFailure.show();
            }
        } else {
            if (DocenteSQL.insertar(d)) {
                Alert InsertSuccess = AlertFactory.generateAlert(Alert.AlertType.INFORMATION,
                        "Se inserto al docente con exito");
                InsertSuccess.show();
            } else {
                Alert ModifyFailure = AlertFactory.generateAlert(Alert.AlertType.ERROR,
                        "Ocurrio un error al insertar al docente");
                ModifyFailure.show();
            }
        }
        cleanFields();
        updateTable();
    }
}
