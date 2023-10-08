/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi_salle_de_sport.Entities;

/**
 *
 * @author HP
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mohamed
 */
public class User {

   private int id_user ;
    private String nomUser ;
    private String prenomUser ;

    public User() {
    }

    

    public int getId_user() {
        return id_user;
    }

    public String getNomUser() {
        return nomUser;
    }

    public String getPrenomUser() {
        return prenomUser;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

        public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public void setPrenomUser(String prenomUser) {
        this.prenomUser = prenomUser;
    }

    public User(int id_user ,String nomUser, String prenomUser) {  
        this.id_user = id_user;

        this.nomUser = nomUser;
        this.prenomUser = prenomUser;
    }

    @Override
    public String toString() {
        return  nomUser ;
    }

    
    

   
    
}

    


