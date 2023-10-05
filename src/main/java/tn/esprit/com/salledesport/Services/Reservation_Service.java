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
import tn.esprit.com.salledesport.Entities.Reservation;
import tn.esprit.com.salledesport.Utiles.MyConnection;
/**
 *
 * @author chayma2
 */
public class Reservation_Service {
    
           //var
    MyConnection Mycnx = MyConnection.getInstance();
    Connection cnx = Mycnx.getCnx();
    
    
    public static boolean offerExists(int idOffer) {
        boolean offerExists = false;
        String query = "SELECT COUNT(*) FROM Offer WHERE idOffer = ?";
        
        try (Connection cnx = MyConnection.getInstance().getCnx();
             PreparedStatement R = cnx.prepareStatement(query))
        {          
            R.setInt(1, idOffer);
            ResultSet resultSet = R.executeQuery();
            
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                offerExists = (count > 0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return offerExists;
    }

    /*===================================================*/
    
    public void ajouterReservation(Reservation r, int idOffer) {
        String req = "INSERT INTO `Reservation`(`dateReservation`, `idUser`, `idOffer`) VALUES (?, ?, ?)";
        
        try (Connection cnx = MyConnection.getInstance().getCnx();
             PreparedStatement R = cnx.prepareStatement(req))
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(r.getDateReservation());
            
            R.setString(1, date);
            R.setInt(2, r.getIdUser());
            R.setInt(3, idOffer);
            R.executeUpdate();
            System.out.println("Reservation ajoutée avec succès!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    /*===================================================*/
         //Fetch
    public List<Reservation> afficherReservation(){
        List<Reservation> reservations = new ArrayList<>();
         //1
         String req = "SELECT * FROM Reservation";
        try {
            //2
            Statement st = cnx.createStatement();
            //3
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Reservation R = new Reservation();
                R.setIdReservation(rs.getInt(1));
                R.setDateReservation(rs.getDate(2));
                R.setIdUser(rs.getInt(3));
                R.setIdOffer(rs.getInt(4));
                reservations.add(R);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Offer_Service.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return reservations;
    }
    /*===================================================*/
           public void supprimerReservation(int id) {
            System.out.println(id);
                try {
            String req = "DELETE FROM `Reservation` WHERE `idReservation`="+id;
            Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
            System.out.println("Reservation supprimer avec succés");
        } catch (SQLException ex) {
            System.out.println(ex);
            }
        }
    /*===================================================*/
           
        public void modifierOffer(Reservation r) {    
      try {
           String req="UPDATE `Reservation` SET `dateReservation`=?,`idUser`=?,`idOffer`=? WHERE `idReservation`="+r.getIdReservation();
            PreparedStatement st = cnx.prepareStatement(req);
            
             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(r.getDateReservation());
            
            st.setString(1, date);
            st.setInt(2, r.getIdUser());
            st.setInt(3, r.getIdOffer());
            st.executeUpdate();
            System.out.println("Reservation Modifié avec succés");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
  }
    
    
}



    
    

