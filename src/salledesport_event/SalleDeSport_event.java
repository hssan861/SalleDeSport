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
import services.ParticipationServices;
import java.text.ParseException;
import models.Participation;

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
         
       //ajout d'un event
       EventServices ps = new EventServices();
       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
       Date dateEvent = dateFormat.parse("2024-06-18");
       /*Event Eventnew = new Event ("match","mazen","defis","boumhal",dateEvent,30,"image");
       
       ps.ajouterEvent(Eventnew);
       */
         //afficher event
        System.out.println(ps.afficherEvent());
        //ajout d'un participant
        Event e = new Event(27,"match","mazen","defis","boumhal",dateEvent,30,"image");
        ParticipationServices pat = new ParticipationServices();
       /* SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
       Date datePart = s.parse("2024-02-19");
       Participation partnew = new Participation(1,e,datePart);
       pat.ajouterParticipation(partnew);*/
     
      //  afficher participation
       //System.out.println(pat.afficherParticipation());
        
        // Create an instance of the Event class with the data you want to update
 /*Event eventToUpdate = new Event();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateEvent = sdf.parse("2023-06-18");
            eventToUpdate.setDateEvent(dateEvent);          
    eventToUpdate.setIdEvent(18); 
    eventToUpdate.setTitreEvent("crossfit");
    eventToUpdate.setNomCoach("ayoub");
    eventToUpdate.setTypeEvent("yoga");
    eventToUpdate.setAdresseEvent("zaghouane");
    eventToUpdate.setPrixEvent(80.0); 
        eventToUpdate.setImgEvent("image");
    // Call the modifierEvent method to update the event
   ps.modifierEvent(eventToUpdate); 
   System.out.println(ps.afficherEvent());*/
   
   
   //suppresseion d'un participation
  /* int idPartDel = 10;
   Participation PartToDelete = new Participation();
   PartToDelete.setIdPart(idPartDel);
   pat.deletePartcipant(PartToDelete);
   System.out.println(pat.afficherParticipation());
   */
   //suppression d'un event
    /* int id=22; 
         Event eventToDelete = new Event();
         eventToDelete.setIdEvent(id);
         ps.deleteEvent(eventToDelete);
   System.out.println(ps.afficherEvent());*/
    
//modification d'une participation
  /* Participation partToUpdate = new Participation();
    partToUpdate.setIdPart(28);    
    partToUpdate.setEvent(e);
    partToUpdate.setIdUser(10); 
 
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date datepart = sdf.parse("2023-06-18");
        partToUpdate.setDatePart(datepart);  
    
    // Call the modifierEvent method to update the event
   pat.modifierParticipation(partToUpdate); 
   System.out.println(pat.afficherParticipation());*/
}}

