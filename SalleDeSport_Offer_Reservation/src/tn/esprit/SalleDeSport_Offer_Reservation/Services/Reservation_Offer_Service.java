/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.SalleDeSport_Offer_Reservation.Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.esprit.SalleDeSport_Offer_Reservation.Entities.Offer;
import tn.esprit.SalleDeSport_Offer_Reservation.Entities.Reservation_Offer;
import tn.esprit.SalleDeSport_Offer_Reservation.Utiles.MyConnection;

/**
 *
 * @author chayma2
 */
public class Reservation_Offer_Service {
    
           //var
    MyConnection Mycnx = MyConnection.getInstance();
    Connection cnx = Mycnx.getCnx();

    
        /*===================================================*/
    
    public void ajouterReservation(Reservation_Offer r, Offer offer) {
        String req = "INSERT INTO `Reservation_Offer`(`dateReservation`, `idUser`, `idOffer`) VALUES (?, ?, ?)";
        
        try (Connection cnx = MyConnection.getInstance().getCnx();
             PreparedStatement R = cnx.prepareStatement(req))
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(r.getDateReservation());
            
            R.setString(1, date);
            R.setInt(2, r.getIdUser());
            R.setInt(3,offer.getIdOffer() );
            int rowsAffected = R.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Reservation ajoutée avec succès!");
        } else {
            System.out.println("Échec de l'ajout de la réservation.");
        }
        } catch (SQLException ex) {
                System.err.println("Erreur lors de l'ajout de la réservation : " + ex.getMessage());        }
    }
    
    /*===================================================*/
         //Fetch
    public List<Reservation_Offer> fetchReservation(){
        List<Reservation_Offer> reservations = new ArrayList<>();
         //1
         String req = "SELECT * FROM Reservation_Offer  " ;
             
        try {
            //2
            Statement st = cnx.createStatement();
            //3
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Reservation_Offer R = new Reservation_Offer();
                R.setIdReservation(rs.getInt(1));
                R.setDateReservation(rs.getDate(2));
                R.setIdUser(rs.getInt(3));
                
               int idOffer = rs.getInt("idOffer"); // reading idOffer from Reservation
                Offer_Service service = new Offer_Service();
                
                Offer offer = service.getById(idOffer); //fetch byId Offer
                //offer.setIdOffer(rs.getInt(4));
                if (offer != null) {
                Set<Offer> offers = new HashSet<>(); 
                offers.add(offer);
                R.setOffer(offers); 
                }
                reservations.add(R);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Reservation_Offer_Service.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return reservations;
    }
            /*===================================================*/
    public List<Reservation_Offer> fetchWithIdReservation(int idReservation) {
    List<Reservation_Offer> reservations = new ArrayList<>();
    //1
    String req = "SELECT * FROM Reservation_Offer WHERE idReservation = ?";

    try {
        //2
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, idReservation);

        //3
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Reservation_Offer R = new Reservation_Offer();
            R.setIdReservation(rs.getInt(1));
            R.setDateReservation(rs.getDate(2));
            R.setIdUser(rs.getInt(3));

            int idOffer = rs.getInt("idOffer"); // reading idOffer from Reservation
            Offer_Service service = new Offer_Service();

            Offer offer = service.getById(idOffer); // fetch byId Offer
            //offer.setIdOffer(rs.getInt(4));
            if (offer != null) {
                Set<Offer> offers = new HashSet<>();
                offers.add(offer);
                R.setOffer(offers);
            }
            reservations.add(R);
        }
        else {
            System.out.println("Reservation with idReservation " + idReservation + " does not exist.");
        }
    } catch (SQLException ex) {
        Logger.getLogger(Reservation_Offer_Service.class.getName()).log(Level.SEVERE, null, ex);
    }

    return reservations;
}

       


    /*===================================================*/
           
            public Boolean supprimerReservation(int id) {
            try {    
            String req = "DELETE FROM `Reservation_Offer` WHERE `idReservation`="+id;
         
            Statement stm = cnx.createStatement();

            stm = cnx.createStatement();
            int rowsDeleted = stm.executeUpdate(req);
            if (rowsDeleted > 0) {
                   System.out.println("Réservation with id ="+id+" is deleted");

                return true;
            } else {
                 System.out.println("Réservation with id ="+id+" dos not exist");
                 return false;
            }
        } catch (SQLException ex) {
                   System.err.println (ex.getMessage());

               }
               return false;
       }
    /*===================================================*/
           
        public Boolean modifierReservation(Reservation_Offer r ,int idRese, int idOffer) {
            
      try {
           String req="UPDATE `Reservation_Offer` SET `dateReservation`=?,`idUser`=?,`idOffer`=? WHERE `idReservation`="+idRese;
            PreparedStatement st = cnx.prepareStatement(req);
            
             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(r.getDateReservation());
            
            st.setString(1, date);
            st.setInt(2, r.getIdUser());
            st.setInt(3, idOffer);
            //st.setInt(4, idRese); // Use the provided idRese parameter as the condition
            st.executeUpdate();
            int rowsUpdated = st.executeUpdate();
        
        if (rowsUpdated > 0) {
            System.out.println("Reservation Modifiée avec succès");
            return true; // Update was successful
        }
        else {
                System.out.println("Aucune modification effectuée");
                return false; // Update was successful
        }
            
        } catch (SQLException ex) {
            System.out.println(ex);
            }
        return false;
        }
    
    
}
