/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;
/**
 *
 * @author chayma2
 */
public class Abonnement {
    int idabonement;
    LocalDateTime dateAbonnement;
    Type_abonn typeAbon;
    int idUser;
    int verification_code;
    //private Set<Type_abonn> abonns;

    

    public Abonnement() {
    }

    
    
    public Abonnement(int idUser, int idAbonement, LocalDateTime dateAbonnement, Type_abonn typeAbon , int verification_code) {
        this.idUser = idUser;
        this.idabonement = idAbonement;
        this.dateAbonnement = dateAbonnement;
        this.typeAbon = typeAbon;
        this.verification_code = verification_code;
    }

    public Abonnement(int idUser, LocalDateTime dateAbonnement, Type_abonn typeAbon, int verification_code) {
        this.idUser = idUser;
        this.dateAbonnement = dateAbonnement;
        this.typeAbon = typeAbon;
        this.verification_code = verification_code;

    }

    public Abonnement(LocalDateTime dateAbonnement, int idUser, int verification_code) {
        this.dateAbonnement = dateAbonnement;
        this.idUser = idUser;
        this.verification_code = verification_code;
    }
    
    

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setIdabonement(int idabonement) {
        this.idabonement = idabonement;
    }

    

    public void setDateAbonnement(LocalDateTime dateAbonnement) {
        this.dateAbonnement = dateAbonnement;
    }

    public void setVerification_code(int verification_code) {
        this.verification_code = verification_code;
    }
    

    public void setTypeAbon(Type_abonn typeAbon) {
        this.typeAbon = typeAbon;
    }

    public int getIdUser() {
        return idUser;
    }

    public int getIdabonement() {
        return idabonement;
    }

    public int getVerification_code() {
        return verification_code;
    }


    public LocalDateTime getDateAbonnement() {
        return dateAbonnement;
    }

    public Type_abonn getTypeAbon() {
        return typeAbon;
    }

    @Override
    public String toString() {
        return "Abonnement{" + 
                ", idAbonement=" + idabonement + 
                ", dateAbonnement=" + dateAbonnement + 
                "idUser=" + idUser + 
                ", typeAbon=" + typeAbon.getType() + '}'+ 
                "\n";
    }
    
    
    
    
}
