/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi_salle_de_sport.GUI;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import pi_salle_de_sport.Entities.Categorie;
import pi_salle_de_sport.Entities.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import pi_salle_de_sport.Entities.Activities;
import pi_salle_de_sport.Services.ServiceActivities;
import pi_salle_de_sport.Services.UserService;
/**
 * FXML Controller class
 *
 * @author HP
 */
public class ModifieractivitiesFXMLController implements Initializable {
    private int code;
    @FXML
    private ComboBox<Categorie> categoryComboBox;

    @FXML
    private ComboBox<User> coachComboBox;

    @FXML
    private TextField descriptionField;

    @FXML
    private DatePicker endDatePicker;

    
    @FXML
    private Button goAfficher;
    @FXML
    private TextField salleField;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private TextField titleField;
    
    ServiceActivities sa=new ServiceActivities();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
      
        // TODO
    }    


public void initData(int code, String titre, String description, LocalDate dateDebut, LocalDate dateFin, String salle, User coach, Categorie categorie) {
    // Utiliser les données passées pour initialiser votre interface utilisateur
    this.code = code; // Assigner l'ID de l'activité

    titleField.setText(titre);
    descriptionField.setText(description);
    startDatePicker.setValue(dateDebut);
    endDatePicker.setValue(dateFin);
    salleField.setText(salle);

    // Remplir la combobox du coach
    coachComboBox.getItems().clear();
    coachComboBox.getItems().add(coach);
    coachComboBox.getSelectionModel().selectFirst(); // Sélectionner le coach passé en paramètre

    // Remplir la combobox de la catégorie
    categoryComboBox.getItems().clear();
    categoryComboBox.getItems().add(categorie);
    categoryComboBox.getSelectionModel().selectFirst(); // Sélectionner la catégorie passée en paramètre

    // Remplir la combobox des catégories (enum)
    ObservableList<Categorie> categories = FXCollections.observableArrayList(Categorie.values());
    categoryComboBox.setItems(categories);

    // Remplir la combobox des coachs
    UserService coachService = new UserService();
    List<User> coachList = coachService.afficherUser();
    ObservableList<User> coaches = FXCollections.observableArrayList(coachList);
    coachComboBox.setItems(coaches);
}

     




@FXML
void modifierActivities(ActionEvent event) {
    // Utiliser this.code pour accéder à l'ID de l'activité
    System.out.println("ID de l'activité : " + this.code);

    // Récupérer les valeurs des champs depuis l'interface utilisateur
    String titre = titleField.getText();
    String description = descriptionField.getText();
    LocalDate dateDebut = startDatePicker.getValue();
    LocalDate dateFin = endDatePicker.getValue();
    String salle = salleField.getText();
    User coach = coachComboBox.getValue();
    Categorie categorie = categoryComboBox.getValue();

    // Vérifier si les champs obligatoires sont remplis
    if (titre.isEmpty() || description.isEmpty() || dateDebut == null || dateFin == null || salle.isEmpty() || coach == null || categorie == null) {
        // Afficher une alerte de validation
        afficherAlerte("Champs manquants", "Veuillez remplir tous les champs.");
        return;
    }

    // Créer un objet Activities avec les nouvelles données
    Activities activity = new Activities();
    activity.setCode(this.code);  // Assigner l'ID de l'activité

    activity.setTitre(titre);
    activity.setDescription(description);
    activity.setDateDeb(Date.valueOf(dateDebut));
    activity.setDateFin(Date.valueOf(dateFin));
    activity.setSalle(salle);
    activity.setIdCoach(coach);
    activity.setCategorie(categorie);

    // Appeler le service pour modifier l'activité
    boolean result = sa.modifier(activity);
    System.out.print(result);

    // Traiter la réponse du service
    if (result) {
        // L'activité a été modifiée avec succès
        afficherAlerte("Succès", "Activité modifiée avec succès.");
    } else {
        // Aucune activité trouvée avec le code spécifié, ou une erreur s'est produite lors de la modification
        afficherAlerte("Échec", "Aucune activité trouvée avec le code spécifié, ou une erreur s'est produite lors de la modification.");
    }
}

// Méthode pour afficher une alerte
private void afficherAlerte(String titre, String contenu) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle(titre);
    alert.setHeaderText(null);
    alert.setContentText(contenu);
    alert.showAndWait();
}

}

     
     
     
     

