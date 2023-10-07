/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi_salle_de_sport;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import pi_salle_de_sport.Entities.Categorie;
import pi_salle_de_sport.Entities.Activities;
import pi_salle_de_sport.Entities.Reservation;
import pi_salle_de_sport.Entities.User;
import pi_salle_de_sport.Services.ServiceActivities;
import pi_salle_de_sport.Services.ServiceReservation;
import pi_salle_de_sport.Services.UserService;

/**
 *
 * @author HP
 */
public class Pi_salle_de_sport {

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) throws ParseException {
    //AJOUTER UNE RESERVATION
// Convertir la chaîne de caractères en objet de type Date
 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
Date date_res = dateFormat.parse("2023-09-30 20:30:00");
// Créer une instance de la classe Reservation avec la date de réservation convertie
Reservation R = new Reservation(date_res,2, 98);
ServiceReservation r= new ServiceReservation();
r.addReservation(R);}}
 
 
//ServiceReservation ps = new ServiceReservation();
       
 


        
////        AJOUTER UNE ACTIVITES
//     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//Date date_deb = dateFormat.parse("2023-09-30 15:30:00");
//Date date_fin = dateFormat.parse("2023-09-02 15:30:00");
//
//User u = new User(3,"Said", "Brahmi");
//// Assuming setIdUser exists in your User class
//  // Replace 123 with the actual user ID
//
//Activities act = new Activities("bet", date_deb, date_fin, u, Categorie.Training, "kes'ha", "ssss");
//
//ServiceActivities r = new ServiceActivities();
//r.addReservation(act);}}

        
        
        
        
//        // AFFICHER LES ACTIVITES
//          // Créez une instance de votre service (assumons que c'est votreService)
//        ServiceActivities r= new ServiceActivities();
//        List<Activities> activitiesList = r.afficher();
//        for (Activities activity : activitiesList) {
//    System.out.println("Code : " + activity.getCode());
//    System.out.println("Catégorie : " + activity.getCategorie());
//    System.out.println("Date de début : " + activity.getDateDeb());
//    System.out.println("Date de fin : " + activity.getDateFin());
//    System.out.println("Description : " + activity.getDescription());
//    System.out.println("Coach : " + activity.getIdCoach());
//    System.out.println("Salle : " + activity.getSalle());
//    System.out.println("Titre : " + activity.getTitre());
//    System.out.println("-----------------------------");
//}}}

        
        
        
        
        
        
        
        
//                // MODIFIER ACTIVITES
//               ServiceActivities r= new ServiceActivities();
//      Activities activiteAModifier = new Activities();
//      // Format de date
//    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//    // Date de début
//    Date dateDebut = dateFormat.parse("2023-10-15 14:30:00");
//    activiteAModifier.setDateDeb(dateDebut);
//
//    // Date de fin
//    Date dateFin = dateFormat.parse("2023-10-15 15:30:00");
//    activiteAModifier.setDateFin(dateFin);
//activiteAModifier.setCode(97);  // Remplacez par le code de l'activité que vous souhaitez modifier
//activiteAModifier.setCategorie(pi_salle_de_sport.Entities.Categorie.Aquatique);  // Remplacez par la nouvelle catégorie
////activiteAModifier.setDateDeb(new Date());  // Remplacez par la nouvelle date de début
////activiteAModifier.setDateFin(new Date());  // Remplacez par la nouvelle date de fin
//activiteAModifier.setDescription("Nouvelle description");  // Remplacez par la nouvelle description
//
////User u = new User(1,"kjlnlbli", "hoslknnjnni");
////User user = UserService.getUserById(u);
//User u = new UserService().getUserById(2);  // Assuming this is a non-static method
//
////activiteAModifier.setIdCoach(u);
//// Assuming u is a User object
//activiteAModifier.setIdCoach(u); // Assuming getId_user() returns an integer
//  // Remplacez par le nouvel ID du coach
//activiteAModifier.setSalle("Nouvelle salle");  // Remplacez par la nouvelle salle
//activiteAModifier.setTitre("Nouveau titre");  // Remplacez par le nouveau titre
//
//// Appelez la méthode modifier() pour mettre à jour l'activité
//Boolean modificationReussie = r.modifier(activiteAModifier);
//
//// Vérifiez si la modification a réussi
//if (modificationReussie) {
//    System.out.println("L'activité a été modifiée avec succès.");
//} else {
//    System.out.println("Échec de la modification de l'activité.");
//}}}







        // SUPPEIMER UNE ACTIVITE
