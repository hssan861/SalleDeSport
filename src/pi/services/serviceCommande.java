/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.services;



/**
 *
 * @author hama
 */
import Interface.myservice;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import pi.entities.Commande;
import pi.tools.DataS;


public class serviceCommande implements myservice<Commande> {
    private final Connection cnx;

    public serviceCommande() {
        // Initialisez la connexion à la base de données dans le constructeur
        cnx = DataS.getInstance().getConnection();
    }

    @Override
    public int add(Commande commande) {
    try {
        // à faire : verifier l'exsitence du produit à ajouter
        // Utiliser une requête préparée pour éviter les injections SQL
        String req = "INSERT INTO commande(id_prd, date, adresse, type, id_user) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = cnx.prepareStatement(req,Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, commande.getIdPrd());
        ps.setString(2, new SimpleDateFormat("yyyy-MM-dd").format(commande.getDate()));
        ps.setString(3, commande.getAdresse());
        ps.setString(4, commande.getType());
        ps.setInt(5, commande.getIdUser());

        int affectedRows = ps.executeUpdate();
        if(affectedRows > 0){
            System.out.println("Commande ajoutée avec succès!");
        }else{
            System.out.println("Erreur est rencontree lors de l'ajout d'une commande");
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
        ex.printStackTrace();
        return -1;
    }
}

    @Override
    public List<Commande> afficher() {
    List<Commande> commandes = new ArrayList<>();
    String req = "SELECT * FROM commande";
    try {
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Commande commande = new Commande();
            commande.setId(rs.getInt("id"));
            commande.setIdPrd(rs.getInt("id_prd"));
            commande.setDate(rs.getDate("date"));
            commande.setAdresse(rs.getString("adresse"));
            commande.setType(rs.getString("type"));
            commande.setIdUser(rs.getInt("id_user"));
            commandes.add(commande);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return commandes;
}


 @Override
public Boolean modifier(Commande commande) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String dateFormatted = dateFormat.format(commande.getDate());

    String req = "UPDATE commande SET " +
        "`id_prd`='" + commande.getIdPrd() + "', " +
        "`date`='" + dateFormatted + "', " +
        "`adresse`='" + commande.getAdresse().replace("'", "''") + "', " +
        "`type`='" + commande.getType().replace("'", "''") + "', " +
        "`id_user`='" + commande.getIdUser() + "' " +
        "WHERE `id`=" + commande.getId();

    try {
        Statement stm = cnx.createStatement();
        stm.executeUpdate(req);
        System.out.println("Commande modifiée avec succès");
        return true;
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return false;
}


    @Override
    public Boolean supprimer(Commande commande) {
    System.out.println(commande);
    String req = "DELETE FROM commande WHERE id=" + commande.getId(); // Assurez-vous que la table et la colonne sont correctes

    try {
        Statement stm = cnx.createStatement();
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

