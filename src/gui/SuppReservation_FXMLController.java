/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

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
import models.Reservation_offer;
import services.ReservationOffer_Service;

/**
 * FXML Controller class
 *
 * @author chayma2
 */
public class SuppReservation_FXMLController implements Initializable {
     
    ReservationOffer_Service rs = new ReservationOffer_Service();
    private Reservation_offer selectedReservation; 
    
     public void setSelectedReservation(Reservation_offer r) {
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
