/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi_salle_de_sport.Services;

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
import pi_salle_de_sport.Entities.Reservation;
import pi_salle_de_sport.Entities.User;
import pi_salle_de_sport.Utils.MyDB;

/**
 *
 * @author HP
 */
public class ServiceReservation implements IService<Reservation> {
    Connection cnx = MyDB.getInstance().getCnx();

   @Override
public void addReservation(Reservation t) {
    int userId = t.getIdUser().getId_user();
    try {
        // Convert the date to a string in the MySQL format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateResFormatted = dateFormat.format(t.getDateRes());

        // Use a PreparedStatement to safely insert data into the database
        String req = "INSERT INTO `reservation_cours` (`code`, `date_res`, `id_user`) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = cnx.prepareStatement(req);
        preparedStatement.setInt(1, t.getCode());
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
    List<Reservation> Reservations = new ArrayList<>();
    //1
    String req = "SELECT * FROM `reservation_cours`";
    try {
        //2
        Statement st = cnx.createStatement();
        //3
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Reservation reservation = new Reservation();
            reservation.setId(rs.getInt("id"));
            reservation.setDateRes(rs.getDate("date_res"));
            int userId = rs.getInt("id_user");
                UserService userService = new UserService();
                User user = userService.getUserById(userId);
                reservation.setIdUser(user);

            reservation.setCode(rs.getInt("code"));
            Reservations.add(reservation);
        }
    } catch (SQLException ex) {
        Logger.getLogger(IService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return Reservations;
}


    @Override
public Boolean modifier(Reservation r) {
    String requete = "UPDATE `reservation_cours` SET `code`=?, `id_user`=? WHERE id=?";
    try {
        PreparedStatement preparedStatement = cnx.prepareStatement(requete);
        preparedStatement.setInt(1, r.getCode());
        preparedStatement.setInt(2, r.getIdUser().getId_user());
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

        
    

   
    
    

