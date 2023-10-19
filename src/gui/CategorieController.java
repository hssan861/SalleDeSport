/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Categorie;
import service.CategorieService;

/**
 * FXML Controller class
 *
 * @author Z4RGA
 */
public class CategorieController implements Initializable {

    @FXML
    private TextField enom;
    private Label lavel;
    @FXML
    private TableView<Categorie> table;
    @FXML
    private TableColumn<Categorie, Integer> eid;
    @FXML
    private TableColumn<Categorie, String> name;
    @FXML
    private TableColumn<Categorie, String> description;
    @FXML
    private TextArea edescription;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

  @FXML
private void register(ActionEvent event) {
    // Get input values from the fields
    String nom = enom.getText();
    String description = edescription.getText(); // Assuming you have a field for description

    // Create a new Categorie object
    Categorie newCategorie = new Categorie(nom, description);

    // Call the service to add the new category
    CategorieService categorieService = new CategorieService();
    categorieService.ajouterCategorie(newCategorie);

    // Optionally, you can update the TableView to reflect the changes
    // table.getItems().add(newCategorie);

    // Clear the input fields
    enom.clear();
    edescription.clear();
}


   @FXML
private void deleteCategorie(ActionEvent event) {
    // Get the selected item from the TableView
    Categorie selectedCategorie = table.getSelectionModel().getSelectedItem();

    if (selectedCategorie != null) {
        // Call the service to delete the selected category
        CategorieService categorieService = new CategorieService();
        categorieService.supprimerCategorie(selectedCategorie);

        // Optionally, you can update the TableView to reflect the changes
        table.getItems().remove(selectedCategorie);
    } else {
        // Show a message indicating that no category is selected for deletion
        lavel.setText("No category selected for deletion");
    }
}


 @FXML
private void showAll(ActionEvent event) {
    // Call the service to retrieve all categories
    CategorieService categorieService = new CategorieService();
    List<Categorie> categories = categorieService.afficherCategorie();

    // Update the TableView with the retrieved data
    eid.setCellValueFactory(new PropertyValueFactory<>("idCategorie"));
    name.setCellValueFactory(new PropertyValueFactory<>("nomCategorie"));
    description.setCellValueFactory(new PropertyValueFactory<>("descriptionCategorie"));

    table.getItems().setAll(categories);
}


   @FXML
private void update(ActionEvent event) {
    // Get the selected item from the TableView
    Categorie selectedCategorie = table.getSelectionModel().getSelectedItem();

    if (selectedCategorie != null) {
        // Get updated values from the input fields or wherever you are getting them from
        String updatedNom = enom.getText();
        String updatedDescription = edescription.getText(); // Assuming you have a field for description

        // Update the selected category with new values
        selectedCategorie.setNomCategorie(updatedNom);
        selectedCategorie.setDescriptionCategorie(updatedDescription);

        // Call the service to update the category in the database
        CategorieService categorieService = new CategorieService();
        categorieService.modifierCategorie(selectedCategorie);

        // Optionally, you can update the TableView to reflect the changes
        table.refresh();
    } else {
        // Show a message indicating that no category is selected for update
        lavel.setText("No category selected for update");
    }
}

    
}
