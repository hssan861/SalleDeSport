/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.salledesport_offre;

/**
 *
 * @author USER
 */
public class Admin {
    private int idAdmin ;
    private String usernameAdmin ;
    private String emailAdmin ;
    private String mdpAdmin ;
    private String imgAdmin;



    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getUsernameAdmin() {
        return usernameAdmin;
    }

    public void setUsernameAdmin(String usernameAdmin) {
        this.usernameAdmin = usernameAdmin;
    }

    public String getEmailAdmin() {
        return emailAdmin;
    }

    public void setEmailAdmin(String emailAdmin) {
        this.emailAdmin = emailAdmin;
    }

    public String getMdpAdmin() {
        return mdpAdmin;
    }

    public void setMdpAdmin(String mdpAdmin) {
        this.mdpAdmin = mdpAdmin;
    }

    public String getImgAdmin() {
        return imgAdmin;
    }

    public void setImgAdmin(String imgAdmin) {
        this.imgAdmin = imgAdmin;
    }

    @Override
    public String toString() {
        return "Admin{" + "idAdmin=" + idAdmin + ", usernameAdmin=" + usernameAdmin + ", emailAdmin=" + emailAdmin + ", mdpAdmin=" + mdpAdmin + ", imgAdmin=" + imgAdmin + '}';
    }

    
         
    
    
}
