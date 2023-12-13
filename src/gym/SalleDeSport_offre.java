/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gym;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import models.Type_abonn;
import models.Abonnement;
import models.Offer;
import models.Reservation_offer;
import services.TypeAbonn_Service;
import services.Abonnement_Service;
import services.Offer_Service;
import services.ReservationOffer_Service;
import util.MyConnection;

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
        
        //Ajoout Type_Abonn
        TypeAbonn_Service t = new TypeAbonn_Service() ;
        Type_abonn tb=new Type_abonn("abonn2","description2",0);
        //t.ajoutTypeAbonn(tb);
                System.out.println(t.afficherTypeAbonn());

        /*===================================================*/
        Abonnement_Service as = new Abonnement_Service();
        Abonnement ab=new Abonnement();
                   ab.setDateAbonnement(LocalDateTime.now());
                   ab.setIdUser(3);
                   ab.setVerification_code(56789);
                   tb.setId(8);
        //as.Abonner(ab, tb);
        //System.out.println(as.afficherAbonnement());
     
        /*===================================================*/
 
        
                        //   Ajouter Offer     //
            Offer_Service serviceOffer = new Offer_Service();
            //Offer offer=new Offer();
            //1
          SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          Date date_resDebut = dateFormat.parse("2023-09-30 15:30:00");
          Date date_resFin = dateFormat.parse("2023-10-30 15:30:00");
         Offer o = new Offer("Offer_SpecialeModifier","Profiter Notre Offer speciale les nouveautes",250,date_resDebut,date_resFin,"img",12);
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
                        
        //Date date_resDebut = dateFormat.parse("2023-09-30 15:30:00");
        //Date date_resFin = dateFormat.parse("2023-10-30 15:30:00");
        //Offer o = new Offer("Offer_Special COUPEL","Modified",300,date_resDebut,date_resFin,"/src/tn/esprit/SalleDeSport_Offer_Reservation/img/Black_Friday.jpeg");
        //serviceOffer.ModOffer(o);
        
       
        /*Offer_Service offerService = new Offer_Service();
        List<Offer> orderedOffers = offerService.orderOffersByReservationCount();

        for (Offer offer : orderedOffers) {
            System.out.println(offer);
        }*/

        /*===================================================*/
        
        //ajouter Reservation
                Offer offer = new Offer();
        ReservationOffer_Service RS = new ReservationOffer_Service(); 
        Reservation_offer R = new Reservation_offer();
        R.setIdUser(1000); 
        R.setCodePromo("aaa");
        offer.setIdOffer(1);
        R.setDateReservation(LocalDateTime.now());
        //RS.ajouterReservation(R, offer);
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
