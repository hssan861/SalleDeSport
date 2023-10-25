/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import java.util.Date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;

/**
 *
 * @author hama
 */
public class produit_commande {
    private int id;
    private int id_prd; // Clé étrangère vers la table Produit
    private int id_cmd;//CL2é tableau commande 
    private Date date;

    public produit_commande() {
    }

    public produit_commande(int id, int id_prd, int id_cmd, Date date) {
        this.id = id;
        this.id_prd = id_prd;
        this.id_cmd = id_cmd;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_prd() {
        return id_prd;
    }

    public void setId_prd(int id_prd) {
        this.id_prd = id_prd;
    }

    public int getId_cmd() {
        return id_cmd;
    }

    public void setId_cmd(int id_cmd) {
        this.id_cmd = id_cmd;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "produit_commande{" + 
                "id=" + id + 
                ", id_prd=" + id_prd + 
                ", id_cmd=" + id_cmd + 
                ", date=" + date + "}"+
                "\n";
    }
    
}
