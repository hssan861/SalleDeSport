/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salledesport_event;

import static java.lang.Math.E;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import models.Event;
import services.EventServices;
import services.ParticipantServices;
import java.text.ParseException;
import models.Participant;

/**
 *
 * @author rayen
 */
public class SalleDeSport_event {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException, SQLException {
        // TODO code application logic here
         //1
       //ajout d'un event
       EventServices ps = new EventServices();
        //afficher 
        //System.out.println(ps.afficherEvent());
        //ajout d'un participant
        ParticipantServices p = new ParticipantServices();
        
       // System.out.println(p.afficherParticipant());
        
        // Create an instance of the Event class with the data you want to update
  /*  Event eventToUpdate = new Event();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateEvent = sdf.parse("2023-06-18");
            eventToUpdate.setDateEvent(dateEvent);          
    eventToUpdate.setIdEvent(1); // Replace 1 with the actual event ID you want to update
    eventToUpdate.setTitreEvent("Camping");
    eventToUpdate.setNomCoach("ayoub");
    eventToUpdate.setTypeEvent("yoga");
    eventToUpdate.setAdresseEvent("zaghouane");
    eventToUpdate.setPrixEvent(50.0); 
    // Call the modifierEvent method to update the event
   ps.modifierEvent(eventToUpdate); 
   System.out.println(ps.afficherEvent());*/
   //int id = 2;
   
   
  /* int idPartDel = 7;
   Participant PartToDelete = new Participant();
   PartToDelete.setIdUser(idPartDel);
   p.deletePartcipant(PartToDelete);
   System.out.println(p.afficherParticipant());
   */
   
     int id= 2; 
         Event eventToDelete = new Event();
         eventToDelete.setIdEvent(id);
         ps.deleteEvent(eventToDelete);
   System.out.println(ps.afficherEvent());
}
}

