/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.services;

import Interface.myservice;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import pi.entities.Produit;
import pi.tools.DataS;

public class serviceProduit implements myservice<Produit> {
    private Connection cnx;

    public serviceProduit() {
        // Initialisez la connexion à la base de données dans le constructeur
        cnx = DataS.getInstance().getConnection();
    }

    @Override
    /*public void addProduit(Produit p) {
        try {
            // Convertir la date en une chaîne de caractères au format MySQL
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date= dateFormat.format(p.getDate());

            // Utiliser une requête préparée pour éviter les injections SQL
            String req = "INSERT INTO produit( 'idAdmin', 'titre', 'image', 'date', 'description', 'categorie') VALUES (?,?,?,?,?,?)";

            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, p.getIdAdmin());
            ps.setString(2, p.getTitre());
            ps.setString(3, p.getImage());
            ps.setString(4, date);
            ps.setString(5, p.getDescription());
            ps.setString(6, p.getCategorie());

            ps.executeUpdate();
            System.out.println("Produit ajouté avec succès!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }*/
    public int add(Produit p) {
    try {
        // Convertir la date en une chaîne de caractères au format MySQL
        

        // Utiliser une requête préparée pour éviter les injections SQL
        String req = "INSERT INTO produit(idAdmin, titre, image, date, description, categorie) VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = cnx.prepareStatement(req,Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, p.getIdAdmin());
        ps.setString(2, p.getTitre());
        ps.setString(3, p.getImage());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
        String date = dateFormat.format(p.getDate());
        ps.setString(4, date);
        ps.setString(5, p.getDescription());
        ps.setString(6, p.getCategorie());

        int affectedRows = ps.executeUpdate();
        if(affectedRows > 0){
            System.out.println("Produit ajouté avec succès!");
        }else{
            System.out.println("Erreur est servenue lors de l'ajout d'un prdouit");
        }
        try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return (int)generatedKeys.getLong(1);
            }
            else {
                return -1;
            }
        }catch(SQLException ex){
                ex.printStackTrace();
                return -1;
        }
    } catch (SQLException ex) {
       return -1;
    }
}


    @Override
    public List<Produit> afficher() {
        List<Produit> produits = new ArrayList<>();
        String req = "SELECT * FROM produit";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Produit produit = new Produit();
                produit.setId(rs.getInt("id_prd"));
                produit.setIdAdmin(rs.getInt("idAdmin"));
                produit.setTitre(rs.getString("titre"));
                produit.setImage(rs.getString("image"));
                produit.setDate(rs.getDate("date"));
                produit.setDescription(rs.getString("description"));
                produit.setCategorie(rs.getString("categorie"));
                produits.add(produit);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return produits;
    }

@Override
public Boolean modifier(Produit produit) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String dateFormatted = dateFormat.format(produit.getDate());

    String req = "UPDATE produit SET " +
        "`idAdmin`='" + produit.getIdAdmin() + "', " +
        "`titre`='" + produit.getTitre().replace("'", "''") + "', " +
        "`image`='" + produit.getImage().replace("'", "''") + "', " +
        "`date`='" + dateFormatted + "', " +
        "`description`='" + produit.getDescription().replace("'", "''") + "', " +
        "`categorie`='" + produit.getCategorie().replace("'", "''") + "' " +
        "WHERE `id_prd`=" + produit.getId();

    try {
        Statement stm = cnx.createStatement();
        stm.executeUpdate(req);
        System.out.println("Produit modifié avec succès");
        return true;
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return false;
}

    @Override
    public Boolean supprimer(Produit produit) {
        try {
            Statement stm = cnx.createStatement();
            String req = "DELETE FROM produit WHERE id_prd=" + produit.getId(); // Assurez-vous que la table et la colonne sont correctes
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
