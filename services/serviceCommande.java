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
import java.util.Date;
import java.util.List;
import pi.entities.Commande;
import pi.entities.Produit;
import pi.tools.DataS;


public class serviceCommande implements myservice<Commande> {
    private final Connection cnx;

    public serviceCommande() {
        // Initialisez la connexion Ã  la base de donnÃ©es dans le constructeur
        cnx = DataS.getInstance().getConnection();
    }

    @Override
    public int add(Commande commande) {
    try {
        // Ã  faire : verifier l'exsitence du produit Ã  ajouter
        // Utiliser une requÃªte prÃ©parÃ©e pour Ã©viter les injections SQL
        String req = "INSERT INTO commande(date, adresse, type) VALUES ( ?, ?, ?)";
        PreparedStatement ps = cnx.prepareStatement(req,Statement.RETURN_GENERATED_KEYS);
      
        ps.setString(1, new SimpleDateFormat("yyyy-MM-dd").format(commande.getDate()));
        ps.setString(2, commande.getAdresse());
        ps.setString(3, commande.getType());

        int affectedRows = ps.executeUpdate();
        if(affectedRows > 0){
            System.out.println("Commande ajoutÃ©e avec succÃ¨s!");
        }else{
            System.out.println("Erreur est rencontree lors de l'ajout d'une commande");
        }
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                int idCommande = (int)generatedKeys.getLong(1);
                /*String req1 = "INSERT into commande_produit(idCmd, idPrd) Value(?,?)";
                PreparedStatement ps1 = cnx.prepareStatement(req,Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, idCommande);
                
                for(Produit p: commande.getProduits()){
                    int idProduit = p.getId();
                    ps.setInt(2, idProduit);
                    // execute query
                }*/
                return idCommande;
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
           
            commande.setDate(rs.getDate("date"));
            commande.setAdresse(rs.getString("adresse"));
            commande.setType(rs.getString("type"));
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
    System.out.println(commande.getAdresse() + " " + commande.getId());
    String req = "UPDATE commande SET " +
       
        "`date`='" + dateFormatted + "', " +
        "`adresse`='" + commande.getAdresse() + "', " +
        "`type`='" + commande.getType() + "' " +
        "WHERE `id`=" + commande.getId();

    try {
        Statement stm = cnx.createStatement();
        stm.executeUpdate(req);
        System.out.println("Commande modifiÃ©e avec succÃ¨s");
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
    
    
    
    
  public Commande findCommandeById(int id) {
    String sql = "SELECT * FROM commande WHERE id = ?";
    try (Connection conn = DataS.getInstance().getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, id);

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
              
                Date date = rs.getDate("date");
                String adresse = rs.getString("adresse");
                String type = rs.getString("type");
             

                // Créez un nouvel objet Commande avec les données récupérées
                Commande commande = new Commande(date, adresse, type);

                return commande;
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }

    return null; // Renvoie null si la commande n'est pas trouvée
}  
   


}

