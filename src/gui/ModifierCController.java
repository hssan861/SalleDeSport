/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import models.Commande;
import services.serviceCommande;

/**
 * FXML Controller class
 *
 * @author hama
 */
public class ModifierCController implements Initializable {
    private Commande c;
    public void setcommodif(Commande c0){
        c=c0;
        adresseField.setText(c.getAdresse());
        typeField.setText(c.getType());
    }

    /**
     * Initializes the controller class.
     */
    
         public ModifierCController(){
        this.sc = new serviceCommande();
    }
    private serviceCommande sc; 
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    
    @FXML
    private TextField adresseField;

    @FXML
    private DatePicker tfdate;

    @FXML
    private TextField typeField;

    @FXML
    void modifierCommande(ActionEvent event) {
// Créez un objet Commande pour stocker les données de mise à jour
    Commande commande = new Commande();
    if((!typeField.getText().isEmpty())&&(!adresseField.getText().isEmpty())&&(tfdate.getValue() != null)){
        // Récupérez les données de l'interface utilisateur
        String type=typeField.getText();
        String adresse = adresseField.getText();
        LocalDate LocalDate = tfdate.getValue();
        Date date = Date.from(LocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        commande.setId(c.getId());
        commande.setDate(date);
        commande.setAdresse(adresse);
        commande.setType(type);
        sc.modifier(commande);
        showErrorMessage("Commande mis à jour avec succès !");
    }else{
        showErrorMessage("Veuillez remplir correctement tous les champs.");
    }


}

private void showErrorMessage(String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Erreur de saisie");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
    
  


    @FXML
    public void retour(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("AfficheC.fxml"));
        Parent root=loader.load();
        adresseField.getScene().setRoot(root);
    }
}
