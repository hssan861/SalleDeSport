/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import models.Reservation_offer;

/**
 *
 * @author chayma2
 */
public class ReservationCellFactory implements Callback<ListView<Reservation_offer>, ListCell<Reservation_offer>> {

    @Override
    public ListCell<Reservation_offer> call(ListView<Reservation_offer> p) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
 return new ListCell<>(){
        @Override    
            protected void updateItem(Reservation_offer t, boolean bln) {
                            super.updateItem(t, bln); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody

                               if (t != null) {
                    // Create labels to display the information
                    Label idUserLabel = new Label("\tUser ID: " + t.getIdUser().toString()+"\t");
                    Label dateLabel = new Label("Date: " + t.getDateReservation().toString()+"\t");
                    Label codePromoLabel = new Label("Code Promo: " + t.getCodePromo().toString()+"\t");
                    Label offerLabel = new Label("Offer: " + t.getOffer().getTitleOffer()+"\t");
                    
                                        // Apply CSS styles
                    idUserLabel.setStyle("-fx-text-fill: #1b95b2; -fx-font-weight: bold;");
                    dateLabel.setStyle("-fx-text-fill: #1b95b2; -fx-font-weight: bold;");
                    codePromoLabel.setStyle("-fx-text-fill: #1b95b2; -fx-font-weight: bold;");
                    offerLabel.setStyle("-fx-text-fill: #1b95b2; -fx-font-weight: bold;");

                    // Create an HBox to arrange the labels horizontally
                    HBox hbox = new HBox(idUserLabel, dateLabel, codePromoLabel, offerLabel);
                                setGraphic(hbox);
                                }
                }
 };
}
}