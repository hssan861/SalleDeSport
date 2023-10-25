package models;

import java.util.Date;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author hama
 */
public class Produit {
      
    private int id;
    private int idAdmin;
    private String titre;
    private String image;
    private Date date;
    private String description;
    private String categorie;
    //private boolean selected;
    private BooleanProperty selected = new SimpleBooleanProperty(false);
    public BooleanProperty selectedProperty() {
        return selected;
    }

    public boolean isSelected() {
        return selected.get();
    }

    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }
    
    // Constructeur par dÃ©faut
    public Produit() {
    }

    public ImageView getImageAsImageView() {
        // Créez un ImageView à partir du nom de fichier d'image
        ImageView imageView = new ImageView(new Image( this.image));
        // Configurez la taille de l'image si nécessaire
        imageView.setFitWidth(100); // Largeur souhaitée
        imageView.setFitHeight(100); // Hauteur souhaitée
        return imageView;
    }

    // Constructeur avec paramÃ¨tres
    

    public Produit(int idAdmin, String titre, String image, Date date, String description, String categorie) {
        this.idAdmin = idAdmin;
        this.titre = titre;
        this.image = image;
        this.date = date;
        this.description = description;
        this.categorie = categorie;
        //this.selected = false;
    }

    public Produit(String titre, String image, Date date, String description, String categorie) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Produit(String titre, Image image, Date date, String description, String categorie) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
    
    /*public void setSelected(boolean isSelected){
        this.selected = isSelected;
    }
    public boolean getSelected(){
        return this.selected;
    }*/

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", idAdmin=" + idAdmin + ", titre=" + titre + ", image=" + image + ", date=" + date + ", description=" + description + ", categorie=" + categorie + '}';
    }



}

  

  

    
