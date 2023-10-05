/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.com.salledesport.Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.esprit.com.salledesport.Entities.Offer;
import tn.esprit.com.salledesport.Utiles.MyConnection;

/**
 *
 * @author chayma2
 */
public class Offer_Service {
        //var
    MyConnection Mycnx = MyConnection.getInstance();
    Connection cnx = Mycnx.getCnx();
    
     /*===================================================*/
    //Add 
    public void ajouterOffer(Offer o){
        //1
        
        String req = "INSERT INTO `Offer`( `titleOffer`, `descriptionOffer`, `prix`) VALUES ('"+o.getTitleOffer()+"','"+o.getDescriptionOffer()+"',"+o.getPrix()+")";
        
        try {
            //2 : Statement : requêtes statiques simples
            Statement st = cnx.createStatement();
            //3 exec
            st.executeUpdate(req);
            System.out.println("Offer ajoutée avec succes!");
            
        } catch (SQLException ex) {
            Logger.getLogger(Offer_Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     /*===================================================*/
    
     //Fetch
    public List<Offer> afficherOffer(){
        List<Offer> offers = new ArrayList<>();
         //1
         String req = "SELECT * FROM Offer";
        try {
            //2
            Statement st = cnx.createStatement();
            //3
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Offer o = new Offer();
                o.setIdOffer(rs.getInt(1));
                o.setTitleOffer(rs.getString(2));
                o.setDescriptionOffer(rs.getString("descriptionOffer"));
                o.setPrix(rs.getFloat(4));
                offers.add(o);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Offer_Service.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return offers;
    }
     /*===================================================*/
       public void supprimerOffer(int id) {
            System.out.println(id);
                try {
            String req = "DELETE FROM `Offer` WHERE `idOffer`="+id;
            Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
            System.out.println("Offer supprimer avec succés");
        } catch (SQLException ex) {
            System.out.println(ex);
            }
        }
       
        /*===================================================*/
       
             public void modifierOffer(Offer o) {    
      try {
           String req="UPDATE `Offer` SET `titleOffer`=?,`descriptionOffer`=?,`prix`=? WHERE `idOffer`="+o.getIdOffer();
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, o.getTitleOffer());
            st.setString(2, o.getDescriptionOffer());
            st.setFloat(3, o.getPrix());
            st.executeUpdate();
            System.out.println("Offer Modifié avec succés");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
  }
    
}
