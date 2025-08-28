package ctn.infaut;

import ctn.infaut.controllers.Curso;
import ctn.infaut.DAO.CursoDAO;
import ctn.infaut.DAO.CursoDAO;
import ctn.infaut.AlertFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class MenuCursoController implements Initializable {

    ObservableList<Curso> roomsList;
    static boolean isMod = false;
    CursoDAO CursoSQL;

    @FXML
    private TableView<Curso> RoomsTable;
    @FXML
    private TableColumn<Curso, Integer> idColumn;
    @FXML
    private TableColumn<Curso, String> descColumn;
    @FXML
    private TextField RoomId;
    @FXML
    private TextField Description;
    @FXML
    private Button New;
    @FXML
    private Button Save;
    @FXML
    private Button Delete;
    @FXML
    private Button Cancel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            CursoSQL = new CursoDAO();
            updateTable();
        } catch (SQLException e) {
            Alert initialQueryFailure = AlertFactory.generateAlert(Alert.AlertType.ERROR,
                    "Hubo un error al obtener los cursos: " + e.getMessage());
            initialQueryFailure.show();
        }
    }

    public void updateTable() throws SQLException {
        roomsList = FXCollections.observableArrayList(CursoSQL.consulta());

        idColumn.setCellValueFactory(new PropertyValueFactory<>("idCurso"));
        descColumn.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        RoomsTable.setItems(roomsList);
    }

    @FXML
    private void deleteRoom(ActionEvent event) throws SQLException {
        Alert confirmRemoveAlert = AlertFactory.generateAlert(
                Alert.AlertType.CONFIRMATION,
                "Seguro que desea eliminar el curso. ESTA ACCION NO SE PUEDE REVERTIR!!");
        Optional<ButtonType> opt = confirmRemoveAlert.showAndWait();

        Integer idCurso = Integer.parseInt(RoomId.getText());
        String desc = Description.getText();
        Curso roomToDelete = new Curso(idCurso, desc);

        if (opt.get() == ButtonType.OK) {
            if (CursoSQL.eliminar(roomToDelete)) {
                Alert deleteSuccess = AlertFactory.generateAlert(Alert.AlertType.INFORMATION,
                        "Se elimino el curso con exito. Fuiste advertido");

                deleteSuccess.show();
            } else {
                Alert deleteFailure = AlertFactory.generateAlert(Alert.AlertType.ERROR,
                        "Hubo un error al eliminar el curso");

                deleteFailure.show();
                updateTable();
            }
        }
    }

    @FXML
    private void completeField(MouseEvent event) {
        Curso selection = RoomsTable.getSelectionModel().getSelectedItem();

        if (selection != null) {
            RoomId.setText(String.valueOf(selection.getIdCurso()));
            Description.setText(selection.getDescripcion());
        }

        New.setDisable(true);
        Save.setDisable(false);
        Delete.setDisable(false);
        Cancel.setDisable(false);

        Description.setDisable(false);

        isMod = true;
    }

    @FXML
    private void newRoom(ActionEvent event) {
        New.setDisable(true);
        Save.setDisable(false);
        Delete.setDisable(false);
        Cancel.setDisable(false);

        Description.setDisable(false);
    }

    @FXML
    private void saveChanges(ActionEvent event) throws SQLException {
        String desc = Description.getText();

        if (isMod) {
            Integer id = Integer.parseInt(RoomId.getText());
            Curso a = new Curso(id, desc);
            System.out.println("isMod es: " + isMod);
            update(a);
        } else {
            Curso a = new Curso(desc);
            System.out.println("isMod es false: " + isMod);
            insert(a);
        }
        updateTable();
    }

    private void insert(Curso a) throws SQLException {
        if (!CursoSQL.insertar(a)) {
            Alert insertFailed = AlertFactory.generateAlert(Alert.AlertType.ERROR, "No se pudo insertar el curso "
                    + "verifique la entrada");
            insertFailed.show();
        } else {
            Alert insertSuccess = AlertFactory.generateAlert(Alert.AlertType.INFORMATION,
                    "Se inserto el curso con exito");
            insertSuccess.show();
        }
    }

    private void update(Curso a) throws SQLException {
        if (!CursoSQL.modificar(a)) {
            Alert modFailed = AlertFactory.generateAlert(Alert.AlertType.ERROR, "No se puedo modificar el curso");
            modFailed.show();
        } else {
            Alert modSuccess = AlertFactory.generateAlert(Alert.AlertType.INFORMATION, "Se modifico el curso con exito");
            isMod = false;
            modSuccess.show();
        }
    }

    @FXML
    private void cancelOperation(ActionEvent event) {
        New.setDisable(false);
        Save.setDisable(true);
        Delete.setDisable(true);
        Cancel.setDisable(true);

        Description.setDisable(true);
        isMod = false;
    }
}
