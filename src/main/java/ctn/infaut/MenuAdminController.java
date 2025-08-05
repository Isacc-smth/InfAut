/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ctn.infaut;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 * FXML Controller
 * class
 *
 * @author
 *         isaacfeltes
 */
public class MenuAdminController implements Initializable {
  @FXML
  private MenuBar MainMenuBar;
  @FXML
  private Menu ActionsMenu;
  @FXML
  private MenuItem ExitButton;
  @FXML
  private Menu TableSelectionMenu;
  @FXML
  private MenuItem TeacherItem;
  @FXML
  private MenuItem StuedentItem;
  @FXML
  private MenuItem FingerprintItem;
  @FXML
  private MenuItem AssignatureItem;
  @FXML
  private MenuItem CourseItem;
  @FXML
  private MenuItem ClassroomItem;
  @FXML
  private Menu AboutMenu;
  @FXML
  private MenuItem InfautDocsItem;

  @FXML
  public void exitApp(ActionEvent event) {
    Platform.exit();
  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {

  }

  @FXML
  private void openTeacherMenu(ActionEvent event) {
  }

  @FXML
  private void openStudentMenu(ActionEvent event) {
  }

  @FXML
  private void openFingerprintMenu(ActionEvent event) {
  }

  @FXML
  private void openAssignatureMenu(ActionEvent event) {
  }

  @FXML
  private void openCourseMenu(ActionEvent event) {
  }

  @FXML
  private void openClassroomMenu(ActionEvent event) {
  }

  @FXML
  private void openDocs(ActionEvent event) {
  }
}
