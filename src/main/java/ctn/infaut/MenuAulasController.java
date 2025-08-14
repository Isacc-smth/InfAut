package ctn.infaut;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
import ctn.infaut.controllers.Aula;
import ctn.infaut.services.AlertFactory;
import ctn.infaut.services.AulaService;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
  }

  public void updateTable() {
    roomsList = FXCollections.observableArrayList(AulaService.consulta());

    idColumn.setCellValueFactory(new PropertyValueFactory<>("idAula"));
    descColumn.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
    RoomsTable.setItems(roomsList);
  }

  @FXML
  private void completeField(MouseEvent event) {
    Aula selection = RoomsTable.getSelectionModel().getSelectedItem();

    Description.setText(selection.getDescripcion());

    isMod = true;
  }

  @FXML
  private void newRoom(ActionEvent event) {
    New.setDisable(true);
    Save.setDisable(false);
    Delete.setDisable(false);
    Cancel.setDisable(false);
  }

  @FXML
  private void saveChanges(ActionEvent event) {
    String desc = Description.getText();
    Integer id = Integer.parseInt(RoomId.getText());
    Aula a = new Aula(id, desc);

    if (isMod) {
      insert(a);
    } else {

    }
  }

  private void insert(Aula room) {
    if (!AulaService.insertar(room)) {
      Alert insertFailed = AlertFactory.generateAlert(Alert.AlertType.ERROR, "No se pudo insertar el aula"
          + "verifique la entrada");
      insertFailed.show();
    } else {
      Alert insertSuccess = AlertFactory.generateAlert(Alert.AlertType.INFORMATION, "Se inserto el aula con exito");
      insertSuccess.show();
    }
  }

  @FXML
  private void cancelCanges(ActionEvent event) {
  }

}
