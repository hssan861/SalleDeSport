/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tn.esprit.SalleDeSport_Offer_Reservation.CUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import tn.esprit.SalleDeSport_Offer_Reservation.Entities.Reservation_Offer;
import tn.esprit.SalleDeSport_Offer_Reservation.Services.Reservation_Offer_Service;

/**
 * FXML Controller class
 *
 * @author chayma2
 */
public class SuppReservation_FXMLController implements Initializable {
     
    Reservation_Offer_Service rs = new Reservation_Offer_Service();
    private Reservation_Offer selectedReservation; 
    
     public void setSelectedReservation(Reservation_Offer r) {
        selectedReservation = r;
    }

    @FXML
    private Button Retour;
    @FXML
    private Button Confirmer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void RetourSupp(ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Afficher_Reservation_FXML.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        Node source = (Node) event.getSource();
        Stage currentStage = (Stage) source.getScene().getWindow();
        currentStage.close();
        } catch (IOException e) {
        e.printStackTrace(); 
    
    }
    }

    @FXML
    private void Supprimer(ActionEvent event) {
        if (selectedReservation != null) {
            rs.supprimerReservation(selectedReservation.getIdReservation());
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Reservation deleted successfully.");
            alert.showAndWait();
            // Close the current stage
            ((Stage) Confirmer.getScene().getWindow()).close();
            
            }
        
    }
    
}
