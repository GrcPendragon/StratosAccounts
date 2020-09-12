/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Directory;

/**
 * FXML Controller class
 *
 * @author Fausto
 */
public class InsertDataController implements Initializable {

    @FXML
    private Button btnAceptar;
    @FXML
    private Button btnCancelar;
    @FXML
    private PasswordField txtPass;
    @FXML
    private TextField txtPagina;
    @FXML
    private TextField txtUsuario;
    @FXML
    private TextField txtCorreo;
    
    private Directory registro;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void click(ActionEvent event) {
        String pagina = this.txtPagina.getText();
        String usuario = this.txtUsuario.getText();
        String correo = this.txtCorreo.getText();
        String pass = this.txtPass.getText();
        
        registro = new Directory(pagina, usuario, correo, pass);
        
        Stage stage = (Stage) this.btnAceptar.getScene().getWindow();
        stage.close();
    }

    public Directory getRegistro() {
        return registro;
    }
}
