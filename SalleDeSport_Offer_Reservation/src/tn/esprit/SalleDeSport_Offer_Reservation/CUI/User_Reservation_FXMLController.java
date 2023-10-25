/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tn.esprit.SalleDeSport_Offer_Reservation.CUI;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import tn.esprit.SalleDeSport_Offer_Reservation.Entities.Reservation_Offer;
import tn.esprit.SalleDeSport_Offer_Reservation.Services.Reservation_Offer_Service;

/**
 * FXML Controller class
 *
 * @author chayma2
 */
public class User_Reservation_FXMLController implements Initializable {
    //Reservation_Offer r =new Reservation_Offer();
    Reservation_Offer_Service rs = new Reservation_Offer_Service();
    private int idReservation;
    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    @FXML
    private TableView<Reservation_Offer> tableView_Reservation;
    @FXML
    private Button ModifierReservation;
    @FXML
    private Button SuppReservation;
    @FXML
    private Button Afficher;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    

    @FXML
    private void goToModoer(ActionEvent event) {
    }

    @FXML
    private void GoToSuppReservation(ActionEvent event) {
    }

    @FXML
    private void ActualiserAction(ActionEvent event) {
                updateTableView(idReservation);

    }
    
    public void updateTableView(int idReservation) {
        Reservation_Offer reservation = rs.fetchWithIdReservation(idReservation);

        // Clear the existing items in the table
        tableView_Reservation.getItems().clear();
        
        // Add the fetched reservations to the table
        tableView_Reservation.getItems().add(reservation);
    }
    
}
