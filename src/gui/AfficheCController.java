/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Commande;
import models.Produit;
import services.serviceCommande;
import services.serviceProduit;

/**
 * FXML Controller class
 *
 * @author hama
 */
public class AfficheCController implements Initializable {

    /**
     * Initializes the controller class.
     * 
     */
     public AfficheCController(){
        this.sc = new serviceCommande();
    }
    private serviceCommande sc; 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // TODO
    }    
    
    @FXML
    private TableColumn <Commande, String > adresseColumn;

    @FXML
    private Button affc;

    @FXML
    private ListView<Commande> commandesTable;

    @FXML
    private TableColumn<Commande, Date> dateCommandeColumn;

    @FXML
    private TableColumn<Commande, String> typeColumn;

    @FXML
    void afficheeC(ActionEvent event) {
 ArrayList<Commande> commandes = (ArrayList) this.sc.afficher(); // You need to implement this method
        // Créez une ObservableList à partir de votre liste de produits
        ObservableList<Commande> data = FXCollections.observableArrayList(commandes);

        // Configurez le ListView pour afficher les produits
        commandesTable.setItems(data);

        // Définissez un cell factory personnalisé si vous souhaitez personnaliser l'affichage des éléments dans le ListView.
        commandesTable.setCellFactory(param -> new ListCell<Commande>() {
            @Override
            protected void updateItem(Commande item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    // Créez un affichage personnalisé pour la cellule
                    setText("Adresse: " + item.getAdresse() + "\n" +
                            "Date: " + item.getDate() + "\n" +
                            "Type: " + item.getType() );

                    }}});

}
    
       private void showAlert(Alert.AlertType alertType, String title, String content) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
}
    

    @FXML
    void supprimerC(ActionEvent event) {
// Get the selected Commande from the TableView
    Commande selectedCommande = commandesTable.getSelectionModel().getSelectedItem();

    if (selectedCommande != null) {
        // Assuming you have a service to delete a Commande
        boolean result = sc.supprimer(selectedCommande);

        if (result) {
            // Remove the selected item from the TableView
            commandesTable.getItems().remove(selectedCommande);
            // Optional: Show a confirmation message to the user
            showAlert(AlertType.INFORMATION, "Commande Deleted", "Commande has been deleted.");
        } else {
            showAlert(AlertType.ERROR, "Error", "Failed to delete the Commande.");
        }
    } else {
        showAlert(AlertType.WARNING, "No Selection", "Please select a Commande to delete.");
    }
}
    private void showInfoMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();}

    public void Options(ActionEvent actionEvent) throws IOException {
        Commande selectedItem = commandesTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            // Créez une alerte (popup) avec des boutons Supprimer et Modifier
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sélection d'élément");
            alert.setHeaderText("Que voulez-vous faire avec cet élément ?");
            alert.setContentText("Sélectionnez une option :");

            ButtonType buttonTypeDelete = new ButtonType("Supprimer");
            ButtonType buttonTypevoir = new ButtonType("Afficher");

            alert.getButtonTypes().setAll(buttonTypeDelete, buttonTypevoir, ButtonType.CANCEL);

            // Affichez l'alerte et attendez la réponse de l'utilisateur
            Optional<ButtonType> result = alert.showAndWait();

            // Traitez la réponse de l'utilisateur
            if (result.isPresent()) {
                if (result.get() == buttonTypeDelete) {
                    this.sc.supprimer(selectedItem);
                    commandesTable.getItems().remove(selectedItem);
                    showInfoMessage("Commande supprimé avec succès !");
                    // Vous pouvez ajouter ici la logique pour supprimer l'élément de votre modèle de données
                } else if (result.get() == buttonTypevoir) {
                    // Vous pouvez ajouter ici la logique pour ouvrir l'élément en mode édition
                    FXMLLoader fxl=new FXMLLoader();
                    fxl.setLocation(getClass().getResource("AfficheOneCommande.fxml"));
                    Parent root=fxl.load();
                    AfficheOneCommande c=fxl.getController();
                    c.setdatacommande(selectedItem);
                    commandesTable.getScene().setRoot(root);
                }
            }
        }
    }

    public void Retour(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("ChoosePrd.fxml"));
        Parent root=loader.load();
        commandesTable.getScene().setRoot(root);
    }
}

