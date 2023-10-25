/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.SalleDeSport_Offer_Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import tn.esprit.SalleDeSport_Offer_Reservation.Entities.Abonnement;
import tn.esprit.SalleDeSport_Offer_Reservation.Entities.CodePromo;
import tn.esprit.SalleDeSport_Offer_Reservation.Entities.Offer;
import tn.esprit.SalleDeSport_Offer_Reservation.Entities.Reservation_Offer;
import static tn.esprit.SalleDeSport_Offer_Reservation.Entities.TypeAbonnement.Yellow_Sport;
import tn.esprit.SalleDeSport_Offer_Reservation.Services.Abonnement_Service;
import tn.esprit.SalleDeSport_Offer_Reservation.Services.Offer_Service;
import tn.esprit.SalleDeSport_Offer_Reservation.Services.Reservation_Offer_Service;
import tn.esprit.SalleDeSport_Offer_Reservation.Utiles.MyConnection;

/**
 *
 * @author chayma2
 */
public class SalleDeSport_offre {

    public static void main(String[] args) throws ParseException {
             /*===================================================*/
                        //   Teseter connectiviter     //
        MyConnection.getInstance(); 
        MyConnection.getInstance();
        /*===================================================*/
                        //   Ajouter Offer     //
            //Offer_Service serviceOffer = new Offer_Service();
            //Offer offer=new Offer();
            //1
          //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          /*Date date_resDebut = dateFormat.parse("2023-09-30 15:30:00");
          Date date_resFin = dateFormat.parse("2023-10-30 15:30:00");*/
         //Offer o = new Offer("Offer_Speciale","Profiter Notre Offer speciale les nouveautes",250,date_resDebut,date_resFin,"img");
          // Ajouter
        //serviceOffer.ajouterOffer(o);
        //2   Afficher
        //System.out.println(serviceOffer.afficherOffer());
        //System.out.println(serviceOffer.fetchOfferById(2));
        //3
                       // SupprimerrOffer     //
                       //int idOffer=9;
                       //if(serviceOffer.supprimerOffer(idOffer)==false)
                       //{        System.out.println("Offer with idOffer " + idOffer + " does not exist.");}
                        // ModifierOffer     //   
                        
        /*Date date_resDebut = dateFormat.parse("2023-09-30 15:30:00");
        Date date_resFin = dateFormat.parse("2023-10-30 15:30:00");
        Offer o = new Offer("Offer_Special COUPEL","Modified",300,date_resDebut,date_resFin,"/src/tn/esprit/SalleDeSport_Offer_Reservation/img/Black_Friday.jpeg");
        serviceOffer.ModOffer(o);*/
        
       
        Offer_Service offerService = new Offer_Service();
        List<Offer> orderedOffers = offerService.orderOffersByReservationCount();

        for (Offer offer : orderedOffers) {
            System.out.println(offer);
        }

        /*===================================================*/
        
        //ajouter Reservation
        //Reservation_Offer_Service RS = new Reservation_Offer_Service(); 
        /*Reservation_Offer R = new Reservation_Offer();
        R.setIdUser(1000); 
        R.setCodePromo(CodePromo.Ayari20);
        Offer offer = new Offer();
        offer.setIdOffer(3);
        R.setDateReservation(LocalDateTime.now());
        RS.ajouterReservation(R, offer);*/
        //System.out.println(RS.fetchReservation());
        //System.out.println(RS.fetchWithIdReservation(8));
        //RS.supprimerReservation(21);
  
        /*int id=1;
        int idOffer=2;

    RS.modifierReservation(R,id,idOffer);*/

        
        //Metiers
        //System.out.println(serviceOffer.afficherOffer());
        //List<Offer> offers = serviceOffer.afficherOffer();
        //serviceOffer.OffersWithMostReservations(offers);
        
        //serviceOffer.applyDiscountAndMakeDecision(o);

                /*===================================================*/
       /* Abonnement_Service as = new Abonnement_Service();
        Abonnement a = new Abonnement(3,LocalDateTime.now(),Yellow_Sport);
        //as.Abonner(a);
        
        System.out.println(as.fetchWithIdAbonnmenet(2));*/
        

  
  
    } 
    
    
}
