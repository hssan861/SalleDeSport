/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi_salle_de_sport.GUI;

/**
 *
 * @author HP
 */

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pi_salle_de_sport.Entities.Activities;
import pi_salle_de_sport.Entities.Categorie;
import pi_salle_de_sport.Entities.User;
import pi_salle_de_sport.Services.ServiceActivities;
import pi_salle_de_sport.Services.UserService;

public class ActivitesController {

    
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Categorie> categoryComboBox;

    @FXML
    private ComboBox<User> coachComboBox;

    @FXML
    private TextField descriptionField;

    @FXML
    private DatePicker endDatePicker;

    
    @FXML
    private Button goAfficher;
    @FXML
    private TextField salleField;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private TextField titleField;

    @FXML
    void initialize() {
         ObservableList<Categorie> categories = FXCollections.observableArrayList(Categorie.values());
        categoryComboBox.setItems(categories);
        
        
        
         UserService coachService = new UserService();

        // Fetch coach data from the database
        List<User> coachList = coachService.afficherUser();

        // Populate the ComboBox with coach data
        ObservableList<User> coaches = FXCollections.observableArrayList(coachList);
        coachComboBox.setItems(coaches);
        
    }

    
    
    @FXML
private void ajouter(ActionEvent event) {
    // Retrieve input values
    String titre = titleField.getText();
    String description = descriptionField.getText();
    LocalDate dateDeb = startDatePicker.getValue();
    LocalDate dateFin = endDatePicker.getValue();
    String salle = salleField.getText();
 User coach = (User) coachComboBox.getValue();  // Assuming User is the type for coach
    Categorie categorie = (Categorie) categoryComboBox.getValue();
    // Perform validation
    if (titre.isEmpty() || description.isEmpty() || dateDeb == null || dateFin == null || salle.isEmpty()) {
        showAlert("Erreur", "Veuillez remplir tous les champs.");
        return;
    }

    // Create an Activities object
    Activities activity = new Activities(titre, convertToDate(dateDeb), convertToDate(dateFin), coach, categorie, description, salle);

    // Print the Activities object
    System.out.println(activity);

    // Add activity to the service and update the view
    // Assuming you have a service for activities called "activityService"
    ServiceActivities r = new ServiceActivities();

    // Optionally, update the view here
r.addReservation(activity);
    // Show a success message to the user
    showAlert("Success", "Activity added successfully!");
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
    void GoAfficher(ActionEvent event) {
 try {

            Parent root = FXMLLoader.load(getClass().getResource("listeactitvitiesFXML.fxml"));
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

}

