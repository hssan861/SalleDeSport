/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.User;
import services.UserService;
import util.Utils;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class SetNewPasswordController implements Initializable {
@FXML
    private TextField newPasswordField;

@FXML
private Button submitButton;
        /**
         * Initializes the controller class.
         */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private TextField confirmPasswordField;
    @FXML
    private void setPassword(ActionEvent event){
        if(!isPasswordValid(newPasswordField.getText())){
            // INVALID PASSWORD
            // TODO: SHOW ALERT
            
            return;
        }
        if(!newPasswordField.getText().equals(confirmPasswordField.getText())){
           Alert a = new Alert(Alert.AlertType.ERROR, "Mot de passe invalide", ButtonType.OK);
            a.showAndWait();
        }
        
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        String email=(String) stage.getUserData();
        
        UserService uService=new UserService();
        int userId=uService.getIdByEmail(email);
        uService.changePasswordById(userId, Utils.encryptString(newPasswordField.getText()));
        stage.close();
        //
          try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Login.fxml"));
                Parent root = loader.load();
                LoginController loginController = loader.getController();
             
                Scene scene = new Scene(root);
                Stage loginStage = new Stage();
                loginStage.setScene(scene);
                loginStage.setTitle("Login");
                loginStage.showAndWait(); // Use showAndWait to block until the user closes the window

             
               
            } catch (IOException e){
            System.out.println(e.getMessage());
            }
    }
    private boolean isPasswordValid(String password) {
        Pattern p = Pattern.compile("[a-zA-Z_0-9]+");
        Matcher m = p.matcher(password);
                if (((password.length() > 7))
                && (m.find() && m.group().equals(password))) {
         
            return true;
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Le mot de passe doit être au minimum 8 caractéres!", ButtonType.OK);
            a.showAndWait();
            return false;
        }
        
    }
     @FXML
    private void Annuler(ActionEvent event){
        try {
            // Load the Login.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = loader.load();

            // Get the stage (window) of the current scene
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        } 
        
        
}
}
