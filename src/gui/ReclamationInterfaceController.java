package gui;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Reclamation;
import services.ReclamationService;

public class ReclamationInterfaceController implements Initializable {

    @FXML
    private TableView<Reclamation> reclamationsTable;

    @FXML
    private TableColumn<Reclamation, String> descriptionColumn;

    @FXML
    private TableColumn<Reclamation, String> nomColumn;

    @FXML
    private TableColumn<Reclamation, String> prenomColumn;

    @FXML
    private TableColumn<Reclamation, String> emailColumn;

    @FXML
    private TableColumn<Reclamation, String> imageColumn;

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

    private ReclamationService reclamationsService;
    private ObservableList<Reclamation> reclamationsList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        reclamationsService = new ReclamationService();
        reclamationsList = FXCollections.observableArrayList();

        setupTableColumns();
        loadReclamations();
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            searchReclamations(newValue.toLowerCase());
        });
    }

    private void setupTableColumns() {
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        nomColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUser().getNom()));
        prenomColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUser().getPrenom()));
        emailColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUser().getEmail()));
        imageColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUser().getImg()));
    }

    private void loadReclamations() {
        reclamationsList.setAll(reclamationsService.getAll());
        reclamationsTable.setItems(reclamationsList);
    }

    @FXML
    private void handleAjouterButtonAction(ActionEvent event) {
        // Get the current stage (ReclamationInterface stage)
        Stage reclamationInterfaceStage = (Stage) addUserButton.getScene().getWindow();
        reclamationInterfaceStage.close(); // Close the ReclamationInterface stage

        try {
              FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AddReclamation.fxml"));
            Parent root = loader.load();
            AddReclamationController controller=loader.getController();
            controller.setPreviousScene(((Node) event.getSource()).getScene());
            // Get the stage (window) of the current scene
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
/*
 @FXML
private void handleModifierButtonAction(ActionEvent event) {
    try {
        // Check if a row is selected in the table
        Reclamation selectedReclamation = reclamationsTable.getSelectionModel().getSelectedItem();

        if (selectedReclamation != null) {
            // Load the ModifierReclamation interface
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ModifierReclamation.fxml"));
            Parent root = loader.load();
            ModifierReclamationController modifierReclamationController = loader.getController();
            modifierReclamationController.setReclamationData(selectedReclamation); // Pass the selected Reclamation

            Stage modifierReclamationStage = new Stage();
            modifierReclamationStage.setScene(new Scene(root));
            modifierReclamationStage.setTitle("Modifier Reclamation");
            modifierReclamationStage.show();

            // Get the current stage (ReclamationInterface stage)
            Stage reclamationInterfaceStage = (Stage) modifierButton.getScene().getWindow();
            reclamationInterfaceStage.close(); // Close the ReclamationInterface stage
        } else {
            // Display an error message or alert indicating that no item is selected.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Sélectionner une réclamation");
            alert.setContentText("Veuillez sélectionner une réclamation à modifier.");
            alert.showAndWait();
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
*/





    @FXML
    private void handleSupprimerButtonAction(ActionEvent event) {
        Reclamation selectedReclamation = reclamationsTable.getSelectionModel().getSelectedItem();

        if (selectedReclamation != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Confirmation de la suppression");
            alert.setContentText("Êtes-vous sûr de vouloir supprimer cette réclamation?");
            ButtonType yesButton = new ButtonType("Oui");
            ButtonType noButton = new ButtonType("Non");
            alert.getButtonTypes().setAll(yesButton, noButton);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == yesButton) {
                reclamationsService.deleteReclamation(selectedReclamation);
                reclamationsList.remove(selectedReclamation);
            }
        }
    }

    private void searchReclamations(String searchValue) {
        ObservableList<Reclamation> filteredReclamations = FXCollections.observableArrayList();

        for (Reclamation reclamation : reclamationsList) {
            if (reclamation.getDescription().toLowerCase().contains(searchValue) ||
                reclamation.getUser().getNom().toLowerCase().contains(searchValue) ||
                reclamation.getUser().getPrenom().toLowerCase().contains(searchValue) ||
                reclamation.getUser().getEmail().toLowerCase().contains(searchValue) ||
                reclamation.getUser().getImg().toLowerCase().contains(searchValue)) {
                filteredReclamations.add(reclamation);
            }
        }

        reclamationsTable.setItems(filteredReclamations);
    }

    @FXML
    private void handleDeconnexionButtonAction(ActionEvent event) throws IOException {
        // Close the ReclamationInterface
        Stage reclamationInterfaceStage = (Stage) deconnexionButton.getScene().getWindow();
        reclamationInterfaceStage.close();

        // Open the Login interface
        Stage loginStage = new Stage();
        Parent loginRoot = FXMLLoader.load(getClass().getResource("/gui/Login.fxml"));
        Scene loginScene = new Scene(loginRoot);
        loginStage.setScene(loginScene);
        loginStage.setTitle("Login");
        loginStage.show();
    }

    @FXML
    private void handleRefreshButtonAction(ActionEvent event) {
        loadReclamations();
    }
    
    @FXML
private void handleRetourButtonAction(ActionEvent event) {
    try {
        // Close the ReclamationInterface
        Stage reclamationInterfaceStage = (Stage) deconnexionButton.getScene().getWindow();
        reclamationInterfaceStage.close();

        // Open the UserInterface
        Stage userInterfaceStage = new Stage();
        Parent userRoot = FXMLLoader.load(getClass().getResource("/gui/UserInterface.fxml"));
        Scene userScene = new Scene(userRoot);
        userInterfaceStage.setScene(userScene);
        userInterfaceStage.setTitle("User Interface");
        userInterfaceStage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

}
