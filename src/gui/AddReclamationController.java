package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import models.User;
import services.ReclamationService;
import models.Reclamation;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import models.Login;
import models.Role;

public class AddReclamationController implements Initializable {
    private EspaceClientController espaceClientController;
    private User currentUser;
    User login;
    @FXML
    private TextArea descriptionTextArea;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialization code if needed
        login=User.getInstance();
    }


    @FXML
    private void ajouterReclamation(ActionEvent event) {
        ReclamationService rs = new ReclamationService();
        try {
            // Handle user input and add the user to the database
            String Description = descriptionTextArea.getText();
            Reclamation rec = new Reclamation(Description,login);
            rs.ajouterReclamation(rec);

            // Clear input fields
            descriptionTextArea.clear();

            // Show a confirmation message
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Reclamation added successfully!", ButtonType.OK);
            alert.showAndWait();
        } catch (NumberFormatException e) {
            // Handle invalid age input
        }
    }

    @FXML
    private void retourEspaceClient(ActionEvent event) {
        // Close the current AddReclamation stage
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();

        try {
            // Load the EspaceClient.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EspaceClient.fxml"));
            Parent root = loader.load();

            // Create a new stage for the EspaceClient
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);

            // Show the EspaceClient stage
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur d'ajout de réclamation");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
