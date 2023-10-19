/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi_salle_de_sport.GUI;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pi_salle_de_sport.Entities.Activities;
import pi_salle_de_sport.Entities.Categorie;
import pi_salle_de_sport.Entities.Reservation;
import pi_salle_de_sport.Entities.User;
import pi_salle_de_sport.Services.ServiceActivities;
import pi_salle_de_sport.Services.ServiceReservation;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AfficherReservationFXMLController implements Initializable {
  @FXML
    private TextField ActiviteRech;

    @FXML
    private Button aaaa;

    @FXML
    private ListView<Reservation> lvactivite;
    /**
     * Initializes the controller class.
     */
    
    ServiceReservation sa =new ServiceReservation();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         // Initialiser la liste des activités
      ObservableList<Reservation> items = FXCollections.observableArrayList ()  ;
        items.addAll(sa.afficher());
        lvactivite.setItems(items);
    }    
     @FXML
void supprimerReservation(ActionEvent event) {
    // Obtenir l'activité sélectionnée depuis la ListView
    Reservation activiteSelectionnee = lvactivite.getSelectionModel().getSelectedItem();
    if (activiteSelectionnee != null) {
        // Afficher une boîte de dialogue de confirmation
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation de la suppression");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Êtes-vous sûr de vouloir supprimer cette reservation ?");

        // Attendre la réponse de l'utilisateur
        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Utilisateur a confirmé la suppression
            ServiceReservation ServiceReservation = new ServiceReservation();
            boolean suppressionReussie = ServiceReservation.supprimer(activiteSelectionnee);

            if (suppressionReussie) {
                lvactivite.getItems().remove(activiteSelectionnee);
            } else {
                // Afficher un message d'erreur si la suppression a échoué
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Erreur lors de la suppression de l'activité sélectionnée.");
                alert.showAndWait();
            }
        }
    } else {
        // Afficher un message si aucune activité n'a été sélectionnée
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Avertissement");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner une activité à supprimer.");
        alert.showAndWait();
    }
}

@FXML
void modifierReservation(ActionEvent event) {
    try {
        // Récupérer l'activité sélectionnée dans la ListView
    Reservation activiteSelectionnee = lvactivite.getSelectionModel().getSelectedItem();

        if (activiteSelectionnee != null) {
            // Récupérer l'ID de l'activité sélectionnée
            int id = activiteSelectionnee.getId();

          
            User user = activiteSelectionnee.getIdUser();
            Activities activities = activiteSelectionnee.getCode();

            // Charger une nouvelle scène ou créer une nouvelle scène
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierReservationFXML.fxml"));
            Parent nouvelleSceneParent = loader.load();
            ModifierReservationFXMLController ModifierReservationFXMLController = loader.getController();

            // Passer les attributs à la nouvelle scène, y compris l'ID de l'activité
            ModifierReservationFXMLController.initData(id, user, activities);

            // Récupérer la scène actuelle à partir de l'événement
            Scene sceneActuelle = ((Node) event.getSource()).getScene();

            // Changer la scène vers la nouvelle scène
            Stage stage = (Stage) sceneActuelle.getWindow();
            stage.setScene(new Scene(nouvelleSceneParent));
            stage.show();
        }
    } catch (IOException e) {
        e.printStackTrace(); // Vous pouvez gérer cette exception selon votre besoin
    }
}

// Méthode pour convertir java.util.Date en LocalDate
private LocalDate convertToLocalDate(Date date) {
    // Vérifiez si la date est de type java.sql.Date
    if (date instanceof java.sql.Date) {
        return ((java.sql.Date) date).toLocalDate();
    } else {
        // Si ce n'est pas de type java.sql.Date, convertissez normalement
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
}
