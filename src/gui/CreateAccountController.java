/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import java.io.File;
import models.User;
import services.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Role;
import util.Utils;

 
/**
 * FXML Controller class
 *
 * @author wassi
 */
public class CreateAccountController implements Initializable {
    @FXML
    private TextField nomField;
    @FXML
    private TextField prenomField;
     @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    
    @FXML
    private TextField imageField;
    @FXML
    private TextField ageField;
    @FXML
    private UserService usersService;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
    @FXML 
        void switchButton1(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/Login.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
     private void Registration(ActionEvent event) throws IOException, SQLException{
       if (nomField.getText().isEmpty() ||prenomField.getText().isEmpty() ||emailField.getText().isEmpty() || passwordField.getText().isEmpty()||    imageField.getText().isEmpty() || ageField.getText().isEmpty()  ) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Aucun champ vide n'est accepté", ButtonType.OK);
            a.showAndWait();
        } else if (!Validateemail()) {
           // Alert a = new Alert(Alert.AlertType.ERROR, "Email  invalide!", ButtonType.OK);
           // a.showAndWait();
        
        }
        else if (!Validatemotdepasse()) {
           // Alert a = new Alert(Alert.AlertType.ERROR, "Le mot de passe doit être au minimum 8 caractéres!", ButtonType.OK);
            //a.showAndWait();
        }
        else {
             UserService su = new  UserService();
            User u = new User(nomField.getText(),prenomField.getText(),Utils.encryptString(passwordField.getText()) ,Role.Utilisateur,emailField.getText(),imageField.getText(), Integer.parseInt(ageField.getText()) ) {} ;
            if(!su.ajouterUser(u)){
                return;
            }
            List<User> all = su.getAll();
            nomField.clear();
            prenomField.clear();
            emailField.clear();
            passwordField.clear();  
            imageField.clear();
            ageField.clear();         
            Alert a = new Alert(Alert.AlertType.INFORMATION, "You have been registred succesfully !", ButtonType.OK);
            a.showAndWait();
            
                Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                Scene LoginpageScene = new Scene(root);
                Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                 appStage.setScene(LoginpageScene);
                appStage.show();
        }
        
    } 
    /*********************************************email API***************************************************************************/    

  
   
    /*********************************************CONTROLE DE SAISIE***************************************************************************/    
     
@FXML
public void ChosePhoto(ActionEvent event) {
    Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Open Resource File");

    // Show the file dialog to select an image file
    File selectedFile = fileChooser.showOpenDialog(appStage);

    if (selectedFile != null) {
        // Set the file path to the imageField TextField
        imageField.setText(selectedFile.toURI().toString());
    }
}

     
private boolean Validateemail() {
    Pattern p = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    Matcher m = p.matcher(emailField.getText());
    if (m.find() && m.group().equals(emailField.getText())) {
        emailField.setEffect(null);
        return true;
    } else {
        Alert a = new Alert(Alert.AlertType.ERROR, "Email Invalide", ButtonType.OK);
        a.showAndWait();
        return false;
    }
}

     
     

   
    

    private boolean Validatemotdepasse() {
        Pattern p = Pattern.compile("[a-zA-Z_0-9]+");
        Matcher m = p.matcher(passwordField.getText());
                if (((passwordField.getText().length() > 7))
                && (m.find() && m.group().equals(passwordField.getText()))) {
            passwordField.setEffect(null);
            return true;
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Le mot de passe doit être au minimum 8 caractéres!", ButtonType.OK);
            a.showAndWait();
            return false;
        }
        
    }



    
}
