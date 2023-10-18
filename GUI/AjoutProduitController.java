/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.GUI;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import pi.entities.Produit;
import pi.services.*;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author hama
 */
public class AjoutProduitController implements Initializable {

    /**
     * Initializes the controller class.
     */  
    public AjoutProduitController(){
        this.sp = new serviceProduit();
    }

    private serviceProduit sp; 
@FXML
private Button btnajouter;

@FXML
private TextField categorieField;

@FXML
private TextField descriptionField;

@FXML
private ImageView imageView;

@FXML
private Button insert_image;

@FXML
private DatePicker tfdate;

@FXML
private TextField titreField;

@FXML
void add(ActionEvent event) {
    // Valider les champs de saisie avant d'ajouter un produit
    if (isValidInput()) {
        System.out.println("valid");
        // Récupérer les données du produit depuis les champs de saisie
        String description = descriptionField.getText();
        LocalDate date = tfdate.getValue();
        String title = titreField.getText();
        String category = categorieField.getText();
        
        // Récupérer l'image (ce code suppose que vous avez déjà chargé l'image dans imageView)
        Image image = imageView.getImage();
        
        String imagePath = (image != null) ? image.impl_getUrl() : "";
        
        Produit p = new Produit(1, title, imagePath, Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()), description, category);
        sp.add(p);
        // Ajouter le produit ici en utilisant votre service
        // ...
    } else {
        // Afficher un message d'erreur si la saisie est invalide
        System.out.println("not valid");
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir correctement tous les champs.");
        alert.showAndWait();
    }
}

boolean isValidInput() {
    // Ajouter vos règles de validation ici
    return !titreField.getText().isEmpty() && !categorieField.getText().isEmpty() && !descriptionField.getText().isEmpty() && tfdate.getValue() != null;
}


   @FXML
void addImage(ActionEvent event) {
  
    FileChooser fileChooser = new FileChooser();
    
    // Filtrez les fichiers pour n'accepter que les images
    FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif");
    fileChooser.getExtensionFilters().add(imageFilter);
    
    File file = fileChooser.showOpenDialog(null);
    
    if (file != null) {
        String imagePath = file.toURI().toString();
        
        // Vérifiez que le fichier est une image valide
        if (isValidImageFile(imagePath)) {
            Image image = new Image(imagePath);
            this.imageView.setImage(image);
        } else {
            // Affichez un message d'erreur si le fichier n'est pas une image valide
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de sélection d'image");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un fichier image valide (png, jpg, jpeg, gif).");
            alert.showAndWait();
        }
    }
}

boolean isValidImageFile(String imagePath) {
    // Ajoutez ici la logique pour valider si imagePath correspond à un fichier image valide
    // Par exemple, vous pouvez vérifier l'extension du fichier
    return imagePath != null && (imagePath.endsWith(".png") || imagePath.endsWith(".jpg") || imagePath.endsWith(".jpeg") || imagePath.endsWith(".gif"));
}



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
   
    
    }

}