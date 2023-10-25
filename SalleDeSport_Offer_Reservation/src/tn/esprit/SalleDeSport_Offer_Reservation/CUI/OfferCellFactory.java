/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.SalleDeSport_Offer_Reservation.CUI;

import java.io.IOException;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import tn.esprit.SalleDeSport_Offer_Reservation.Entities.Offer;

/**
 *
 * @author chayma2
 */
public class OfferCellFactory implements Callback<ListView<Offer>, ListCell<Offer>>{
    
 

    @Override
    public ListCell<Offer> call(ListView<Offer> p) {
        return new ListCell<>(){
            private CardListViewOfferController controller;
            {
                // Load the FXML for each item
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CardListViewOffer.fxml"));
                try {
                    loader.load();
                    controller = loader.getController();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            //protected void updateItem(Offer t, boolean bln) {
                /*super.updateItem(t, bln); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
                
                    if(t != null){
                        setText(null);
                    Label l = new Label();
                    l.setText(t.getTitleOffer());
                    Label prixl = new Label();
                    prixl.setText(String.valueOf(t.getPrix()));
                    VBox vbox = new VBox(l, prixl);
                    setGraphic(vbox);
                    }*/
                protected void updateItem(Offer offer, boolean empty) {
                super.updateItem(offer, empty);

                if (empty || offer == null) {
                    setGraphic(null);
                } else {
                    controller.setData(offer);
                    setGraphic(controller.getVbox());
                
                }
            }
            
        };
    }
    
}
