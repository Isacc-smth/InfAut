package ctn.infaut;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


import ctn.infaut.DAO.AulaDAO;
import ctn.infaut.controllers.Aula;
import ctn.infaut.controllers.AulaSingleton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class BuscarAulaController implements Initializable {

	@FXML
	private TableView<Aula> tablaDeBusqueda;
	@FXML
	private TableColumn<Aula, Integer> ColumnaId;
    @FXML
	private TableColumn<Aula, String> ColumnaAula;

    private ObservableList<Aula> cursos;

	/**
     * Inicia el controlador, buscador de la tabla de aulas y
     * agregando las entradas a la tabla
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
        try {
            AulaDAO AulaSQL = new AulaDAO();
            cursos = FXCollections.observableArrayList(AulaSQL.consulta());

            ColumnaId.setCellValueFactory(new PropertyValueFactory<>("idAula"));
            ColumnaAula.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

            tablaDeBusqueda.setItems(cursos);

        } catch (SQLException e) {
            Alert consultaInicialFallo = AlertFactory.generateAlert(AlertType.ERROR, 
                    "Hubo un error al obtener los cursos: " + 
                    e.getMessage()
                );
            System.out.println(e.getMessage());
            consultaInicialFallo.show();
        }
	}	

    /**
     * Obtiene el curso seleccionado y lo asigna a
     * {@link ctn.infaut.Controllers.AulaSingleton}
     *
     * @param event representa informacion util de acciones realizadas con el raton como verificar si fue doble click
     * */
	@FXML
	private void completarEnSingleton(MouseEvent event) {
        // Forzar doble click para evitar completados accidentales
        if (event.getClickCount() > 1) {
            Aula seleccion = tablaDeBusqueda.getSelectionModel().getSelectedItem();
            AulaSingleton.getInstance().setIdAula(seleccion.getIdAula());
            AulaSingleton.getInstance().setDescripcion(seleccion.getDescripcion());

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource())
                .getScene().getWindow();
            stage.close();
        }
	}
}
