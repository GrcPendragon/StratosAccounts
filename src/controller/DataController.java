/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
public class DataController implements Initializable {

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

    private ObservableList<Directory> directorio;

    private Alert alerta;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initDatos(ObservableList<Directory> directorio, Directory registro) {
        this.directorio = directorio;
        this.registro = registro;
        this.txtPagina.setText(registro.getPagina());
        this.txtUsuario.setText(registro.getUsuario());
        this.txtCorreo.setText(registro.getCorreo());
        this.txtPass.setText(registro.getPass());
    }

    @FXML
    private void clickAccept(ActionEvent event) {
        String pagina = this.txtPagina.getText();
        String usuario = this.txtUsuario.getText();
        String correo = this.txtCorreo.getText();
        String pass = this.txtPass.getText();
        Stage stage;

        try {
            if (isEmptyRegister(pagina, usuario, correo, pass)) {
                alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setHeaderText(null);
                alerta.setTitle("Faltan datos");
                alerta.setContentText("Todos los campos son necesarios.");
                alerta.show();
            } else {
                if (this.registro != null) {
                    //Modificar
                    this.registro.setPagina(pagina);
                    this.registro.setUsuario(usuario);
                    this.registro.setCorreo(correo);
                    this.registro.setPass(pass);
                    
                    stage = (Stage) this.btnAceptar.getScene().getWindow();
                    stage.close();
                } else {
                    //Ingresar
                    registro = new Directory(pagina, usuario, correo, pass);
                    if (this.directorio.contains(registro)) {
                        alerta = new Alert(Alert.AlertType.ERROR);
                        alerta.setHeaderText(null);
                        alerta.setTitle("Error!");
                        alerta.setContentText("El registro ya existe.");
                        alerta.show();
                    } else {
                        this.directorio.add(registro);
                        stage = (Stage) this.btnAceptar.getScene().getWindow();
                        stage.close();
                    }
                }
            }

        } catch (Exception e) {
            alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setTitle("Error!");
            alerta.setContentText(e.getMessage());
            alerta.show();
        }
    }

    @FXML
    private void clickCancel(ActionEvent event) {
        Stage stage = (Stage) this.btnAceptar.getScene().getWindow();
        stage.close();
    }

    public ObservableList<Directory> getDirectorio() {
        return directorio;
    }

    public void setDirectorio(ObservableList<Directory> directorio) {
        this.directorio = directorio;
    }

    public boolean isEmptyRegister(String pagina, String usuario, String correo, String pass) {
        if (pagina.isEmpty() || usuario.isEmpty() || correo.isEmpty() || pass.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
