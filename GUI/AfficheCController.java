/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.GUI;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pi.entities.Commande;
import pi.entities.Produit;
import pi.services.serviceCommande;
import pi.services.serviceProduit;

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
    private TableView<Commande> commandesTable;

    @FXML
    private TableColumn<Commande, Date> dateCommandeColumn;

    @FXML
    private TableColumn<Commande, Integer> idCommandeColumn;

    @FXML
    private TableColumn<Commande, Produit> produitColumn;

    @FXML
    private Button suupC;

    @FXML
    private TableColumn<Commande, String> typeColumn;

    @FXML
    void afficheeC(ActionEvent event) {
 ArrayList<Commande> commandes = (ArrayList) this.sc.afficher(); // You need to implement this method

    // Set cell value factories for each column
    idCommandeColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
    produitColumn.setCellValueFactory(new PropertyValueFactory<>("produit")); // Assuming there's a 'produit' property in Commande
    dateCommandeColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
    adresseColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));
    typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

    // Create an ObservableList from the list of Commande objects
    ObservableList<Commande> data = FXCollections.observableArrayList(commandes);

    // Set the items of the TableView
    commandesTable.setItems(data);
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






    }

