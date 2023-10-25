package services;

import Interfaces.myservice;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Produit;
import util.MyConnection;

public class serviceProduit implements myservice<Produit> {

    private Connection cnx;

    public serviceProduit() {
        // Initialisez la connexion Ã  la base de donnÃ©es dans le constructeur
        cnx = MyConnection.getInstance().getCnx();
    }

    @Override
    public void insert(int idc, int idp) {

    }
    public Map<String, Double> calculatePercentageByCategory() {
        Map<String, Long> categoryCount = new HashMap<>();
        Map<String, Double> categoryPercentage = new HashMap();

        try {
            // Exécutez une requête SQL pour récupérer les données nécessaires
            String sql = "SELECT p.categorie, COUNT(pc.id_cmd) FROM produit p " +
                    "INNER JOIN  produit_commande pc ON p.id_prd = pc.id_prd " +
                    "GROUP BY p.categorie";

            PreparedStatement statement = cnx.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String categorie = resultSet.getString(1);
                Long count = resultSet.getLong(2);
                categoryCount.put(categorie, count);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        long totalCommandes = getTotalCommandCount();

        for (Map.Entry<String, Long> entry : categoryCount.entrySet()) {
            double percentage = (entry.getValue() * 100.0) / totalCommandes;
            categoryPercentage.put(entry.getKey(), percentage);
        }

        return categoryPercentage;
    }

    // Méthode pour obtenir le nombre total de commandes
    private long getTotalCommandCount() {
        try {
            String sql = "SELECT COUNT(*) FROM commande";
            PreparedStatement statement = cnx.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    /*public void addProduit(Produit p) {
        try {
            // Convertir la date en une chaÃ®ne de caractÃ¨res au format MySQL
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date= dateFormat.format(p.getDate());

            // Utiliser une requÃªte prÃ©parÃ©e pour Ã©viter les injections SQL
            String req = "INSERT INTO produit( 'idAdmin', 'titre', 'image', 'date', 'description', 'categorie') VALUES (?,?,?,?,?,?)";

            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, p.getIdAdmin());
            ps.setString(2, p.getTitre());
            ps.setString(3, p.getImage());
            ps.setString(4, date);
            ps.setString(5, p.getDescription());
            ps.setString(6, p.getCategorie());

            ps.executeUpdate();
            System.out.println("Produit ajoutÃ© avec succÃ¨s!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }*/
    public int add(Produit p) {
        try {
            // Convertir la date en une chaÃ®ne de caractÃ¨res au format MySQL

            // Utiliser une requÃªte prÃ©parÃ©e pour Ã©viter les injections SQL
            String req = "INSERT INTO produit(idAdmin, titre, image, date, description, categorie) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = cnx.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, p.getIdAdmin());
            ps.setString(2, p.getTitre());
            ps.setString(3, p.getImage());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
            String date = dateFormat.format(p.getDate());
            ps.setString(4, date);
            ps.setString(5, p.getDescription());
            ps.setString(6, p.getCategorie());

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Produit ajouté avec succé¨!");
            } else {
                System.out.println("Erreur est servenue lors de l'ajout d'un prdouit");
            }
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return (int) generatedKeys.getLong(1);
                } else {
                    return -1;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                return -1;
            }
        } catch (SQLException ex) {
            return -1;
        }
    }
    @Override
    public List<Produit> afficherproduit(int id) {
        try {
            List<Produit> produits = new ArrayList<>();
            String req = "SELECT * FROM produit p LEFT JOIN produit_commande pc ON pc.id_prd = p.id_prd WHERE pc.id_cmd = " + id;
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
            return produits;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    @Override
    public List<Produit> afficher() {
        
        try {
            List<Produit> produits = new ArrayList<>();
            String req = "SELECT * FROM produit";
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
            return produits;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public Boolean modifier(Produit produit) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateFormatted = dateFormat.format(produit.getDate());
            System.out.println(produit.getImage());
            String req = "UPDATE produit SET "
                    + "`idAdmin`='" + produit.getIdAdmin() + "', "
                    + "`titre`='" + produit.getTitre() + "', "
                    + "`image`='" + produit.getImage() + "', "
                    + "`date`='" + dateFormatted + "', "
                    + "`description`='" + produit.getDescription() + "', "
                    + "`categorie`='" + produit.getCategorie() + "' "
                    + "WHERE `id_prd`=" + produit.getId();
            Statement stm = cnx.createStatement();
            stm.execute(req);
            return true;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public Boolean DeleteCommandeproduit(int idc, int idp) {
        return null;
    }

    @Override
    public Boolean supprimer(Produit produit) {
        try {
            // Supprimez les lignes de la table produit_commande
            String deleteCommandeProduit = "DELETE FROM produit_commande WHERE id_prd=" + produit.getId();
            Statement stmt = cnx.createStatement();
            stmt.executeUpdate(deleteCommandeProduit);
            stmt.close();

            // Supprimez la ligne de la table produit
            String deleteProduit = "DELETE FROM produit WHERE id_prd=" + produit.getId();
            Statement stm = cnx.createStatement();
            int rowsDeleted = stm.executeUpdate(deleteProduit);

            if (rowsDeleted > 0) {
                // Si tout s'est bien passé, validez la transaction

                return true;
            } else {
                // Si rien n'a été supprimé, annulez la transaction

                return false;
            }
        } catch (SQLException ex) {
            try {
                if (cnx != null) {
                    cnx.rollback();
                }
            } catch (SQLException rollbackEx) {
                // Gestion de l'exception de rollback
                rollbackEx.printStackTrace();
            }
            throw new RuntimeException(ex);
        } finally {
            try {
                if (cnx != null) {
                    cnx.setAutoCommit(true);

                }
            } catch (SQLException closeEx) {
                // Gestion de l'exception de fermeture de la connexion
                closeEx.printStackTrace();
            }
        }
    }

    public Produit findProduitById(int productId) {
        
        try {
            
            String query = "SELECT * FROM produit WHERE id_prd = ?";
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setInt(1, productId); // Set the parameter to the provided product ID
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Produit produit = new Produit();
                produit.setId(rs.getInt("id_prd"));
                produit.setIdAdmin(rs.getInt("idAdmin"));
                produit.setTitre(rs.getString("titre"));
                produit.setImage(rs.getString("image"));
                produit.setDate(rs.getDate("date"));
                produit.setDescription(rs.getString("description"));
                produit.setCategorie(rs.getString("categorie"));
                return produit;
            }
        } catch (Exception ex) {
            return null;
        }
        return null;
    }

}
