/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import API.SendSM;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Activities;
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
    private TextField textfield;

    @FXML


void AjouterReservation(ActionEvent event) {
    // Obtenir la date actuelle
    LocalDate dateRes = LocalDate.now();

    // Récupérer l'activité sélectionnée depuis la ListView
    Activities selectedActivity = afficheracitiviter.getSelectionModel().getSelectedItem();

    // Récupérer l'utilisateur sélectionné depuis la ComboBox
    User selectedUser = utilisateurComboBox.getValue();

    // Vérifier que l'activité et l'utilisateur sont sélectionnés
    if (selectedActivity == null || selectedUser == null) {
        showAlert("Erreur", "Veuillez sélectionner un utilisateur et une activité.");
        return;  // Arrêter l'ajout en cas de validation échouée
    }

    // Créer un objet Reservation avec la date actuelle, l'utilisateur et l'activité
    Reservation reservation = new Reservation(convertToDate(dateRes), selectedUser, selectedActivity);

    // Ajouter la réservation via votre service (ServiceReservation)
    ServiceReservation serviceReservation = new ServiceReservation();
    serviceReservation.addReservation(reservation);

   try {
            SendSM sm = new SendSM();
            sm.sendSM(reservation);
            System.out.println("SMS envoyé avec succès");
        } catch (Exception e) {
            // handle the exception here
            e.printStackTrace();
        }
    // Afficher un message de succès
    showAlert("Succès", "Réservation ajoutée avec succès !");

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
