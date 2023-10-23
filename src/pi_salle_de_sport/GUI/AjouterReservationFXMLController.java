/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi_salle_de_sport.GUI;

import api.SendSMS;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import pi_salle_de_sport.Entities.Activities;
import pi_salle_de_sport.Entities.Reservation;
import pi_salle_de_sport.Entities.User;
import pi_salle_de_sport.Services.ServiceActivities;
import pi_salle_de_sport.Services.ServiceReservation;
import pi_salle_de_sport.Services.UserService;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AjouterReservationFXMLController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    ServiceActivities sa =new ServiceActivities();
    
    @FXML
    private ComboBox<Activities> activitesComboBox;

    @FXML
    private Button ajouter;

    @FXML
    private Button goAfficher;
      @FXML      
    private ListView<Activities> afficheracitiviter;
   

    @FXML
    private ComboBox<User> utilisateurComboBox;

    @FXML

void AjouterReservation(ActionEvent event) {
    // Obtenir la date actuelle
LocalDate dateRes = LocalDate.now();
        
    // Récupérer l'utilisateur et l'activité sélectionnés
    User user = (User) utilisateurComboBox.getValue();
    Activities activities = (Activities) activitesComboBox.getValue();

    // Vérifier que l'utilisateur et l'activité sont sélectionnés
    if (user == null || activities == null) {
        showAlert("Erreur", "Veuillez sélectionner un utilisateur et une activité.");
        return;  // Arrêter l'ajout en cas de validation échouée
    }

    // Créer un objet Reservation avec la date actuelle, l'utilisateur et l'activité
    Reservation reservation = new Reservation(convertToDate(dateRes), user, activities);

    // Ajouter la réservation via votre service (ServiceReservation)
    ServiceReservation serviceReservation = new ServiceReservation();
    serviceReservation.addReservation(reservation);

    // Afficher un message de succès
    showAlert("Succès", "Réservation ajoutée avec succès !");
    
    try {
            SendSMS sm = new SendSMS();
            sm.sendSMS(reservation);
            System.out.println("SMS envoyé avec succès");
        } catch (Exception e) {
            // handle the exception here
            e.printStackTrace();
        }
}

private void showAlert(String title, String content) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
}

private Date convertToDate(LocalDate localDate) {
    return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
}

        
        
        
    

    @FXML
    void goAfficher(ActionEvent event) {
try {

            Parent root = FXMLLoader.load(getClass().getResource("AfficherReservationFXML.fxml"));
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
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
             ServiceActivities activities = new ServiceActivities();

        // Fetch coach data from the database
        List<Activities> activitiesList = activities.afficher();

        // Populate the ComboBox with coach data
        ObservableList<Activities> activitiesl = FXCollections.observableArrayList(activitiesList);
        activitesComboBox.setItems(activitiesl);
        
        
        
         UserService coachService = new UserService();

        // Fetch coach data from the database
        List<User> coachList = coachService.afficherUser();

        // Populate the ComboBox with coach data
        ObservableList<User> coaches = FXCollections.observableArrayList(coachList);
        utilisateurComboBox.setItems(coaches);
        
        // Initialiser la liste des activités
      ObservableList<Activities> items = FXCollections.observableArrayList ()  ;
        items.addAll(sa.afficher());
        afficheracitiviter.setItems(items);
        
    }    
    
}
