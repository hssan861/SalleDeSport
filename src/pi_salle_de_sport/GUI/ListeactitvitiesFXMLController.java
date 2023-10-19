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
import java.util.List;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pi_salle_de_sport.Entities.Activities;
import pi_salle_de_sport.Entities.Categorie;
import pi_salle_de_sport.Entities.User;
import pi_salle_de_sport.Services.ServiceActivities;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ListeactitvitiesFXMLController implements Initializable {
    
     private Stage stage;
    private Scene scene;
    private Parent root;
      @FXML
    private Button ajouter;
       @FXML
    private TextField ActiviteRech;
   @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Goajouter;
@FXML
 private ListView<Activities> lvactivite;

    @FXML
    void GoAjouter(ActionEvent event) {
try {

            Parent root = FXMLLoader.load(getClass().getResource("ajouterActivites.fxml"));
            // Parent root = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }
    

        ServiceActivities sa =new ServiceActivities();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            // Initialiser la liste des activités
      ObservableList<Activities> items = FXCollections.observableArrayList ()  ;
        items.addAll(sa.afficher());
        lvactivite.setItems(items);
    }    
    
    
    
    
    
    
   

    @FXML
void supprimerActivities(ActionEvent event) {
    // Obtenir l'activité sélectionnée depuis la ListView
    Activities activiteSelectionnee = lvactivite.getSelectionModel().getSelectedItem();
    if (activiteSelectionnee != null) {
        // Afficher une boîte de dialogue de confirmation
        Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation de la suppression");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Êtes-vous sûr de vouloir supprimer cette activité ?");

        // Attendre la réponse de l'utilisateur
        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Utilisateur a confirmé la suppression
            ServiceActivities serviceActivities = new ServiceActivities();
            boolean suppressionReussie = serviceActivities.supprimer(activiteSelectionnee);

            if (suppressionReussie) {
                lvactivite.getItems().remove(activiteSelectionnee);
            } else {
                // Afficher un message d'erreur si la suppression a échoué
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Erreur lors de la suppression de l'activité sélectionnée.");
                alert.showAndWait();
            }
        }
    } else {
        // Afficher un message si aucune activité n'a été sélectionnée
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Avertissement");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner une activité à supprimer.");
        alert.showAndWait();
    }
}
// @FXML
//void modifierActivite(ActionEvent event) {
//    try {
//        // Récupérer l'activité sélectionnée dans la ListView
//        Activities activiteSelectionnee = lvactivite.getSelectionModel().getSelectedItem();
//
//        if (activiteSelectionnee != null) {
//            // Récupérer les attributs de l'activité sélectionnée
//            int id = activiteSelectionnee.getCode();
//            String titre = activiteSelectionnee.getTitre();
//            String description = activiteSelectionnee.getDescription();
//            LocalDate dateDebut = convertToLocalDate(activiteSelectionnee.getDateDeb());
//            LocalDate dateFin = convertToLocalDate(activiteSelectionnee.getDateFin());
//            String salle = activiteSelectionnee.getSalle();
//            User coach = activiteSelectionnee.getIdCoach();
//            Categorie categorie = activiteSelectionnee.getCategorie();
//
//            // Charger une nouvelle scène ou créer une nouvelle scène
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("modifieractivitiesFXML.fxml"));
//            Parent nouvelleSceneParent = loader.load();
//            ModifieractivitiesFXMLController nouvelleSceneController = loader.getController();
//
//            // Passer les attributs à la nouvelle scène
//            nouvelleSceneController.initData(titre, description, dateDebut, dateFin, salle, coach, categorie);
//
//            // Récupérer la scène actuelle à partir de l'événement
//            Scene sceneActuelle = ((Node) event.getSource()).getScene();
//
//            // Changer la scène vers la nouvelle scène
//            Stage stage = (Stage) sceneActuelle.getWindow();
//            stage.setScene(new Scene(nouvelleSceneParent));
//            stage.show();
//        }
//    } catch (IOException e) {
//        e.printStackTrace(); // Vous pouvez gérer cette exception selon votre besoin
//    }
//}
@FXML
void modifierActivite(ActionEvent event) {
    try {
        // Récupérer l'activité sélectionnée dans la ListView
        Activities activiteSelectionnee = lvactivite.getSelectionModel().getSelectedItem();

        if (activiteSelectionnee != null) {
            // Récupérer l'ID de l'activité sélectionnée
            int code = activiteSelectionnee.getCode();

          String titre = activiteSelectionnee.getTitre();
            String description = activiteSelectionnee.getDescription();
            LocalDate dateDebut = convertToLocalDate(activiteSelectionnee.getDateDeb());
            LocalDate dateFin = convertToLocalDate(activiteSelectionnee.getDateFin());
            String salle = activiteSelectionnee.getSalle();
            User coach = activiteSelectionnee.getIdCoach();
            Categorie categorie = activiteSelectionnee.getCategorie();

            // Charger une nouvelle scène ou créer une nouvelle scène
            FXMLLoader loader = new FXMLLoader(getClass().getResource("modifieractivitiesFXML.fxml"));
            Parent nouvelleSceneParent = loader.load();
            ModifieractivitiesFXMLController ModifieractivitiesFXMLController = loader.getController();

            // Passer les attributs à la nouvelle scène, y compris l'ID de l'activité
            ModifieractivitiesFXMLController.initData(code, titre, description, dateDebut, dateFin, salle, coach, categorie);

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
