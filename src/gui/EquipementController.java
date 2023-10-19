/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Categorie;
import models.Equipement;
import service.CategorieService;
import service.EquipementService;

/**
 * FXML Controller class
 *
 * @author Z4RGA
 */
public class EquipementController implements Initializable {

    @FXML
    private TextField enom;
    @FXML
    private TextField eqt;
    @FXML
    private TextField eprix;
    @FXML
    private Label lavel;
    @FXML
    private TableView<Equipement> table;
    @FXML
    private TableColumn<Equipement, Integer> eid;
    @FXML
    private TableColumn<Equipement, String> name;
    @FXML
    private TableColumn<Equipement, String> qte;
    @FXML
    private TableColumn<Equipement, Date> date;
    @FXML
    private TableColumn<Equipement, Integer> prix;
    
    
    @FXML
    private DatePicker edate;

    @FXML

    private ComboBox<String> categoryComboBox;
    private CategorieService categorieService;
    private List<Categorie> categories;
    @FXML
    private TableColumn<?, ?> categorie;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        categorieService = new CategorieService();
    // Retrieve the list of categories from the service
        categories = categorieService.getCategoryListFromService();
         // Populate the ComboBox with category names
        for (Categorie categorie : categories) {
            categoryComboBox.getItems().add(categorie.getNomCategorie());
        }
        

    }

@FXML
private void register(ActionEvent event) {
    // Get input values from the fields
    String nom = enom.getText();
    int quantite = Integer.parseInt(eqt.getText());
    String dateAchat = edate.getValue().toString(); // Assuming edate is a DatePicker
    float prixAchat = Float.parseFloat(eprix.getText());

    // Get the selected category name
    String selectedCategoryName = categoryComboBox.getValue();
    
    // Find the corresponding Categorie object
    Categorie selectedCategory = null;
    for (Categorie categorie : categories) {
        if (categorie.getNomCategorie().equals(selectedCategoryName)) {
            selectedCategory = categorie;
            break;
        }
    }

    // Create an Equipement object with the input values and selected category
    Equipement equipement = new Equipement(nom, quantite, dateAchat, prixAchat, selectedCategory);

    // Call the service to add the equipement to the database
    EquipementService equipementService = new EquipementService();
    equipementService.ajouterEquipement(equipement);

    // Optionally, you can update the TableView to reflect the changes
    // table.getItems().add(equipement);

    // Clear the input fields
    enom.clear();
    eqt.clear();
    edate.getEditor().clear(); // Clear DatePicker
    eprix.clear();
}




@FXML
private void deleteEmployee(ActionEvent event) {
    // Get the selected item from the TableView
    Equipement selectedEquipement = table.getSelectionModel().getSelectedItem();

    if (selectedEquipement != null) {
        // Call the service to delete the selected equipement
        EquipementService equipementService = new EquipementService();
        equipementService.supprimerEquipement(selectedEquipement);

        // Optionally, you can update the TableView to reflect the changes
        table.getItems().remove(selectedEquipement);
    } else {
        // Show a message indicating that no equipement is selected
        lavel.setText("No equipement selected for deletion");
    }
}


    @FXML
    private void showAll(ActionEvent event) {
        // Call the service to retrieve all equipements
        EquipementService equipementService = new EquipementService();
        List<Equipement> equipements = equipementService.afficherEquipement();

        // Update the TableView with the retrieved data
        eid.setCellValueFactory(new PropertyValueFactory<>("idEquipement"));
        name.setCellValueFactory(new PropertyValueFactory<>("nomEquipement"));
        qte.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        date.setCellValueFactory(new PropertyValueFactory<>("dateAchat"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prixAchat"));
        categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));


        table.getItems().setAll(equipements);
    }

   @FXML
private void update(ActionEvent event) {
    // Get the selected item from the TableView
    Equipement selectedEquipement = table.getSelectionModel().getSelectedItem();

    if (selectedEquipement != null) {
        // Get updated values from the input fields or wherever you are getting them from
        String updatedNom = enom.getText();
        int updatedQuantite = Integer.parseInt(eqt.getText());
        String updatedDateAchat = edate.getValue().toString(); // Assuming edate is a DatePicker
        float updatedPrixAchat = Float.parseFloat(eprix.getText());

        // Assuming you have a Categorie object associated with the Equipement
        Categorie updatedCategorie = new Categorie(); // Replace with your actual logic

        // Update the selected equipement with new values
        selectedEquipement.setNomEquipement(updatedNom);
        selectedEquipement.setQuantite(updatedQuantite);
        selectedEquipement.setDateAchat(updatedDateAchat);
        selectedEquipement.setPrixAchat(updatedPrixAchat);
        selectedEquipement.setCategorie(updatedCategorie);

        // Call the service to update the equipement in the database
        EquipementService equipementService = new EquipementService();
        equipementService.modifierEquipement(selectedEquipement);

        // Optionally, you can update the TableView to reflect the changes
        table.refresh();
    } else {
        // Show a message indicating that no equipement is selected for update
        lavel.setText("No equipement selected for update");
    }
}

    @FXML
    private void handleButtonClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Categorie.fxml"));
            Parent root = loader.load();

            // Get the controller for the Categorie interface
            CategorieController categorieController = loader.getController();

            Stage stage = new Stage();
            stage.setTitle("Categorie Interface");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
