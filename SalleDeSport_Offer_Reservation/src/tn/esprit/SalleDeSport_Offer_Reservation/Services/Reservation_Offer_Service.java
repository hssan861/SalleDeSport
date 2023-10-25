/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.SalleDeSport_Offer_Reservation.Services;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.esprit.SalleDeSport_Offer_Reservation.Entities.CodePromo;
import tn.esprit.SalleDeSport_Offer_Reservation.Entities.Offer;
import tn.esprit.SalleDeSport_Offer_Reservation.Entities.Reservation_Offer;
import tn.esprit.SalleDeSport_Offer_Reservation.Entities.User;
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
        String req = "INSERT INTO `Reservation_Offer`(`dateReservation`, `idUser`, `idOffer`, `codePromo`) VALUES (?, ?, ?,?)";
        
        try (Connection cnx = MyConnection.getInstance().getCnx();
             PreparedStatement R = cnx.prepareStatement(req))
        {
            User user;
            
            R.setObject(1,r.getDateReservation()); 
            R.setInt(2, r.getIdUser());
            R.setInt(3,offer.getIdOffer());
            R.setObject(4, r.getCodePromo().toString());
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
                
                    java.sql.Date  dateReservation = rs.getDate("dateReservation");
                    java.util.Date utilDate = new java.util.Date(dateReservation.getTime());
                    LocalDateTime localDateTime = utilDate.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
                    R.setDateReservation(localDateTime);
            
                R.setIdUser(rs.getInt(3));
                // Fetch the associated Offer and set it
                Offer_Service s = new Offer_Service();
                Offer offer = s.fetchOfferById(rs.getInt(4)); 
                R.setOffer(offer);
                String codePromoString = rs.getString("codePromo");
                R.setCodePromo(CodePromo.valueOf(codePromoString));
                reservations.add(R);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Reservation_Offer_Service.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return reservations;
    }
            /*===================================================*/
   
    public Reservation_Offer fetchWithIdReservation(int idReservation) {
    String req = "SELECT * FROM Reservation_Offer WHERE idReservation = ?";

    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, idReservation);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Reservation_Offer R = new Reservation_Offer();
            R.setIdReservation(rs.getInt(1));
            java.sql.Date  dateReservation = rs.getDate("dateReservation");
                    java.util.Date utilDate = new java.util.Date(dateReservation.getTime());
                    LocalDateTime localDateTime = utilDate.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
                    R.setDateReservation(localDateTime);
            R.setIdUser(rs.getInt(3));
            Offer_Service s = new Offer_Service();
            Offer offer = s.fetchOfferById(rs.getInt(4)); 
            R.setOffer(offer);
            String codePromoString = rs.getString("codePromo");
            R.setCodePromo(CodePromo.valueOf(codePromoString));

            return R;
        } else {
            System.out.println("Reservation with idReservation " + idReservation + " does not exist.");
            return null;
        }
    } catch (SQLException ex) {
        Logger.getLogger(Reservation_Offer_Service.class.getName()).log(Level.SEVERE, null, ex);
        return null; 
    }
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
           
     public Integer reservationAllCount() {
    int totalReservationCount = 0;
    String sql = "SELECT COUNT(idReservation) AS totalReservationCount FROM Reservation_Offer";

    try (PreparedStatement statement = cnx.prepareStatement(sql)) {
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
 public void pdf(){
    try {
            String file = "src/tn/esprit/SalleDeSport_Offer_Reservation/documents/Reservation.pdf";
            Document document = new Document();
          Paragraph para = new Paragraph("List des offer \n");
            PdfWriter.getInstance(document, new FileOutputStream(file));
            
            for (Reservation_Offer r :fetchReservation() ) {
                           para.add( "\n Reservation : \n nomClient=" + r.getIdUser() + "\n Date Reservation =" + r.getDateReservation() + "\n Code Promo utilisé=" + r.getCodePromo() + "\n Offer=" + r.getOffer().getTitleOffer() );
    }
 
            document.open();
            document.add(para);
            document.close();
        } catch (FileNotFoundException ex) {
        } catch (DocumentException ex) {
        }
    }
    
    
}
