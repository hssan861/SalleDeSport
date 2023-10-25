/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.SalleDeSport_Offer_Reservation.Entities;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;
import tn.esprit.SalleDeSport_Offer_Reservation.Services.Offer_Service;

/**
 *
 * @author chayma2
 */
public class ReservationCountCallback implements Callback<CellDataFeatures<Offer, Integer>, ObservableValue<Integer>> {
    private Offer_Service offerService = new Offer_Service();

    @Override
    public ObservableValue<Integer> call(CellDataFeatures<Offer, Integer> param) {
        Offer offer = param.getValue();
        int reservationCount = offerService.reservationCount(offer.getIdOffer());
        return new SimpleObjectProperty<>(reservationCount);
    }
}