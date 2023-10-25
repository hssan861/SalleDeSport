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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.esprit.SalleDeSport_Offer_Reservation.Entities.Offer;
import tn.esprit.SalleDeSport_Offer_Reservation.Entities.Reservation_Offer;
import tn.esprit.SalleDeSport_Offer_Reservation.Utiles.MyConnection;

/**
 *
 * @author chayma2
 */
public class Offer_Service {
        //var
    MyConnection Mycnx = MyConnection.getInstance();
    Connection cnx = Mycnx.getCnx();
    
    /*===================================================*/
    
    public boolean offerExists(String nomOffre) {
        boolean offerExists = false;
        String query = "SELECT COUNT(*) FROM Offer WHERE titleOffer = ?";
        
        try 
        {   
            PreparedStatement R = cnx.prepareStatement(query);
            R.setString(1, nomOffre);
            ResultSet resultSet = R.executeQuery();
            
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                offerExists = (count > 0);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Offer_Service.class.getName()).log(Level.SEVERE, null,ex);
        }
        return offerExists;
    }

    
     /*===================================================*/
    //Add 
    public void ajouterOffer(Offer o) {
        //1
       // Convertir la date en une chaîne de caractères au format MySQL
       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateDebFormatted = dateFormat.format(o.getDateDebOffer());
                String dateFinFormatted = dateFormat.format(o.getDateFinOffer());
                
        String req = "INSERT INTO `Offer`( `titleOffer`, `descriptionOffer`, `prix`, `dateDebOffer`, `dateFinOffer`, `img`) VALUES ('"+o.getTitleOffer()+"','"+o.getDescriptionOffer()+"',"+o.getPrix()+",'"+dateDebFormatted +"','"+dateFinFormatted+"','"+o.getImg()+"')";

        try {
            //2 : Statement : requêtes statiques simples
            Statement st = cnx.createStatement();
            //3 exec
            if(offerExists("titleOffer")==false){
            st.executeUpdate(req);
            System.out.println("Offer ajoutée avec succes!");
            }
            else {
                System.out.println("Offer deja existe!");
            
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Offer_Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     /*===================================================*/
    
     //Fetch
    public List<Offer> afficherOffer(){
        List<Offer> offers = new ArrayList<>();
         //1
         String req = "SELECT * FROM Offer";
        try {
            //2
            Statement st = cnx.createStatement();
            //3
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Offer o = new Offer();
                o.setIdOffer(rs.getInt(1));
                o.setTitleOffer(rs.getString(2));
                o.setDescriptionOffer(rs.getString("descriptionOffer"));
                o.setPrix(rs.getFloat(4));
                o.setDateDebOffer(rs.getDate("dateDebOffer"));
                o.setDateFinOffer(rs.getDate("dateFinOffer"));
                o.setImg(rs.getString("img"));
                offers.add(o);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Offer_Service.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return offers;
    }


     /*===================================================*/
       /*public Boolean supprimerOffer(int id) {
            System.out.println(id);
                
            String req = "DELETE FROM `Offer` WHERE `idOffer`="+id;
         try {
            Statement stm = cnx.createStatement();

            stm = cnx.createStatement();
            int rowsDeleted = stm.executeUpdate(req);
            if (rowsDeleted > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
                   System.err.println (ex.getMessage());

               }
               return false;
       }*/
       
       public boolean supprimerOffer(int id) {
    try {
        // First, delete the reservations associated with the offer
        String deleteReservationsSQL = "DELETE FROM `Reservation_Offer` WHERE `idOffer` = ?";
        PreparedStatement deleteReservationsStmt = cnx.prepareStatement(deleteReservationsSQL);
        deleteReservationsStmt.setInt(1, id);
        int rowsDeletedReservations = deleteReservationsStmt.executeUpdate();

        // Then, delete the offer itself
        String deleteOfferSQL = "DELETE FROM `Offer` WHERE `idOffer` = ?";
        PreparedStatement deleteOfferStmt = cnx.prepareStatement(deleteOfferSQL);
        deleteOfferStmt.setInt(1, id);
        int rowsDeletedOffer = deleteOfferStmt.executeUpdate();

        // Check if any rows were deleted from both tables
        if (rowsDeletedReservations > 0 || rowsDeletedOffer > 0) {
            System.out.println("Offer and associated reservations deleted successfully");
            return true;
        } /*else {
            System.out.println("No rows were deleted");
            return false;
        }*/
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
            }
        return false;
       }


        /*===================================================*/

                                public void modifierOffer(Offer o, int idOfferM) {
                // Convertir la date en une chaîne de caractères au format MySQL
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateDebFormatted = dateFormat.format(o.getDateDebOffer());
                String dateFinFormatted = dateFormat.format(o.getDateFinOffer());

                String req = "UPDATE `Offer` SET `titleOffer` = '" + o.getTitleOffer() + "', " +
                             "`descriptionOffer` = '" + o.getDescriptionOffer() + "', " +
                             "`prix` = " + o.getPrix() + ", " +
                             "`dateDebOffer` = '" + dateDebFormatted + "', " +
                             "`dateFinOffer` = '" + dateFinFormatted + "' " +
                             "`img` = '" + o.getImg() + "' " +
                             "WHERE `idOffer` = " + idOfferM;

                try {
                    Statement stm = cnx.createStatement();
                    int rowsUpdated = stm.executeUpdate(req);

                    if (rowsUpdated > 0) {
                        System.out.println("Offer modifiée avec succès");
                    } else {
                        System.out.println("Aucune modification effectuée. L'offre avec l'ID " + idOfferM + " n'existe pas.");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Offer_Service.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                     /*===================================================*/

                                
                                 public void ModOffer(Offer o) {
                // Convertir la date en une chaîne de caractères au format MySQL
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateDebFormatted = dateFormat.format(o.getDateDebOffer());
                String dateFinFormatted = dateFormat.format(o.getDateFinOffer());

                String req = "UPDATE `Offer` SET " +
             "`titleOffer` = '" + o.getTitleOffer() + "', " +
             "`descriptionOffer` = '" + o.getDescriptionOffer() + "', " +
             "`prix` = " + o.getPrix() + ", " +
             "`dateDebOffer` = '" + dateDebFormatted + "', " +
             "`dateFinOffer` = '" + dateFinFormatted + "', " +
             "`img` = '" + o.getImg() + "' " +
             "WHERE `titleOffer` = '" + o.getTitleOffer() + "'";

                try {
                    Statement stm = cnx.createStatement();
                    int rowsUpdated = stm.executeUpdate(req);

                    if (rowsUpdated > 0) {
                        System.out.println("Offer modifiée avec succès");
                    } 
                } catch (SQLException ex) {
                    Logger.getLogger(Offer_Service.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

                    
                     /*===================================================*/
       
                        public Offer fetchOfferById(int id) {
         String req = "SELECT * FROM `Offer` WHERE `idOffer` ="+id;
        try {
            //2
            Statement st = cnx.createStatement();
            //3
            ResultSet rs = st.executeQuery(req);
            if (rs.next()) {
                Offer o = new Offer();
                o.setIdOffer(rs.getInt(1));
                o.setTitleOffer(rs.getString(2));
                o.setDescriptionOffer(rs.getString("descriptionOffer"));
                o.setPrix(rs.getFloat(4));
                o.setDateDebOffer(rs.getDate("dateDebOffer"));
                o.setDateFinOffer(rs.getDate("dateFinOffer"));
                o.setImg(rs.getString("img"));
                return o;
            }
            else {
            System.out.println("Offer with id " + id + " does not exist.");
            return null;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Offer_Service.class.getName()).log(Level.SEVERE, null, ex);
        }
         return null;
    }
                        
                /*===================================================*/
                        
                        public Integer reservationCount(int id) {
                int totalReservationCount = 0;
                String sql = "SELECT COUNT(r.idReservation) AS totalReservationCount " +
                             "FROM Reservation_Offer r " +
                             "WHERE r.idOffer = ?";

                try (PreparedStatement statement = cnx.prepareStatement(sql)) {
                    statement.setInt(1, id);
                    try (ResultSet rs = statement.executeQuery()) {
                        if (rs.next()) {
                            totalReservationCount = rs.getInt("totalReservationCount");
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Offer_Service.class.getName()).log(Level.SEVERE, null, ex);
                }

                return totalReservationCount;
            }

           /*===================================================*/
                public void OffersWithMostReservations(List<Offer> offers) {
                    int maxReservationCount = 0;
                    int lastReservationCount = 0;
                    Offer offerWithLastReservations = null;
                    boolean test = false;

                    for (Offer offer : offers) {
                        int NBreservation = reservationCount(offer.getIdOffer());

                        if (NBreservation > maxReservationCount) {
                            maxReservationCount = NBreservation;
                        }

                        if (NBreservation >= lastReservationCount) {
                            lastReservationCount = NBreservation;
                            offerWithLastReservations = offer;
                        }
                    }

                    System.out.println("Offer with the most reservations: " + offerWithLastReservations.getTitleOffer());
                    

                    for (Offer offer : offers) {
                        if (offer != offerWithLastReservations) {
                            if (!test) {
                                System.out.println("Offer with the last reservations: " + offer.getTitleOffer());
                                test = true;
                            }
                        }
                    }
                }

                             /*===================================================*/

                public List<Offer> orderOffersByReservationCount() {
    List<Offer> offers = afficherOffer(); // Fetch all offers
    offers.sort(Comparator.comparingInt(o -> -reservationCount(o.getIdOffer())));

     Collections.reverse(offers);

    return offers;
}


             /*===================================================*/

      /*public void applyDiscountAndMakeDecision(List<Offer> offers) {
            
          if (OffersWithMostReservations(offers) ) {
            
            // Apply a 20% discount to the offer's price
            float originalPrice = offer.getPrix();
            float discountedPrice = (float) (originalPrice * 0.8); // 20% discount
            offer.setPrix(discountedPrice);
        }
        
        if (reservationCount(offer.getIdOffer()) < lastReservationCount) {
            // Make a decision based on the last reservation count
            // Add your decision-making logic here
            // For example: set a flag, send a notification, etc.
        }
    }*/

                

    


                        
  
                    
}
