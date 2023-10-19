/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Event;
import models.Participation;
import util.MyConnection;

/**
 *
 * @author rayen
 */
public class ParticipationServices {
      Connection cnx = MyConnection.getInstance().getCnx();
   
    //Add 
    public void ajouterParticipation(Participation P){
        String req = "INSERT INTO participation( idEvent,Nom, Prenom, Ntel, DatePart) VALUES (?,?,?,?,?)";
        int idEvent = P.getEvent().getIdEvent();
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
             ps.setInt(1,idEvent);
            ps.setString(2, P.getNom());
            ps.setString(3,P.getPrenom());
            ps.setString(4,P.getNtel());
            LocalDate date = P.getDatePart();
        ps.setObject(5, java.sql.Date.valueOf(date));
            
             int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Participation ajoutée avec succès!");
            } else {
                System.out.println("Failed to add participation.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParticipationServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Fetch
    public List<Participation> afficherParticipation(){
        List<Participation> Participations = new ArrayList<>();
         
         String req = "SELECT * FROM participation";
        try {
            
            Statement st = cnx.createStatement();
            
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Participation P = new Participation();
                P.setIdPart(rs.getInt("idPart"));
                int idEvent = rs.getInt("idEvent");
                EventServices es = new EventServices();
                Event event = es.getEventById(idEvent);
                P.setIdEvent(event);
               P.setNom(rs.getString("Nom"));
                P.setPrenom(rs.getString("Prenom"));
                 P.setNtel(rs.getString("Ntel"));
                 P.setDatePart(rs.getDate("DatePart").toLocalDate());
              
                Participations.add(P);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ParticipationServices.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return Participations;
    }
    //modifier
     public void modifierParticipation(Participation p) {
          try {
            String req = "UPDATE participation SET Nom=?,Prenom=?,Ntel=?,DatePart=? WHERE idPart=?";
       // int idEvent = p.setEvent().setIdEvent();

            PreparedStatement ps = cnx.prepareStatement(req);
           // ps.setInt(1, idEvent);
            ps.setString(1,p.getNom());
            ps.setString(2,p.getPrenom());
            ps.setString(3,p.getNtel());
           LocalDate date = p.getDatePart();
        ps.setObject(4, java.sql.Date.valueOf(date));
        ps.setInt(5, p.getIdPart());

        int rowsUpdated = ps.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Participation Modifié avec succès");
        } else {
            System.out.println("Aucune Participation n'a été modifié.");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}
 //supprimer
    public void deletePartcipant(Participation P) {
   
    try {
        
        String req = "DELETE FROM participation WHERE idPart ="+P.getIdPart();
        Statement stm = cnx.createStatement();

            stm = cnx.createStatement();
            int rowsDeleted = stm.executeUpdate(req);
            if (rowsDeleted > 0) {
                System.out.println("supp ok");
            } else {
                System.out.println("supp not ok");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
}

}
