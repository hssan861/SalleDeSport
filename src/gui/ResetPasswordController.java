/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import services.UserService;
import util.MailUtil;
import util.Utils;
/**
 * FXML Controller class
 *
 * @author USER
 */
public class ResetPasswordController implements Initializable {
    String code;
   
    @FXML
    private TextField codeTextField;
    @FXML
    private Button verifyCodeButton;
 @FXML
    private TextField email;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
  public String generateCode() {
    int leftLimit = 97; // letter 'a'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = 6;
    Random random = new Random();

    String generatedString = random.ints(leftLimit, rightLimit + 1)
      .limit(targetStringLength)
      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
      .toString();

    return(generatedString);
}
   @FXML
    private void MdpOublie(ActionEvent event){
        if(!Utils.isValidEmail(email.getText())){
            //INVALID EMAIL FORMAT
            //TODO:SHOW ALERT
            Alert a = new Alert(Alert.AlertType.ERROR, "Email  invalide!", ButtonType.OK);
            a.showAndWait();
            return;
        }
        
        UserService uService=new UserService();
        if(!uService.UserExists(email.getText())){
            //EMAIL DOESNT EXIST IN THE DATABASE
            Alert a = new Alert(Alert.AlertType.ERROR, "Email  n'existe pas!", ButtonType.OK);
            a.showAndWait();
            //TODO:SHOW ALERT
            return;
        }
        
        code=generateCode();
        if(MailUtil.sendPasswordResetMail(email.getText(), code)){
            codeTextField.setEditable(true);
            verifyCodeButton.setDisable(false);
        }else{
            //INVALID EMAIL
            //TODO:SHOW ALERT
           
        }
        
    }
    @FXML
    private void verifyCode(ActionEvent event){
        if(codeTextField.getText().equals(code)){
            Stage setNewPasswordStage = new Stage();
                 Parent setNewPasswordInterface;
            try {
                setNewPasswordInterface = FXMLLoader.load(getClass().getResource("SetNewPassword.fxml"));
                Scene setNewPasswordScene = new Scene(setNewPasswordInterface);
                    setNewPasswordStage.setScene(setNewPasswordScene);
                    Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    setNewPasswordStage.setUserData(email.getText());
                    currentStage.close();
                
                    setNewPasswordStage.show();
                    
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            //WRONG CODE
            //TODO:SHOW ALERT
            Alert a = new Alert(Alert.AlertType.ERROR, "Code invalide", ButtonType.OK);
            a.showAndWait();
        }
    }
}
