/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ctn.infaut;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import java.util.logging.Level;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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

    Parent root;
    Stage stage = new Stage();

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

    public void openFXML(String fxml, String title, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        try {
            root = loader.load();

            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.setResizable(false);

            stage.show();

        } catch (IOException e) {
            System.err.println("Ocurrio un error: ");
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, e);
        }

        stage.show();
    }

    @FXML
    private void openTeacherMenu(ActionEvent event) throws IOException {
        openFXML("MenuProfesores.fxml", "Menu de Tabla Docentes", event);
    }

    @FXML
    private void openStudentMenu(ActionEvent event) throws IOException {
        openFXML("MenuAlumno.fxml", "Menu de Tabla Alumno", event);
    }

    @FXML
    private void openFingerprintMenu(ActionEvent event) throws IOException {
    }

    @FXML
    private void openAssignatureMenu(ActionEvent event) throws IOException {
        openFXML("MenuMaterias.fxml", "Menu de Tabla Materias", event);
    }

    @FXML
    private void openCourseMenu(ActionEvent event) throws IOException {
        openFXML("MenuAulas.fxml", "Menu de Tabla Cursos", event);
    }

    @FXML
    private void openClassroomMenu(ActionEvent event) throws IOException {
        openFXML("MenuAulas.fxml", "Menu de Tabla Aulas", event);
    }

    // @FXML
    // private void openDocs(ActionEvent event) {
    // }
}
