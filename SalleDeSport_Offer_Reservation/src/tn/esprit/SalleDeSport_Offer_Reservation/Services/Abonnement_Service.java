/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.SalleDeSport_Offer_Reservation.Services;

import java.security.Timestamp;
import java.sql.Connection;
import tn.esprit.SalleDeSport_Offer_Reservation.Entities.Abonnement;
import tn.esprit.SalleDeSport_Offer_Reservation.Utiles.MyConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import tn.esprit.SalleDeSport_Offer_Reservation.Entities.TypeAbonnement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chayma2
 */
public class Abonnement_Service {
        //var
    MyConnection Mycnx = MyConnection.getInstance();
    Connection cnx = Mycnx.getCnx();
    
    /*===================================================*/
      public void Abonner(Abonnement a) {
        String req = "INSERT INTO `Abonnement`( `dateAbonnement`, `typeAbon`, `idUser`) VALUES ( ?, ?,?)";
        
        try (Connection cnx = MyConnection.getInstance().getCnx();
             PreparedStatement R = cnx.prepareStatement(req))
        {
            
            R.setObject(1, a.getDateAbonnement()); 
            R.setObject(2, a.getTypeAbon().toString());

            R.setInt(3, a.getIdUser());
            
            
            
            int rowsAffected = R.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Abonner ajoutée avec succès!");
        } else {
            System.out.println("Échec de l'abonnement.");
        }
        } catch (SQLException ex) {
                System.err.println("Erreur lors de l'abonnement : " + ex.getMessage());        }
    }
      
      
          /*===================================================*/

      
        public Abonnement fetchWithIdAbonnmenet(int idAbonement) {
    String req = "SELECT * FROM `Abonnement` WHERE idAbonement = ?";
 
    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, idAbonement);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            
            Abonnement A = new Abonnement();
            A.setIdAbonement(rs.getInt("idAbonement")); // Set the idAbonement field

            A.setIdUser(rs.getInt("idUser"));
            
            // Convert Date to LocalDateTime
            java.sql.Date  dateAbonnement = rs.getDate("dateAbonnement");
            //Instant instant = dateAbonnement.toInstant();
                        java.util.Date utilDate = new java.util.Date(dateAbonnement.getTime());

                    LocalDateTime localDateTime = utilDate.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
            A.setDateAbonnement(localDateTime);
            //A.setDateAbonnement(rs.getTime("dateAbonnement"));
                        
            String TypeAbonnmentSt = rs.getString("typeAbon");
            A.setTypeAbon(TypeAbonnement.valueOf(TypeAbonnmentSt));

            return A;
        } else {
            System.out.println("Abonnement with id " + idAbonement + " does not exist.");
            return null;
        }
    } catch (SQLException ex) {
         System.err.println("Erreur lors de l'abonnement : " + ex.getMessage());
    ex.printStackTrace();
    }
        return null;
}
    
    
}
