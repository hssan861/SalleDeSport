/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tn.esprit.SalleDeSport_Offer_Reservation.CUI;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import tn.esprit.SalleDeSport_Offer_Reservation.Entities.Abonnement;
import tn.esprit.SalleDeSport_Offer_Reservation.Entities.TypeAbonnement;
import tn.esprit.SalleDeSport_Offer_Reservation.Services.Abonnement_Service;

/**
 * FXML Controller class
 *
 * @author chayma2
 */
public class AbonnementController implements Initializable {


    Abonnement_Service as = new Abonnement_Service();
    
    @FXML
    private ComboBox<TypeAbonnement> AbonnementCombobox;
    @FXML
    private Button Abonner;
    @FXML
    private Button Retour;
    @FXML
    private Label err_Abonnement;
    @FXML
    private TextField tf_idUser;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        err_Abonnement.setVisible(false);
        ObservableList<TypeAbonnement> categories = FXCollections.observableArrayList(TypeAbonnement.values());
        AbonnementCombobox.setItems(categories);
        // TODO
    }    

    @FXML
    private void AbonnerAction(ActionEvent event) {

    //TypeAbonnement selectedType = AbonnementCombobox.getValue();
     TypeAbonnement selectedType = (TypeAbonnement) AbonnementCombobox.getValue();
    
    if (selectedType == null) {
        err_Abonnement.setText("Please choose a TypeAbonnement");
        err_Abonnement.setVisible(true);
        return; 
    }
    
    int userId = Integer.parseInt(tf_idUser.getText());

    LocalDateTime currentDateTime = LocalDateTime.now();

    Abonnement abonnement = new Abonnement(userId,currentDateTime,selectedType);
    as.Abonner(abonnement);
    showAlert("Success", "Abonnement added successfully!");
    }

    @FXML
    private void GoToAceuilPage(ActionEvent event) {
    }

     private void showAlert(String title, String content) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
    }
    
    
}
