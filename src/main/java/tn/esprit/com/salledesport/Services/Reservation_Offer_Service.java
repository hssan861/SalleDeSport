/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.com.salledesport.Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.esprit.com.salledesport.Entities.Offer;
import tn.esprit.com.salledesport.Entities.Reservation_Offer;
import tn.esprit.com.salledesport.Utiles.MyConnection;
/**
 *
 * @author chayma2
 */
public class Reservation_Offer_Service {
    
           //var
    MyConnection Mycnx = MyConnection.getInstance();
    Connection cnx = Mycnx.getCnx();
        private Offer offer= new Offer();

    
        /*===================================================*/
    
    public void ajouterReservation(Reservation_Offer r, int idOffer) {
        String req = "INSERT INTO `Reservation_Offer`(`dateReservation`, `idUser`, `idOffer`) VALUES (?, ?, ?)";
        
        try (Connection cnx = MyConnection.getInstance().getCnx();
             PreparedStatement R = cnx.prepareStatement(req))
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(r.getDateReservation());
            
            R.setString(1, date);
            R.setInt(2, r.getIdUser());
            R.setInt(3,idOffer );
            R.executeUpdate();
            System.out.println("Reservation ajoutée avec succès!");
        } catch (SQLException ex) {
            Logger.getLogger(Reservation_Offer_Service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*===================================================*/
         //Fetch
    public List<Reservation_Offer> afficherReservation(){
        List<Reservation_Offer> reservations = new ArrayList<>();
         //1
         String req = "SELECT * FROM Reservation_Offer";
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
                // Initialize the offer object for each Reservation_Offer instance
                Offer offer = new Offer();
                 offer.setIdOffer(rs.getInt(4));
                R.setOffer(offer); // Set the offer object in Reservation_Offer
            
                reservations.add(R);
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
            
        } catch (SQLException ex) {
            System.out.println(ex);
            }
        return false;
        }
    
    
}



    
    

