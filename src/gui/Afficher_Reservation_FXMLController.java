/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.ReservationOffer_Service;
import models.Reservation_offer;

/**
 * FXML Controller class
 *
 * @author chayma2
 */
public class Afficher_Reservation_FXMLController implements Initializable {
    
    ReservationOffer_Service rs = new ReservationOffer_Service();
    private Reservation_offer selectedReservation; 

    @FXML
    private Button afficheReservations;
    @FXML
    private ListView<Reservation_offer> ReservationListView;
    @FXML
    private Button Retour;
    @FXML
    private TextField serchTableView;
    @FXML
    private Button SuppReservations;
    @FXML
    private Button imprimer;
    @FXML
    private Label tf_NBReservation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        int totalReservations = rs.reservationAllCount();
    tf_NBReservation.setText(String.valueOf(totalReservations));
    
         ReservationListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedReservation = newValue;
            
        });
    }    

    @FXML
    private void actualiser(ActionEvent event) {
        List<Reservation_offer> offers = rs.fetchReservation();
    
    // Create an ObservableList to store the offers
    ObservableList<Reservation_offer> ReservationList = FXCollections.observableArrayList(offers);
    ReservationListView.setItems(ReservationList);
    
    ReservationListView.setCellFactory(new ReservationCellFactory());
    }

    @FXML
    private void GoToAceuilPage(ActionEvent event) {
    }

    @FXML
    private void Recherch(ActionEvent event) {
        String idText = serchTableView.getText();
    if (!idText.isEmpty()) {
        try {
            int id = Integer.parseInt(idText);
            Reservation_offer r = rs.fetchWithIdReservation(id);

            if (r != null) {
                ObservableList<Reservation_offer> ReservationList = FXCollections.observableArrayList(r);

                ReservationListView.setItems(ReservationList);

                ReservationListView.getSelectionModel().select(r);
            } else {
                showAlert("Reservation Not Found", "Offer with ID " + id + " does not exist.");
            }
        } catch (NumberFormatException e) {
            showAlert("Invalid ID", "Please enter a valid numeric ID.");
        }
    } else {
        showAlert("Empty ID", "Please enter an ID to search for an offer.");
    }
        
    }
    
    private void showAlert(String title, String content) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
    }

    @FXML
    private void GoToSupprimerReservatios(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SuppReservation_FXML.fxml"));
            Parent root = loader.load();

            // Pass the selected offer to the Supp_OfferController
            SuppReservation_FXMLController suppReservationController = loader.getController();
            suppReservationController.setSelectedReservation(selectedReservation);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void donlowd(ActionEvent event) {
        if(selectedReservation==null){
        showAlert("Empty Information","Please select a reservation to download");
                }
        else {
            rs.pdf();
          showAlert("Success","check file");  
        }
    }
    
}
