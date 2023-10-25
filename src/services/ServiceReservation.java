/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import interfaces.IService;
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
import models.Activities;
import models.Reservation;
import models.User;
import util.MyConnection;

/**
 *
 * @author HP
 */
public class ServiceReservation implements IService<Reservation> {
    Connection cnx = MyConnection.getInstance().getCnx();

   @Override
public void addReservation(Reservation t) {
    int userId = t.getIdUser().getId();
    int activities = t.getCode().getCode();

    try {
        // Convert the date to a string in the MySQL format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateResFormatted = dateFormat.format(t.getDateRes());

        // Use a PreparedStatement to safely insert data into the database
        String req = "INSERT INTO `reservation_cours` (`code`, `date_res`, `id_user`) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = cnx.prepareStatement(req);
        preparedStatement.setInt(1, activities);
        preparedStatement.setString(2, dateResFormatted);
        //.setInt(3, t.getIdUser());
         preparedStatement.setInt(3, userId);

        preparedStatement.executeUpdate();
        System.out.println("Reservation added successfully!");
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}


   @Override
public List<Reservation> afficher() {
    List<Reservation> reservations = new ArrayList<>();

    String req = "SELECT * FROM `reservation_cours`";
    try {
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Reservation reservation = new Reservation();
            reservation.setId(rs.getInt("id"));
            reservation.setDateRes(rs.getDate("date_res"));

            int userId = rs.getInt("id_user");
            UserService userService = new UserService();
            User user = userService.getUserById(userId);
            reservation.setIdUser(user);

            int code = rs.getInt("code");
            ServiceActivities ru = new ServiceActivities();
            Activities activity = ru.getActivitiesById(code);

            // Assurez-vous que l'objet d'activité est correctement initialisé
            if (activity != null) {
                reservation.setCode(activity);
                System.out.println("Activité récupérée : " + activity.getTitre());
            } else {
                System.out.println("Aucune activité trouvée pour le code : " + code);
            }

            reservations.add(reservation);
        }
    } catch (SQLException ex) {
        Logger.getLogger(IService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return reservations;
}











//@Override
//public Boolean modifier(Activities t) {
//    int userId = t.getIdCoach().getId_user();
//
//    try {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String dateDebFormatted = dateFormat.format(t.getDateDeb());
//        String dateFinFormatted = dateFormat.format(t.getDateFin());
//
//        String req = "UPDATE `activites` SET " +
//                     "`categorie`=?, " +
//                     "`date_deb`=?, " +
//                     "`date_fin`=?, " +
//                     "`description`=?, " +
//                     "`id_user`=?, " +
//                     "`salle`=?, " +
//                     "`titre`=? " +
//                     "WHERE `code`=?";
//
//        PreparedStatement preparedStatement = cnx.prepareStatement(req);
//        preparedStatement.setString(1, t.getCategorie().toString());
//        preparedStatement.setString(2, dateDebFormatted);
//        preparedStatement.setString(3, dateFinFormatted);
//        preparedStatement.setString(4, t.getDescription().replace("'", "''"));
//        preparedStatement.setInt(5, userId);
//        preparedStatement.setString(6, t.getSalle());
//        preparedStatement.setString(7, t.getTitre().replace("'", "''"));
//        preparedStatement.setInt(8, t.getCode());
//
//        int rowsUpdated = preparedStatement.executeUpdate();
//
//        if (rowsUpdated > 0) {
//            System.out.println("Activity modified successfully.");
//            return true;
//        } else {
//            System.out.println("No activity found with code " + t.getCode() + ". No modification performed.");
//            return false;
//        }
//    } catch (SQLException ex) {
//        System.err.println(ex.getMessage());
//    }
//
//    return false;
//}


    @Override
public Boolean modifier(Reservation r) {
    int userId = r.getIdUser().getId();
    int activities = r.getCode().getCode();
    String requete = "UPDATE `reservation_cours` SET `code`=?, `id_user`=? WHERE id=?";
    try {
        PreparedStatement preparedStatement = cnx.prepareStatement(requete);
        preparedStatement.setInt(1,activities);
        preparedStatement.setInt(2, userId);
        preparedStatement.setInt(3, r.getId());

        int rowsUpdated = preparedStatement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Reservation modified successfully");
            return true;
        } else {
            System.out.println("No reservation found with the given ID");
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return false;
}


   @Override
    public Boolean supprimer(Reservation r) {
           System.out.println(r);
        String req = "DELETE FROM reservation_cours WHERE id=" + r.getId();
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
            throw new RuntimeException(ex);
        }
    }

    
    

  




}

        
    

   
    
    

