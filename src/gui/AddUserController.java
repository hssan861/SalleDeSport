package gui;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import models.User;
import services.UserService;
import models.Role;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Button;
import util.Utils;

public class AddUserController {
    @FXML
    private ComboBox<Role> roleComboBox;

    @FXML
    private TextField nomField;
    @FXML
    private TextField prenomField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField imageField;
    @FXML
    private TextField ageField;

    private UserService userService;

    @FXML
    private Button retourButton;

    @FXML
    private void initialize() {
        userService = new UserService();
        ObservableList<Role> roles = FXCollections.observableArrayList(Role.values());
        roleComboBox.setItems(roles);
    }

    @FXML
    private void handleAddUser(ActionEvent event) {
         if (nomField.getText().isEmpty() ||prenomField.getText().isEmpty() ||emailField.getText().isEmpty() || passwordField.getText().isEmpty()||  imageField.getText().isEmpty() || ageField.getText().isEmpty()  ) {
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
        try {
            // Handle user input and add the user to the database
            String nom = nomField.getText();
            String prenom = prenomField.getText();
            String password =Utils.encryptString(passwordField.getText());
            Role role = roleComboBox.getValue();
            String email = emailField.getText();
            String image = imageField.getText();
            int age = Integer.parseInt(ageField.getText());
           
            User user = new User(nom, prenom, password, role, email, image, age);
            if(!userService.ajouterUser(user)){
                return;
            }

            // Clear input fields
            nomField.clear();
            prenomField.clear();
            passwordField.clear();
            emailField.clear();
            imageField.clear();
            ageField.clear();

            // Show a confirmation message
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "User added successfully!", ButtonType.OK);
            alert.showAndWait();
        } catch (NumberFormatException e) {
            // Handle invalid age input
            //Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid age input. Please enter a valid number.", ButtonType.OK);
           // alert.showAndWait();
        }
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


    @FXML
    private void handleRetourButton(ActionEvent event) {
        // Close the AddUser interface
        Stage addUserStage = (Stage) retourButton.getScene().getWindow();
        addUserStage.close();

        try {
            // Load and show the UserInterface
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/UserInterface.fxml"));
            Parent root = loader.load();
            UserInterfaceController userInterfaceController = loader.getController();
            Stage userInterfaceStage = new Stage();
            userInterfaceStage.setScene(new Scene(root));
            userInterfaceStage.setTitle("User Interface");
            userInterfaceStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
