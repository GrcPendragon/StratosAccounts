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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
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
    private Button btnModificar;
    @FXML
    private Button btnEliminar;
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DataView.fxml"));
            Parent root = loader.load();
            DataController insertar = loader.getController();
            insertar.setDirectorio(directorio);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            
            
            this.directorio = insertar.getDirectorio();
            this.tblDirectory.refresh();
            
        } catch (Exception e) {
            alert.setHeaderText(null);
            alert.setTitle("Error!");
            alert.setContentText(e.toString());
            alert.show();
        }
    }

    @FXML
    private void selected(MouseEvent event) {
        boolean desactivado = true;
        if (tblDirectory.getSelectionModel().getSelectedItem() != null) {
            desactivado = false;
        }
        btnModificar.setDisable(desactivado);
        btnEliminar.setDisable(desactivado);
    }

    @FXML
    private void clickUpdate(ActionEvent event) {
        try {
            Directory registro = this.tblDirectory.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DataView.fxml"));
            Parent root = loader.load();
            DataController modificar = loader.getController();
            modificar.initDatos(directorio, registro);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            
            this.tblDirectory.refresh();
        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR,e.getMessage());
        }
    }

    @FXML
    private void clickDelete(ActionEvent event) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION,
                "¿Esta seguro que desea eliminar este registro?",ButtonType.YES,ButtonType.NO);
        alerta.setHeaderText(null);
        alerta.showAndWait();
        Directory registro = this.tblDirectory.getSelectionModel().getSelectedItem();
        if (alerta.getResult() == ButtonType.YES) {
            this.directorio.remove(registro);
            this.tblDirectory.refresh();
        }
    }

}
