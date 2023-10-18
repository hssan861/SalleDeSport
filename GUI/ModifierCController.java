/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.GUI;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import pi.entities.Commande;
import pi.services.serviceCommande;

/**
 * FXML Controller class
 *
 * @author hama
 */
public class ModifierCController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
         public ModifierCController(){
        this.sc = new serviceCommande();
    }
    private serviceCommande sc; 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
    @FXML
    private TextField adresseField;

    @FXML
    private TextField idCommandeField;

    @FXML
    private ComboBox<?> produitComboBox;

    @FXML
    private DatePicker tfdate;

    @FXML
    private TextField typeField;

    @FXML
    void modifierCommande(ActionEvent event) {
// Créez un objet Commande pour stocker les données de mise à jour
    Commande commande = new Commande();

    // Récupérez les données de l'interface utilisateur
    LocalDate selectedDate = tfdate.getValue();
    String adresse = adresseField.getText();
    String type = typeField.getText();

    try {
        int commandeId = Integer.parseInt(this.idCommandeField.getText());

        if (selectedDate == null || adresse.isEmpty() || type.isEmpty()) {
            // Si l'une des données obligatoires est manquante, affichez un message d'erreur.
            showErrorMessage("Veuillez remplir tous les champs obligatoires.");
            return; // Sortez de la méthode sans effectuer la mise à jour.
        }

        // Convertissez la date sélectionnée en un objet Date
        Date date = Date.from(selectedDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        // Remplissez l'objet Commande avec les données de mise à jour
        commande.setId(commandeId);
        // Assurez-vous de récupérer l'ID du produit lié
        commande.setDate(date);
        commande.setAdresse(adresse);
        commande.setType(type);

        // Ajoutez la logique de mise à jour (appel à la méthode de service)
        Boolean result = this.sc.modifier(commande);

        // Affichez un message en fonction du résultat de la mise à jour
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);

        if (result) {
            alert.setContentText("Commande mise à jour avec succès !");
        } else {
            alert.setContentText("Échec de la mise à jour de la commande.");
        }

        alert.showAndWait();
    } catch (NumberFormatException e) {
        // Afficher un message d'erreur si l'ID de la commande n'est pas un entier valide
        showErrorMessage("ID de commande invalide.");
    }
}

private void showErrorMessage(String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur de saisie");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
    
  
    @FXML
void findCommande(ActionEvent event) {
    // Obtenez l'identifiant de la commande à partir de l'interface utilisateur
    String commandeIdText = idCommandeField.getText();
    
    if (commandeIdText.isEmpty()) {
        // Si le champ d'ID de la commande est vide, affichez un message d'erreur.
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez entrer un ID de commande.");
        alert.showAndWait();
        return; // Sortez de la méthode sans chercher la commande.
    }

    int commandeId = Integer.parseInt(commandeIdText);

    // Utilisez le service Commande pour trouver la commande par son ID
    Commande commande = this.sc.findCommandeById(commandeId);

    if (commande != null) {
        // Remplissez les champs de l'interface utilisateur avec les données de la commande
        adresseField.setText(commande.getAdresse());
        typeField.setText(commande.getType());
        
        // Vous devrez ajouter la logique pour la date ici
        // Assurez-vous que la date est correctement formatée avant de l'assigner à la date de la commande.

        // Vous devrez également gérer les ID utilisateur et produit liés à la commande.

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Commande trouvée avec succès.");
        alert.showAndWait();
    } else {
        // Si la commande n'est pas trouvée, affichez un message d'erreur.
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Commande introuvable. Veuillez vérifier l'ID de la commande.");
        alert.showAndWait();
    }
}

    
}
