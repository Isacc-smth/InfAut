/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ctn.infaut;

import ctn.infaut.controllers.Docente;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MenuProfesoresControlller implements Initializable {

  boolean isUpdatingRow = false;

  @FXML
  private TableView<Docente> TeachersList;
  @FXML
  private TableColumn<Integer, Docente> idDocente;
  @FXML
  private TableColumn<String, Docente> Nombre;
  @FXML
  private TableColumn<String, Docente> Apellido;
  @FXML
  private TableColumn<Integer, Docente> CI;
  @FXML
  private TextField NameField;
  @FXML
  private TextField LastNameField;
  @FXML
  private TextField IDNumberField;
  @FXML
  private Button New;
  @FXML
  private Button Modify;
  @FXML
  private Button Delete;
  @FXML
  private Button Cancel;
  @FXML
  private Button Save;

  @Override
  public void initialize(URL url, ResourceBundle rb) {
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
  private void updateEntry(ActionEvent event) {
  }

  @FXML
  private void deleteEntry(ActionEvent event) {
  }

  @FXML
  private void cancelOperation(ActionEvent event) {
  }

  @FXML
  private void saveChanges(ActionEvent event) {
    if (isUpdatingRow) {

    } else {

    }
  }
}
