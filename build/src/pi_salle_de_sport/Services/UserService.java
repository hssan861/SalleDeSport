/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi_salle_de_sport.Services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pi_salle_de_sport.Entities.User;
import pi_salle_de_sport.Utils.MyDB;
/**package services;


 *
 * @author HP
 */



public class UserService {
   Connection cnx = MyDB.getInstance().getCnx();

    
     public User getUserById(int userId) {
        User user = null;
        String req = "SELECT * FROM `user` WHERE `id_user` = ?";
        try {
            PreparedStatement preparedStatement = cnx.prepareStatement(req);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId_user(resultSet.getInt("id_user"));
                user.setNomUser(resultSet.getString("Nom"));
                user.setPrenomUser(resultSet.getString("Prenom"));
                //  user.setMdp(resultSet.getString("Mdp"));
                //user.setEmailUser(resultSet.getString("Email"));
                //  user.setImg(resultSet.getString("Img"));
                //user.setAgeUser(resultSet.getInt("Age"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

}