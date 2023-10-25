/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tn.esprit.SalleDeSport_Offer_Reservation.CUI;

import com.sun.tools.javac.Main;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import tn.esprit.SalleDeSport_Offer_Reservation.Entities.Offer;
import tn.esprit.SalleDeSport_Offer_Reservation.Services.Offer_Service;

/**
 * FXML Controller class
 *
 * @author chayma2
 */
public class Supp_OfferController implements Initializable {
    
              Offer_Service sp = new Offer_Service();
                  private Offer selectedOffer; // Store the selected offer
                  private Affiche_Offer_FXMLController afficheOfferController;

                public void  setAfficheOfferController(Affiche_Offer_FXMLController controller) {
                    afficheOfferController = controller;
                }

                    private void fireUpdateEvent() {
                    if (afficheOfferController != null) {
                        afficheOfferController.handleUpdateEvent();
                    }
                }
                    
                    
    @FXML
    private Button Retour;

              public void setSelectedOffer(Offer offer) {
        selectedOffer = offer;
              }


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
    private void Supprimer(ActionEvent event) {
            if (selectedOffer != null) {
            sp.supprimerOffer(selectedOffer.getIdOffer());
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Offer deleted successfully.");
            alert.showAndWait();
            fireUpdateEvent();
            ((Stage) Confirmer.getScene().getWindow()).close();
            
            
            }
            
    }

    @FXML
    private void RetourSupp(ActionEvent event) {
         try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Affiche_Offer_FXML.fxml"));
        Parent root = loader.load();

        //Stage stage = new Stage();
        //stage.setScene(new Scene(root));
        //stage.show();
                ((Stage) Retour.getScene().getWindow()).close();

        //Node source = (Node) event.getSource();
        //Stage currentStage = (Stage) source.getScene().getWindow();
        //currentStage.close();
        ((Stage) Retour.getScene().getWindow()).close();
        } catch (IOException e) {
        e.printStackTrace(); 
    }
    
    }
    
    
    
}
