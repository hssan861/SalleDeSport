package application;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import models.Equipement;
import service.EquipementService;
import models.Categorie; // Assuming you have a Categorie class

public class EmployeeController implements Initializable {

    @FXML
    private TextField enom;
    @FXML
    private TextField eqt;
    @FXML
    private DatePicker edate;
    @FXML
    private TextField eprix;
    @FXML
    private Label lavel;
    @FXML
    private TextField id;
    @FXML
    private TableView<Equipement> table;
    @FXML
    private TableColumn<Equipement, Integer> eid;
    @FXML
    private TableColumn<Equipement, String> name;
    @FXML
    private TableColumn<Equipement, Integer> qte;
    @FXML
    private TableColumn<Equipement, Date> date;
    @FXML
    private TableColumn<Equipement, Integer> prix;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize your UI components if necessary
    }    

 @FXML
private void register(ActionEvent event) {
    String nom = enom.getText();
    int quantite = Integer.parseInt(eqt.getText());
    String dateAchat = edate.getValue().toString(); // Use getValue() to get the selected date
    float prixAchat = Float.parseFloat(eprix.getText());

    // Assuming you have a Categorie object associated with the Equipement
    Categorie categorie = new Categorie(); // Replace with your actual logic

    Equipement equipement = new Equipement(nom, quantite, dateAchat, prixAchat, categorie);

    // Call the service to add the equipement to the database
    EquipementService equipementService = new EquipementService();
    equipementService.ajouterEquipement(equipement);

    // Optionally, you can update the TableView to reflect the changes
    // table.getItems().add(equipement);

    // Clear the input fields
    enom.clear();
    eqt.clear();
    edate.getEditor().clear(); // Clear the DatePicker
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
    table.getItems().clear(); // Clear existing data in the table
    table.getItems().addAll(equipements);

    // Optionally, refresh the TableView
    table.refresh();
}


@FXML
private void update(ActionEvent event) {
    Equipement selectedEquipement = table.getSelectionModel().getSelectedItem();

    if (selectedEquipement != null) {
        String updatedNom = enom.getText();
        int updatedQuantite = Integer.parseInt(eqt.getText());
        String updatedDateAchat = edate.getValue().toString(); // Use getValue() for DatePicker
        float updatedPrixAchat = Float.parseFloat(eprix.getText());

        // Assuming you have a Categorie object associated with the Equipement
        Categorie updatedCategorie = new Categorie(); // Replace with your actual logic

        selectedEquipement.setNomEquipement(updatedNom);
        selectedEquipement.setQuantite(updatedQuantite);
        selectedEquipement.setDateAchat(updatedDateAchat);
        selectedEquipement.setPrixAchat(updatedPrixAchat);
        selectedEquipement.setCategorie(updatedCategorie);

        EquipementService equipementService = new EquipementService();
        equipementService.modifierEquipement(selectedEquipement);

        table.refresh();
    } else {
        lavel.setText("No equipement selected for update");
    }
}


}
