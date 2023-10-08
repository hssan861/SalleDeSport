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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pi_salle_de_sport.Entities.Activities;
import pi_salle_de_sport.Entities.Categorie;
import pi_salle_de_sport.Entities.Reservation;
import pi_salle_de_sport.Entities.User;
import pi_salle_de_sport.Utils.MyDB;

/**
 *
 * @author HP
 */
public class ServiceActivities implements IService <Activities> {
Connection cnx = MyDB.getInstance().getCnx();
   @Override
public void addReservation(Activities t) {
    int userId = t.getIdCoach().getId_user();

    try {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateDebFormatted = dateFormat.format(t.getDateDeb());
        String dateFinFormatted = dateFormat.format(t.getDateFin());

        String req = "INSERT INTO `activites` (`categorie`, `date_deb`, `date_fin`, `description`, `id_user`, `salle`, `titre`) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement p = cnx.prepareStatement(req);
        p.setString(1, t.getCategorie().toString());
        p.setString(2, dateDebFormatted);
        p.setString(3, dateFinFormatted);
        p.setString(4, t.getDescription().replace("'", "''"));
        p.setInt(5, userId);
        p.setString(6, t.getSalle());
        p.setString(7, t.getTitre().replace("'", "''"));

        p.executeUpdate();
        System.out.println("Reservation added successfully!");
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}


    @Override
    public List<Activities> afficher() {
        List<Activities> activitiesList = new ArrayList<>();
        String req = "SELECT * FROM `activites`";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Activities a = new Activities();
                // User categ_gall = User.valueOf(id_coach);

                a.setCode(rs.getInt("code"));
                a.setCategorie(Categorie.valueOf(rs.getString("categorie")));
                a.setDateDeb(rs.getDate("date_deb"));
                a.setDateFin(rs.getDate("date_fin"));
                a.setDescription(rs.getString("description"));
                int userId = rs.getInt("id_user");
                UserService userService = new UserService();
                User user = userService.getUserById(userId);
                a.setIdCoach(user);
                a.setSalle(rs.getString("salle"));
                a.setTitre(rs.getString("titre"));
                activitiesList.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(IService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return activitiesList;
    }

    

    @Override
public Boolean modifier(Activities t) {
    int userId = t.getIdCoach().getId_user();

    try {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateDebFormatted = dateFormat.format(t.getDateDeb());
        String dateFinFormatted = dateFormat.format(t.getDateFin());

        String req = "UPDATE `activites` SET " +
                     "`categorie`=?, " +
                     "`date_deb`=?, " +
                     "`date_fin`=?, " +
                     "`description`=?, " +
                     "`id_user`=?, " +
                     "`salle`=?, " +
                     "`titre`=? " +
                     "WHERE `code`=?";

        PreparedStatement preparedStatement = cnx.prepareStatement(req);
        preparedStatement.setString(1, t.getCategorie().toString());
        preparedStatement.setString(2, dateDebFormatted);
        preparedStatement.setString(3, dateFinFormatted);
        preparedStatement.setString(4, t.getDescription().replace("'", "''"));
        preparedStatement.setInt(5, userId);
        preparedStatement.setString(6, t.getSalle());
        preparedStatement.setString(7, t.getTitre().replace("'", "''"));
        preparedStatement.setInt(8, t.getCode());

        int rowsUpdated = preparedStatement.executeUpdate();

        if (rowsUpdated > 0) {
            System.out.println("Activity modified successfully.");
            return true;
        } else {
            System.out.println("No activity found with code " + t.getCode() + ". No modification performed.");
            return false;
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }

    return false;
}

    @Override
   public Boolean supprimer(Activities r) {
           System.out.println(r);
String req = "DELETE FROM activites WHERE code=" + r.getCode();
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

    

  

