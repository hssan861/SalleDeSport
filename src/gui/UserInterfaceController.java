package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import models.User;
import services.UserService;

public class UserInterfaceController implements Initializable {

    @FXML
    private TableView<User> usersTable;

    @FXML
    private TableColumn<User, String> nomColumn;

    @FXML
    private TableColumn<User, String> prenomColumn;

    @FXML
    private TableColumn<User, String> passwordColumn;

    @FXML
    private TableColumn<User, String> emailColumn;

    @FXML
    private TableColumn<User, String> imageColumn;

    @FXML
    private TableColumn<User, Integer> ageColumn;

    @FXML
    private TableColumn<User, String> roleColumn; // New "Role" column

    @FXML
    private Button addUserButton;

    @FXML
    private Button deleteUserButton;

    @FXML
    private Button refreshButton;

    @FXML
    private Button modifierButton;

    @FXML
    private TextField searchField;
    @FXML
private Button deconnexionButton;

    private UserService usersService;
    private ObservableList<User> usersList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        usersService = new UserService();
        usersList = FXCollections.observableArrayList();

        setupTableColumns();
        loadUsers();
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            searchUsers(newValue.toLowerCase());
        });
    }

    private void setupTableColumns() {
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        imageColumn.setCellValueFactory(new PropertyValueFactory<>("img"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

        // Add the "Role" column
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
    }

    private void loadUsers() {
        usersList.setAll(usersService.getAll());
        usersTable.setItems(usersList);
    }
@FXML
private void handleAjouterButtonAction(ActionEvent event) {
    // Get the current stage (UserInterface stage)
    Stage userInterfaceStage = (Stage) addUserButton.getScene().getWindow();
    userInterfaceStage.close(); // Close the UserInterface stage

    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AddUser.fxml"));
        Parent root = loader.load();
        Stage addUserStage = new Stage();
        addUserStage.setScene(new Scene(root));
        addUserStage.setTitle("Add User");
        addUserStage.show();
    } catch (IOException e) {
        System.out.println(e.getMessage());
    }
}

    @FXML
    private void handleModifierButtonAction(ActionEvent event) {
        User selectedUser = usersTable.getSelectionModel().getSelectedItem();
           if (selectedUser != null) {
            // Get the current stage (UserInterface stage)
             Stage userInterfaceStage = (Stage) modifierButton.getScene().getWindow();
             userInterfaceStage.close(); // Close the UserInterface stage
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ModifierUser.fxml"));
                Parent root = loader.load();
                ModifierUserController modifierController = loader.getController();
                modifierController.setUserData(selectedUser);
                Scene scene = new Scene(root);
                Stage modifierStage = new Stage();
                modifierStage.setScene(scene);
                modifierStage.setTitle("Modifier User");
                modifierStage.showAndWait(); // Use showAndWait to block until the user closes the window

                // Get the updated user data from the ModifierUserController
                User updatedUser = modifierController.getUserData();

                // Update the user in your data source or database
                usersService.modifierUser(updatedUser);

                // Refresh the user list to reflect changes
                loadUsers();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @FXML
    private void handleSupprimerButtonAction(ActionEvent event) {
        User selectedUser = usersTable.getSelectionModel().getSelectedItem();

        if (selectedUser != null) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Confirmation de la suppression");
            alert.setContentText("Êtes-vous sûr de vouloir supprimer cet utilisateur?");
            ButtonType yesButton = new ButtonType("Oui");
            ButtonType noButton = new ButtonType("Non");
            alert.getButtonTypes().setAll(yesButton, noButton);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == yesButton) {
                usersService.deleteUser(selectedUser);
                usersList.remove(selectedUser);
            }
        }
    }
    
    

    @FXML
    private void handleRefreshButtonAction(ActionEvent event) {
        loadUsers();
    }
private void searchUsers(String searchValue) {
        ObservableList<User> filteredUsers = FXCollections.observableArrayList();

        for (User user : usersList) {
            if (user.getNom().toLowerCase().contains(searchValue) ||
                user.getPrenom().toLowerCase().contains(searchValue) ||
                user.getMdp().toLowerCase().contains(searchValue) ||
                user.getEmail().toLowerCase().contains(searchValue) ||
                user.getImg().toLowerCase().contains(searchValue) ||
                String.valueOf(user.getAge()).contains(searchValue)||
               (String.valueOf(user.getRole()).toLowerCase().contains(searchValue)))     {
                filteredUsers.add(user);
            }
        }

        usersTable.setItems(filteredUsers);
    }



@FXML
private void handleDeconnexionButtonAction(ActionEvent event) throws IOException {
    // Close the UserInterface
    Stage userInterfaceStage = (Stage) deconnexionButton.getScene().getWindow();
    userInterfaceStage.close();

    // Open the Login interface
    Stage loginStage = new Stage();
    Parent loginRoot = FXMLLoader.load(getClass().getResource("Login.fxml"));
    Scene loginScene = new Scene(loginRoot);
    loginStage.setScene(loginScene);
    loginStage.show();
}


@FXML
private void handleVoirReclamationsButtonAction(ActionEvent event) {
    // Close the UserInterface stage
    Stage userInterfaceStage = (Stage) usersTable.getScene().getWindow();
    userInterfaceStage.close();

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
        System.out.println(e.getMessage());
    }
}


}
