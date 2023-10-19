/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  service;




import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Categorie;
import models.Equipement;
import util.MyConnection;
import service.CategorieService;

/**
 *
 * @author Lenovo
 */
public class EquipementService  {
    
    //var
     private MyConnection myConnection = MyConnection.getInstance();
     private Connection connection = myConnection.getCnx();

    
    
    public void ajouterEquipement (Equipement E){
        // Check si la categorie  existe avant l insersion de equipement
        int CategorieId = E.getCategorie().getIdCategorie();
        CategorieService cas =new CategorieService();
        if (cas.CategorieExists(CategorieId)) {
        String req = "INSERT INTO `equipement`( `NomEquipement`, `Quantite`, `DateAchat`, `PrixAchat`, `IdCategorie`) VALUES (?,?,?,?,?)";
        try {
             PreparedStatement preparedStatement = connection.prepareStatement(req);
           
        preparedStatement.setString(1, E.getNomEquipement());
         preparedStatement.setInt(2, E.getQuantite());
           // ps.setDate(3, (java.sql.Date) E.getDateAchat());
          preparedStatement.setString(3, E.getDateAchat());
           preparedStatement.setFloat(4,  E.getPrixAchat());
          preparedStatement.setInt(5, CategorieId);
          int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Equipement ajoutée avec succès!");
            } else {
                System.out.println("ajout d equipement a échoué.");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EquipementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
    
     public List<Equipement> afficherEquipement() {
    List<Equipement> equipements = new ArrayList<>();
    String req = "SELECT * FROM `equipement`";
    try {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(req);
        while (resultSet.next()) {
            Equipement equipement = new Equipement();
            equipement.setIdEquipement(resultSet.getInt("IdEquipement"));
           equipement.setNomEquipement(resultSet.getString("NomEquipement"));
         equipement.setQuantite(resultSet.getInt("Quantite"));
          equipement.setDateAchat(resultSet.getString("DateAchat"));
          equipement.setPrixAchat(resultSet.getFloat("PrixAchat"));
            
            // Fetch the associated Categorie for the Equipement
            int IdCategorie = resultSet.getInt("IdCategorie");
            CategorieService categorieService = new CategorieService();
            Categorie categorie = categorieService.getCategorieById(IdCategorie);
            equipement.setCategorie(categorie);

           equipements.add(  equipement);
        }
    } catch (SQLException ex) {
        Logger.getLogger(EquipementService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return equipements;
}
     public void supprimerEquipement(Equipement e) {
                try {
            String req = "DELETE FROM `equipement` WHERE IdEquipement=?";
            PreparedStatement preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1,e.getIdEquipement());
           

           preparedStatement.executeUpdate();
            System.out.println("Equipement supprimer avec succés");
             } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
        
        
       public void modifierEquipement(Equipement e) {
            // Check si la categorie  existe avant l insersion de equipement
        int CategorieId = e.getCategorie().getIdCategorie();
        CategorieService cas =new CategorieService();
        if (cas.CategorieExists(CategorieId)) {
    
        String req = "UPDATE `equipement` SET `NomEquipement`=?, `Quantite`=?, `DateAchat`=?, `PrixAchat`=? WHERE `IdEquipement`=?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(req);
        
            preparedStatement.setString(1, e.getNomEquipement());
            preparedStatement.setInt(2, e.getQuantite());
        preparedStatement.setString(3, e.getDateAchat());
        preparedStatement.setFloat(4, e.getPrixAchat());
       
        preparedStatement.setInt(5, e.getIdEquipement());

       int rowsUpdated = preparedStatement.executeUpdate();
       if (rowsUpdated > 0) {
            System.out.println("Equipement modifier avec succée!");
        } else {
            System.out.println("modification a echouée.");
        }
        
    } catch (SQLException ex) {
         Logger.getLogger(EquipementService.class.getName()).log(Level.SEVERE, null, ex);
    }
     } else {
        System.out.println("pas de categorie avec cette ID.");
    }
    
}
    
    
    
}
