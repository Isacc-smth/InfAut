package ctn.infaut;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
import ctn.infaut.controllers.Aula;
import ctn.infaut.AlertFactory;
import ctn.infaut.DAO.AulaDAO;
import java.net.URL;
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

public class MenuAulasController implements Initializable {

    ObservableList<Aula> roomsList;
    static boolean isMod = false;

    @FXML
    private TableView<Aula> RoomsTable;
    @FXML
    private TableColumn<Aula, Integer> idColumn;
    @FXML
    private TableColumn<Aula, String> descColumn;
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
        updateTable();
    }

    public void updateTable() {
        roomsList = FXCollections.observableArrayList(AulaService.consulta());

        idColumn.setCellValueFactory(new PropertyValueFactory<>("idAula"));
        descColumn.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        RoomsTable.setItems(roomsList);
    }

    @FXML
    private void deleteRoom(ActionEvent event) {
        Alert confirmRemoveAlert = AlertFactory.generateAlert(
                Alert.AlertType.CONFIRMATION,
                "Seguro que desea eliminar el aula. ESTA ACCION NO SE PUEDE REVERTIR!!");
        Optional<ButtonType> opt = confirmRemoveAlert.showAndWait();

        Integer idAula = Integer.parseInt(RoomId.getText());
        String desc = Description.getText();
        Aula roomToDelete = new Aula(idAula, desc);

        if (opt.get() == ButtonType.OK) {
            if (AulaService.eliminar(roomToDelete)) {
                Alert deleteSuccess = AlertFactory.generateAlert(Alert.AlertType.INFORMATION,
                        "Se elimino el aula con exito. Fuiste advertido");

                deleteSuccess.show();
            } else {
                Alert deleteFailure = AlertFactory.generateAlert(Alert.AlertType.ERROR,
                        "Hubo un error al eliminar el aula");

                deleteFailure.show();
                updateTable();
            }
        }
    }

    @FXML
    private void completeField(MouseEvent event) {
        Aula selection = RoomsTable.getSelectionModel().getSelectedItem();

        if (selection != null) {
            RoomId.setText(String.valueOf(selection.getIdAula()));
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
    private void saveChanges(ActionEvent event) {
        String desc = Description.getText();

        if (isMod) {
            Integer id = Integer.parseInt(RoomId.getText());
            Aula a = new Aula(id, desc);
            System.out.println("isMod es: " + isMod);
            update(a);
        } else {
            Aula a = new Aula(desc);
            System.out.println("isMod es false: " + isMod);
            insert(a);
        }
        updateTable();
    }

    private void insert(Aula a) {
        if (!AulaService.insertar(a)) {
            Alert insertFailed = AlertFactory.generateAlert(Alert.AlertType.ERROR, "No se pudo insertar el aula"
                    + "verifique la entrada");
            insertFailed.show();
        } else {
            Alert insertSuccess = AlertFactory.generateAlert(Alert.AlertType.INFORMATION,
                    "Se inserto el aula con exito");
            insertSuccess.show();
        }
    }

    private void update(Aula a) {
        if (!AulaService.modificar(a)) {
            Alert modFailed = AlertFactory.generateAlert(Alert.AlertType.ERROR, "No se puedo modificar el aula");
            modFailed.show();
        } else {
            Alert modSuccess = AlertFactory.generateAlert(Alert.AlertType.INFORMATION, "Se modifico el aula con exito");
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
