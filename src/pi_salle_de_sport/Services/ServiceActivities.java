/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi_salle_de_sport.Services;

import interfaces.IService;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pi_salle_de_sport.Entities.Activities;
import pi_salle_de_sport.Entities.Reservation;
import pi_salle_de_sport.Utils.MyDB;

/**
 *
 * @author HP
 */
public class ServiceActivities implements IService <Activities> {
Connection cnx = MyDB.getInstance().getCnx();
    @Override
    public void addReservation(Activities t) {
         
 try {
     // Convertir la date en une chaîne de caractères au format MySQL
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
String dateDebFormatted = dateFormat.format(t.getDateDeb());
String dateFinFormatted = dateFormat.format(t.getDateFin());

// Utiliser la date formatée dans votre requête SQL
String req = "INSERT INTO `activites` (`categorie`, `date_deb`, `date_fin`, `description`, `id_coach`, `salle`, `titre`) " +
        "VALUES ('" + t.getCategorie() + "', '" + dateDebFormatted + "', '" +
        dateFinFormatted+ "', '" + t.getDescription().replace("'", "''") + "', '" + 
        t.getIdCoach() + "', '" + t.getSalle() + "', '" + t.getTitre().replace("'", "''") + "')";



            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Reservation Added successfully!");
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
            a.setCode(rs.getInt("code"));
            a.setCategorie(rs.getString("categorie"));
            a.setDateDeb(rs.getDate("date_deb"));
            a.setDateFin(rs.getDate("date_fin"));
            a.setDescription(rs.getString("description"));
            a.setIdCoach(rs.getInt("id_coach"));
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
           SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
String dateDebFormatted = dateFormat.format(t.getDateDeb());
String dateFinFormatted = dateFormat.format(t.getDateFin());
        String req = "UPDATE `activites` SET " +
        "`categorie`='" + t.getCategorie() + "', " +
        "`date_deb`='" + dateDebFormatted + "', " +
        "`date_fin`='" + dateFinFormatted + "', " +
        "`description`='" + t.getDescription().replace("'", "''") + "', " +
        "`id_coach`='" + t.getIdCoach() + "', " +
        "`salle`='" + t.getSalle() + "', " +
        "`titre`='" + t.getTitre().replace("'", "''") + "' " +
        "WHERE `code`=" + t.getCode();

        try {
           Statement stm = cnx.createStatement();

            stm = cnx.createStatement();
            stm.executeUpdate(req);
            System.out.println("reservation modifiée avec succés");
            return true;
        } catch (SQLException ex) {
            System.err.println (ex.getMessage());
           
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

    

  

