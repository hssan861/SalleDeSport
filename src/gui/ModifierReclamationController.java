package gui;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import models.Reclamation;
import models.Role;
import models.User;
import services.ReclamationService;

public class ModifierReclamationController implements Initializable {
    
    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private Button retourButton;

    private Reclamation reclamationData;
    private ReclamationService reclamationService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        reclamationService = new ReclamationService();
        reclamationData = new Reclamation(); // Initialize reclamationData
    }

    public void setReclamationData(Reclamation reclamation) {
        this.reclamationData = reclamation;
        if (reclamation != null) {
            // Populate the TextArea with the description
            descriptionTextArea.setText(reclamation.getDescription());
        } else {
            // Handle the case when reclamation is null (optional)
            descriptionTextArea.setText("");
        }
    }

    public Reclamation getReclamationData() {
        // Get user data from the form fields
        reclamationData.setDescription(descriptionTextArea.getText());
        
        return reclamationData;
    }

    @FXML
    private void handleEnregistrerButtonAction(ActionEvent event) {
        try {
            // Create a confirmation dialog
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Modifier la réclamation");
            alert.setContentText("Êtes-vous sûr de vouloir modifier cette réclamation?");

            // Option to confirm the modification
            ButtonType confirmerButton = new ButtonType("Confirmer");
            ButtonType cancelButton = new ButtonType("Annuler");
            alert.getButtonTypes().setAll(confirmerButton, cancelButton);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == confirmerButton) {
                // Get the updated reclamation data from the form fields
                Reclamation updatedReclamation = getReclamationData();
                User user = new User(44, "aziz", "souissi", "aziz12345", Role.Utilisateur, "aziz.souissi@esprit.tn", "fgh", 24);
                updatedReclamation.setUser(user);
                // Update the reclamation in your data source or database
                reclamationService.modifierReclamation(updatedReclamation);

                // Optionally, show a success message
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Modification réussie");
                successAlert.setHeaderText("La réclamation a été modifiée avec succès.");
                successAlert.showAndWait();
            }
        } catch (NumberFormatException e) {
            // Handle exceptions as needed
            e.printStackTrace();
        }
    }

    @FXML
    private void handleRetourButton(ActionEvent event) {
        // Close the ModifierReclamation interface
        Stage modifierReclamationStage = (Stage) retourButton.getScene().getWindow();
        modifierReclamationStage.close();

        try {
            // Load and show the ReclamationInterface
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ReclamationInterface.fxml"));
            Parent root = loader.load();
            ReclamationInterfaceController reclamationInterfaceController = loader.getController();
            Stage reclamationInterfaceStage = new Stage();
            reclamationInterfaceStage.setScene(new Scene(root));
            reclamationInterfaceStage.setTitle("Reclamation Interface");
            reclamationInterfaceStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
