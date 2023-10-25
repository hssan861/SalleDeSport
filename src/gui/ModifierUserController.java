package gui;
import java.io.IOException;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import models.Role; // Import the Role enum from your models package
import models.User;
import services.UserService;
import util.Utils;

public class ModifierUserController {

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
    @FXML
    private ComboBox<Role> roleComboBox; // ComboBox for selecting user's role

    private User userData;
    private UserService userService;
        @FXML
    private Button retourButton;

    public ModifierUserController() {
        userService = new UserService();
    }

    public void setUserData(User user) {
        this.userData = user;
        // Populate the fields with user data
        nomField.setText(user.getNom());
        prenomField.setText(user.getPrenom());
        passwordField.setText(user.getMdp());
        emailField.setText(user.getEmail());
        ageField.setText(String.valueOf(user.getAge()));
        imageField.setText(user.getImg());

        // Populate the ComboBox with all Role values
        roleComboBox.setItems(FXCollections.observableArrayList(Role.values()));

        // Set the selected role in the ComboBox to match the user's role
        roleComboBox.setValue(user.getRole());
    }

    public User getUserData() {
        // Get user data from the form fields
        userData.setNom(nomField.getText());
        userData.setPrenom(prenomField.getText());
        userData.setMdp(Utils.encryptString(passwordField.getText()));
        userData.setEmail(emailField.getText());
        userData.setAge(Integer.parseInt(ageField.getText()));
        userData.setImg(imageField.getText());

        // Get the selected role from the ComboBox
        userData.setRole(roleComboBox.getValue());

        return userData;
    }

@FXML
private void handleEnregistrerButtonAction(ActionEvent event) {
    try {
        // Create a confirmation dialog
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Modifier l'utilisateur");
        alert.setContentText("Êtes-vous sûr de vouloir modifier cet utilisateur?");

        // Option to confirm the modification
        ButtonType confirmerButton = new ButtonType("Confirmer");
        ButtonType cancelButton = new ButtonType("Annuler");
        alert.getButtonTypes().setAll(confirmerButton, cancelButton);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == confirmerButton) {
            // Get the updated user data from the form fields
            User updatedUser = getUserData();

            // Update the user in your data source or database
            userService.modifierUser(updatedUser);

            // Optionally, show a success message
            Alert successAlert = new Alert(AlertType.INFORMATION);
            successAlert.setTitle("Modification réussie");
            successAlert.setHeaderText("L'utilisateur a été modifié avec succès.");
            successAlert.showAndWait();
        }
    } catch (NumberFormatException e) {
        // Handle the case where the age field cannot be parsed as an integer
        // You can display an error message or handle it as needed
        // For example, show an alert or set a validation message on the age field.
        e.printStackTrace(); // Print the exception for debugging
    }
}

     @FXML
    private void handleRetourButton(ActionEvent event) {
        // Close the AddUser interface
        Stage modifierUserStage = (Stage) retourButton.getScene().getWindow();
        modifierUserStage.close();

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
