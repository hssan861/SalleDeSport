package pi.entities;

import java.util.Date;

/**
 *
 * @author hama
 */


import java.util.Date;
import java.util.List;

public class Commande {
    private int id;
   
    private Date date;
    private String adresse;
    private String type;
    List<Produit> produits;

    // Constructeur par dÃ©faut
    public Commande() {
    }

    // Constructeur avec paramÃ¨tres
    public Commande( Date date, String adresse, String type) {
       
      
        this.date = date;
        this.adresse = adresse;
        this.type = type;
    }

    public Commande(int i, Date date, String adresse, String type, int i0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }




    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", date=" + date + ", adresse=" + adresse + ", type=" + type + ", id_user=" + '}';
    }
    
}
    
    