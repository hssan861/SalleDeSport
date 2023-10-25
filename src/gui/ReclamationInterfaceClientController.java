package gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Reclamation;
import models.User;
import services.ReclamationService;

public class ReclamationInterfaceClientController implements Initializable {

    @FXML
    private Button addUserButton;
    @FXML
    private Button retoutbutton;

    @FXML
    private ListView<Reclamation> listview;
     @FXML
    private TextField searchField;

    private ReclamationService reclamationsService;
    private ObservableList<Reclamation> reclamationsList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reclamationsService = new ReclamationService();
        reclamationsList = FXCollections.observableArrayList();
        loadReclamations();
         searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            searchReclamations(newValue.toLowerCase());
        });

        listview.setCellFactory(param -> new ListCell<Reclamation>() {
            @Override
            protected void updateItem(Reclamation item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    try {
                        ImageView userImageView = new ImageView();
                     File userImageFile = new File(item.getUser().getImg());
                        if (userImageFile.exists()) {
                            Image userImage = new Image(userImageFile.toURI().toString());
                            userImageView.setImage(userImage);
                            userImageView.setFitWidth(100);
                            userImageView.setPreserveRatio(true);
                        } else {
                            Image defaultImage = new Image(item.getUser().getImg());
                            userImageView.setImage(defaultImage);
                            userImageView.setFitWidth(100);
                            userImageView.setPreserveRatio(true);
                        }

                        String userInformation = "Nom: " + item.getUser().getNom() + "\n"
                                + "Prénom: " + item.getUser().getPrenom() + "\n"
                                + "Email: " + item.getUser().getEmail();

                        VBox userVBox = new VBox(userImageView, new Label(userInformation));
                        userVBox.setSpacing(5);

                        String reclamationInfo = "Description: " + item.getDescription();
                        VBox vbox = new VBox(userVBox, new Label(reclamationInfo));
                        vbox.setSpacing(10);

                        setGraphic(vbox);
                    } catch (Exception e) {
                        e.printStackTrace();
                        setGraphic(null);
                    }
                }
            }
        });
    
         }
                 
    private void loadReclamations() {
        reclamationsList.setAll(reclamationsService.getAll());
        listview.setItems(reclamationsList);
    }

    @FXML
    private void handleAjouterButtonAction(ActionEvent event) {
        Stage reclamationInterfaceStage = (Stage) addUserButton.getScene().getWindow();
        reclamationInterfaceStage.close();

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

        listview.setItems(filteredReclamations);
    }
         @FXML
    private void handleRefreshButtonAction(ActionEvent event) {
        loadReclamations();
    }
      @FXML
    private void handleRetourButton(ActionEvent event) {
        // Close the AddUser interface
        Stage reclamationInterfaceClientStage = (Stage) retoutbutton.getScene().getWindow();
        reclamationInterfaceClientStage.close();

        try {
            // Load and show the UserInterface
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/EspaceClient.fxml"));
            Parent root = loader.load();
            EspaceClientController espaceClientController = loader.getController();
            Stage espaceClientStage = new Stage();
            espaceClientStage.setScene(new Scene(root));
            espaceClientStage.setTitle("Espace Client");
            espaceClientStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