//        ServiceActivities r= new ServiceActivities();
//         //Créez une instance de Reservation avec l'ID approprié à supprimer
//        int codeActiviteASupprimer =92 ;  // Remplacez par l'ID de la réservation que vous souhaitez supprimer
//// Créez une instance de Activities que vous souhaitez supprimer
//Activities activiteASupprimer = new Activities();
//activiteASupprimer.setCode(codeActiviteASupprimer);  // Remplacez par le code de l'activité que vous souhaitez supprimer
//
//// Appelez la méthode supprimer() pour supprimer l'activité
//Boolean suppressionReussie = r.supprimer(activiteASupprimer);
//
//// Vérifiez si la suppression a réussi
//if (suppressionReussie) {
//    System.out.println("L'activité a été supprimée avec succès.");
//} else {
//    System.out.println("Échec de la suppression de l'activité.");
//}}}

       
       
       
       
       
       
       
       
//              
//       // AFFICHER UNE RESERVATION
//           //Créez une instance de votre service (assumons que c'est votreService)
//     ServiceReservation r= new ServiceReservation();
//
//        // Appel à la méthode afficher() pour récupérer les réservations
//        List<Reservation> reservations = r.afficher();
//
//         //Affichez les réservations
//        for (Reservation reservation : reservations) {
//            System.out.println("ID : " + reservation.getId());
//            System.out.println("Date de réservation : " + reservation.getDateRes());
//            System.out.println("ID Utilisateur : " + reservation.getIdUser());
//            System.out.println("Code : " + reservation.getCode());
//            System.out.println("-----------------------------");
//        }}}
//







            //AFFICHER UNE RESERVATION
          // Créez une instance de votre service (assumons que c'est votreService)
         /* ServiceReservation r= new ServiceReservation();
         //Appel à la méthode afficher() pour récupérer les réservations
        List<Reservation> reservations = r.afficher();

         //Affichez les réservations
        for (Reservation reservation : reservations) {
            System.out.println("ID : " + reservation.getId());
            System.out.println("Date de réservation : " + reservation.getDateRes());
            System.out.println("ID Utilisateur : " + reservation.getIdUser());
            System.out.println("Code : " + reservation.getCode());
            System.out.println("-----------------------------");
        }}}*/











        
       
        
        
        
        
       // MODIFIER UNE RESERVATION
       
       /*ServiceReservation ps = new ServiceReservation();
          //Créez une instance de Reservation avec les valeurs appropriées
        Reservation reservationAModifier = new Reservation();
        reservationAModifier.setId(20);  // Remplacez par l'ID de la réservation que vous souhaitez modifier
        reservationAModifier.setCode(80);  // Remplacez par le nouveau code
        reservationAModifier.setIdUser(88);  // Remplacez par le nouvel ID utilisateur

        // Appelez la méthode modifier() pour mettre à jour la réservation
        Boolean modificationReussie = ps.modifier(reservationAModifier);

        // Vérifiez si la modification a réussi
        if (modificationReussie) {
            System.out.println("La réservation a été modifiée avec succès.");
        } else {
            System.out.println("Échec de la modification de la réservation.");
        }}}*/
        
        
 

       
//        //SUPPRIMER UNE RESERVATION
//        ServiceReservation psr = new ServiceReservation();
//
//        // Créez une instance de Reservation avec l'ID approprié à supprimer
//        int idReservationASupprimer = 17;  // Remplacez par l'ID de la réservation que vous souhaitez supprimer
//
//        // Créez une instance de Reservation et définissez l'ID
//        Reservation reservationASupprimer = new Reservation();
//        reservationASupprimer.setId(idReservationASupprimer);
//
//        // Appelez la méthode supprimer() pour supprimer la réservation
//        Boolean suppressionReussie = psr.supprimer(reservationASupprimer);
//
//        // Vérifiez si la suppression a réussi
//        if (suppressionReussie) {
//            System.out.println("La réservation a été supprimée avec succès.");
//        } else {
//            System.out.println("Échec de la suppression de la réservation.");
//        }
//    }}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    
        
        
        
        
        
        
        
        
        
        
        
    

