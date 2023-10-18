package pi.GUI;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import pi.entities.Produit;
import pi.services.serviceProduit;

public class ModifierProduitController implements Initializable{
    
    private serviceProduit sp;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.sp = new serviceProduit();
    } 

    @FXML
    private Button btnmodifier;

    @FXML
    private TextField categorieField;

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField idProduitField;


    @FXML
    private ImageView imageView;

    @FXML
    private Button insert_image;

    @FXML
    private DatePicker tfdate;

    @FXML
    private TextField titreField;
    
    private int idAdmin;

    @FXML
void modifPrdt(ActionEvent event) {
    // Créez un objet Produit pour stocker les données de mise à jour
    Produit pdt = new Produit();
    
    // Récupérez les données de l'interface utilisateur
    LocalDate selectedDate = tfdate.getValue();
    String titre = titreField.getText();
    String categorie = categorieField.getText();
    String description = descriptionField.getText();

    if (selectedDate == null || titre.isEmpty() || categorie.isEmpty() || description.isEmpty()) {
        // Si l'une des données obligatoires est manquante, affichez un message d'erreur.
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs obligatoires.");
        alert.showAndWait();
        return; // Sortez de la méthode sans effectuer la mise à jour.
    }

    // Convertissez la date sélectionnée en un objet Date
    Date date = Date.from(selectedDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    
    // Remplissez l'objet Produit avec les données de mise à jour
    pdt.setId(Integer.parseInt(this.idProduitField.getText()));
    pdt.setIdAdmin(this.idAdmin);
    pdt.setTitre(titre);
    pdt.setCategorie(categorie);
    pdt.setDescription(description);
    pdt.setDate(date);
    Image image = imageView.getImage();
    String imagePath = (image != null) ? image.impl_getUrl() : "";
    pdt.setImage(imagePath);
    // Ajoutez la logique de mise à jour (appel à la méthode de service)
    Boolean result = this.sp.modifier(pdt);


    // Affichez un message en fonction du résultat de la mise à jour
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Information Dialog");
    alert.setHeaderText(null);

    if (result) {
        alert.setContentText("Produit mis à jour avec succès !");
    } else {
        alert.setContentText("Échec de la mise à jour du produit.");
    }

    alert.showAndWait();
}
    
    @FXML
    void trouverProduit(ActionEvent event) {
        
   try {
        int produitId = Integer.parseInt(this.idProduitField.getText());

        Produit prdt = this.sp.findProduitById(produitId);

        if (prdt != null) {
            this.categorieField.setText(prdt.getCategorie());
            this.titreField.setText(prdt.getTitre());
            this.descriptionField.setText(prdt.getDescription());
            // this.tfdate.set(prdt.getCategorie());
            System.out.println("trv prd " + prdt.getImage());
            Image image = new Image(prdt.getImage());
            this.imageView.setImage(image);
            this.idAdmin = prdt.getIdAdmin();
            System.out.println(prdt.getImage());
        } else {
            // Afficher un message d'erreur si le produit avec cet ID est introuvable
            showErrorMessage("Produit introuvable.");
        }
    } catch (NumberFormatException e) {
        // Afficher un message d'erreur si l'ID du produit n'est pas un entier valide
        showErrorMessage("ID de produit invalide.");
    }
}

private void showErrorMessage(String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
        
    
    
    @FXML
    void addImage(ActionEvent event) {
        System.out.println("pi.GUI.ModifierProduitController.addImage()");
        FileChooser fileChooser = new FileChooser();
        
        File file = fileChooser.showOpenDialog(null);
        
        if (file != null) {
            
            String imagePath = file.toURI().toString();
            
            Image image = new Image(imagePath);
            
            this.imageView.setImage(image);

            
            
        }
    }

    

}