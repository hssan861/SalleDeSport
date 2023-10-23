package gui;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import models.User;
import models.Login;
public class EspaceClientController implements Initializable {
    // ... (your existing code)

    User login;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Initialization code if needed
        login=User.getInstance();
    }
    
     @FXML
    private Label welcomeLabel;
        private User currentUser; // Field to store the current user

        public void setCurrentUser(User user) {
        // Here, you can access and use the user data within this controller
        // For example, display the user's name in a label
        this.currentUser=user;
        String userName = user.getNom() + " " + user.getPrenom();
        welcomeLabel.setText(userName); // Assuming nameLabel is a JavaFX Label
    }
    @FXML
    private void ajoutReclamation(ActionEvent event) {
        if(currentUser!=null){
                   System.out.println(currentUser.getEmail());

        }
        try {
            // Load the AddReclamation.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddReclamation.fxml"));
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

    @FXML
    private void deconnexion(ActionEvent event) {
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
