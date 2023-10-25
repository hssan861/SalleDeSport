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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Event;
import models.Participant;
import util.MyConnection;

/**
 *
 * @author rayen
 */
public class ParticipantServices {
     //var
    Connection cnx = MyConnection.getInstance().getCnx();
    //MyConnection Mycnx = MyConnection.getInstance();
    //Connection cnx = Mycnx.getCnx();;
    
    
    //Add 
    public void ajouterParticipant(Participant P){
        String req = "INSERT INTO 'participant'('numTel') VALUES (?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            //ps.setInt(1, P.getIdUser());
            ps.setInt(2, P.getNumTel());
            //ps.setInt(3,getIdEvent);
            ps.executeUpdate();
            System.out.println("Participant ajoutée avec succes!");
        } catch (SQLException ex) {
            Logger.getLogger(ParticipantServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Fetch
    public List<Participant> afficherParticipant(){
        List<Participant> Participants = new ArrayList<>();
         //1
         String req = "SELECT * FROM participant";
        try {
            //2
            Statement st = cnx.createStatement();
            //3
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Participant P = new Participant();
                P.setIdUser(rs.getInt("idUser"));//(rs.getInt("id"));
                P.setNumTel(rs.getInt("numTel"));
                P.setIdEvent(rs.getInt("idEvent"));
                Participants.add(P);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ParticipantServices.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return Participants;
    }
 
    public void deletePartcipant(Participant P) {
   
    try {
        
        String req = "DELETE FROM participant WHERE idUser ="+P.getIdUser();
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
