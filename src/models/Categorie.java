/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 
 * @author Lenovo
 */
public class Categorie {
   private int IdCategorie;
   private String NomCategorie;
   private String DescriptionCategorie;

    public Categorie() {
    }

    public Categorie(String NomCategorie, String DescriptionCategorie) {
        this.NomCategorie = NomCategorie;
        this.DescriptionCategorie = DescriptionCategorie;
    }
   

    public Categorie(int IdCategorie, String NomCategorie, String DescriptionCategorie) {
        this.IdCategorie = IdCategorie;
        this.NomCategorie = NomCategorie;
        this.DescriptionCategorie = DescriptionCategorie;
    }

    public int getIdCategorie() {
        return IdCategorie;
    }

    public void setIdCategorie(int IdCategorie) {
        this.IdCategorie = IdCategorie;
    }

    public String getNomCategorie() {
        return NomCategorie;
    }

    public void setNomCategorie(String NomCategorie) {
        this.NomCategorie = NomCategorie;
    }

    public String getDescriptionCategorie() {
        return DescriptionCategorie;
    }

    public void setDescriptionCategorie(String DescriptionCategorie) {
        this.DescriptionCategorie = DescriptionCategorie;
    }

    @Override
    public String toString() {
        return "Categorie{" + "IdCategorie=" + IdCategorie + ", NomCategorie=" + NomCategorie + ", DescriptionCategorie=" + DescriptionCategorie + '}';
    }

}