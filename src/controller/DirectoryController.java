/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Directory;

/**
 * FXML Controller class
 *
 * @author Fausto
 */
public class DirectoryController implements Initializable {

    @FXML
    private Button btnIngresar;
    @FXML
    private TableView<Directory> tblDirectory;
    @FXML
    private TableColumn colPagina;
    @FXML
    private TableColumn colCorreo;
    @FXML
    private TableColumn colPass;
    @FXML
    private TableColumn colUsuario;
    
    private ObservableList<Directory> directorio;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        directorio = FXCollections.observableArrayList();

        this.colPagina.setCellValueFactory(new PropertyValueFactory("pagina"));
        this.colUsuario.setCellValueFactory(new PropertyValueFactory("usuario"));
        this.colCorreo.setCellValueFactory(new PropertyValueFactory("correo"));
        this.colPass.setCellValueFactory(new PropertyValueFactory("pass"));
        this.tblDirectory.setItems(directorio);
    }

    @FXML
    private void click(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/InsertDataView.fxml"));
            Parent root = loader.load();
            InsertDataController insertar = loader.getController();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            
            Directory registro = insertar.getRegistro();
            
            if (!this.directorio.contains(registro)) {
                this.directorio.add(registro);
                this.tblDirectory.refresh();
            } else {
                alert.setHeaderText(null);
                alert.setTitle("Error!");
                alert.setContentText("El registro ya existe.");
                alert.show();
            }
        } catch (Exception e) {
            alert.setHeaderText(null);
            alert.setTitle("Error!");
            alert.setContentText(e.toString());
            alert.show();
        }
    }

}
