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
import java.time.Instant;
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
public class EventServices {
    
     //var
    Connection cnx = MyConnection.getInstance().getCnx();
    //MyConnection Mycnx = MyConnection.getInstance();
    //Connection cnx = Mycnx.getCnx();;
    
    
    //Add 
    public void ajouterEvent(Event E){
        String req = "INSERT INTO events(titreEvent,nomCoach,typeEvent,AdresseEvent,dateEvent,prixEvent,imgEvent) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, E.getTitreEvent());
            ps.setString(2, E.getNomCoach());
            ps.setString(3, E.getTypeEvent());
            ps.setString(4, E.getAdresseEvent());
           
            LocalDate date = E.getDateEvent();
        ps.setObject(5, java.sql.Date.valueOf(date));
            ps.setDouble(6, E.getPrixEvent());
            ps.setString(7,E.getImgEvent());
            
            ps.executeUpdate();
            System.out.println("Evenement ajoutée avec succes!");
        } catch (SQLException ex) {
            Logger.getLogger(EventServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Fetch
    public List<Event> afficherEvent(){
        List<Event> Events = new ArrayList<>();
         //1
         String req = "SELECT * FROM events";
        try {
            //2
            Statement st = cnx.createStatement();
            //3
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Event E = new Event();
                E.setIdEvent(rs.getInt("idEvent"));
                E.setTitreEvent(rs.getString("titreEvent"));
                E.setNomCoach(rs.getString("nomCoach"));
                E.setTypeEvent(rs.getString("typeEvent"));
                E.setAdresseEvent(rs.getString("adresseEvent"));
                E.setPrixEvent(rs.getDouble("prixEvent"));
                java.sql.Date sqlDate = rs.getDate("dateEvent");
LocalDate localDate = sqlDate.toLocalDate();
E.setDateEvent(localDate);
                //E.setDateEvent(rs.getDate("dateEvent"));
                E.setImgEvent(rs.getString("imgEvent"));
                Events.add(E);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(EventServices.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return Events;
    }
       public void modifierEvent(Event E) {
    try {
        String req = "UPDATE events SET titreEvent=?, nomCoach=?, typeEvent=?, adresseEvent=?, prixEvent=?, dateEvent=?, ImgEvent=? WHERE idEvent=?";
        PreparedStatement ps = cnx.prepareStatement(req);

        ps.setString(1, E.getTitreEvent());
        ps.setString(2, E.getNomCoach());
        ps.setString(3, E.getTypeEvent());
        ps.setString(4, E.getAdresseEvent());
        ps.setDouble(5, E.getPrixEvent());
        
        // Assuming E.getDateEvent() returns a LocalDate
        LocalDate date = E.getDateEvent();
        ps.setObject(6, java.sql.Date.valueOf(date)); // Convert LocalDate to java.sql.Date
        
        ps.setString(7, E.getImgEvent());
        ps.setInt(8, E.getIdEvent());

        int rowsUpdated = ps.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Evenement Modifié avec succès");
        } else {
            System.out.println("Aucun événement n'a été modifié.");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}

     public void deleteEvent(Event E) {
   
    try {
        
        String req = "DELETE FROM events WHERE idEvent ="+E.getIdEvent();
        Statement stm = cnx.createStatement();

            stm = cnx.createStatement();
            int rowsDeleted = stm.executeUpdate(req);
            if (rowsDeleted > 0) {
                System.out.println("Événement supprimé avec succès !");
            } else {
                System.out.println("Aucun événement n'a été supprimé. Vérifiez l'ID de l'événement");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
}

       public Event getEventById(int idEvent) {
    Event event = null;
    String req = "SELECT * FROM events WHERE idEvent = ?";
    try {
        PreparedStatement preparedStatement = cnx.prepareStatement(req);
        preparedStatement.setInt(1, idEvent);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            event = new Event();
            event.setIdEvent(resultSet.getInt("idEvent"));
            event.setTitreEvent(resultSet.getString("titreEvent"));
            event.setNomCoach(resultSet.getString("nomCoach"));
            event.setTypeEvent(resultSet.getString("typeEvent"));
            event.setAdresseEvent(resultSet.getString("adresseEvent"));

            // Convert java.sql.Date to LocalDate
            java.sql.Date sqlDate = resultSet.getDate("dateEvent");
            LocalDate localDate = sqlDate.toLocalDate();
            event.setDateEvent(localDate);

            event.setPrixEvent(resultSet.getDouble("prixEvent"));
            event.setImgEvent(resultSet.getString("imgEvent"));
        }
    } catch (SQLException ex) {
        Logger.getLogger(EventServices.class.getName()).log(Level.SEVERE, null, ex);
    }
    return event;
}
       public List<Event> getAll() {
    List<Event> list = new ArrayList<>();
    try {
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM Event");
        
        EventServices es = new EventServices();

        while (rs.next()) {
            
            Event u = new Event(
                rs.getInt("idEvent"),
                     rs.getString("titreEvent"),
                    rs.getString("nomCoach"),
                    rs.getString("typeEvent"),
                    rs.getString("adresseEvent"),
                     rs.getDate("dateEvent").toLocalDate(),
                rs.getDouble("PrixEvent"),
                rs.getString("imgEvent")
            );
            list.add(u);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return list;
}
}