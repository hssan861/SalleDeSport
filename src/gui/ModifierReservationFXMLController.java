/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
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
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import models.Activities;
import models.Categorie;
import models.Reservation;
import models.User;
import Services.ServiceActivities;
import Services.ServiceReservation;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ModifierReservationFXMLController implements Initializable {
    
    
    private int id;
     private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private ComboBox<Activities> activitesComboBox;

    @FXML
    private Button ajouter;

    @FXML
    private Button goAfficher;
   

    @FXML
    private ComboBox<User> utilisateurComboBox;
    
    
    ServiceReservation sa=new ServiceReservation();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void initData(int id, User coach, Activities activities) {
    // Utiliser les données passées pour initialiser votre interface utilisateur
    this.id = id; // Assigner l'ID de l'activité

   // Nettoyer les éléments actuels de la combobox des utilisateurs
utilisateurComboBox.getItems().clear();

// Remplir la combobox des utilisateurs avec le coach passé en paramètre
utilisateurComboBox.getItems().add(coach);

// Remplir la combobox des utilisateurs avec la liste complète des utilisateurs
UserService us = new UserService();
List<User> userList = us.afficherUser();
utilisateurComboBox.getItems().addAll(userList);

// Sélectionner le coach passé en paramètre dans la combobox des utilisateurs
utilisateurComboBox.getSelectionModel().select(coach);

// Nettoyer les éléments actuels de la combobox des activités
activitesComboBox.getItems().clear();

// Remplir la combobox des activités avec les activités passées en paramètre
activitesComboBox.getItems().addAll(activities);

// Remplir la combobox des activités avec la liste complète des activités
ServiceActivities sr = new ServiceActivities();
List<Activities> activitylist = sr.afficher();
activitesComboBox.getItems().addAll(activitylist);

// Sélectionner la première activité dans la combobox des activités
activitesComboBox.getSelectionModel().selectFirst();



}
    
    
    
    
   @FXML
void ModifReservation(ActionEvent event) throws IOException {

    // Utiliser this.code pour accéder à l'ID de l'activité
    // System.out.println("ID de l'activité : " + id);

    // Récupérer les valeurs des champs depuis l'interface utilisateur
    User user = utilisateurComboBox.getValue();
    Activities activities = activitesComboBox.getValue();

    // Vérifier si les champs obligatoires sont remplis
    if (user == null || activities == null) {
        // Afficher un message d'erreur si les champs obligatoires ne sont pas sélectionnés
        afficherAlerte("Erreur", "Veuillez sélectionner un utilisateur et une activité.");
        return;  // Arrêter le traitement car les champs ne sont pas valides
    }

    // Créer un objet Reservation avec les nouvelles données
    Reservation reservation = new Reservation();
    reservation.setId(id);  // Assigner l'ID de l'activité

    reservation.setIdUser(user);
    reservation.setCode(activities);

    // Appeler le service pour modifier l'activité
    boolean result = sa.modifier(reservation);

    // Traiter la réponse du service
    if (result) {
        // L'activité a été modifiée avec succès
        afficherAlerte("Succès", "Activité modifiée avec succès.");

        // Rediriger vers le fichier FXML "afficher.fxml"
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherReservationFXML.fxml"));
        Parent afficherParent = loader.load();
        Scene afficherScene = new Scene(afficherParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(afficherScene);
        window.show();
    } else {
        // Aucune activité trouvée avec le code spécifié, ou une erreur s'est produite lors de la modification
        afficherAlerte("Échec", "Aucune activité trouvée avec le code spécifié, ou une erreur s'est produite lors de la modification.");
    }
}

// Méthode pour afficher une alerte
private void afficherAlerte(String titre, String contenu) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(titre);
    alert.setHeaderText(null);
    alert.setContentText(contenu);
    alert.showAndWait();
}

// Méthode pour afficher une alerte

}
