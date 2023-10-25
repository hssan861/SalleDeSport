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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Commande;
import models.Produit;
import services.serviceCommande;
import services.serviceProduit;

/**
 * FXML Controller class
 *
 * @author hama
 */
public class AjoutCommandeController implements Initializable {
private serviceCommande sp;
    /**
     * Initializes the controller class.
     */
     public AjoutCommandeController(){
        this.sc = new serviceCommande();
        
    }
    List<Integer> ps=new ArrayList<>() ;
     int id;
     public void setproduits(List<Integer> pl){
         for(Integer p: pl){
             ps.add(p);
         }
     }
    private serviceCommande sc; 
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.sp = new serviceCommande();
    }    


    @FXML
    private TextField adresseField;

    @FXML
    private DatePicker tfdate;

  

    @FXML
    private TextField typeField;

    @FXML
    void afficherCommandes(ActionEvent event) {

    }

    @FXML
    void ajouterCommande(ActionEvent event) {
        if (isValidInput()) {
            String type = typeField.getText();
            LocalDate LocalDate = tfdate.getValue();
            Date date = Date.from(LocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            String adreese = adresseField.getText();
            Commande c= new Commande(date,adreese,type);
            id=sp.add(c);
            for(Integer p: ps){
                sp.insert(id,p);
            }

            showInfoMessage("Commande ajouté avec succès");
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir correctement tous les champs.");
            alert.showAndWait();
        }
}

    private void showInfoMessage(String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Information");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
    boolean isValidInput() {
        // Vérifiez d'abord que tfdate.getValue() n'est pas nul
        if (tfdate.getValue() == null) {
            // La date est nulle, renvoyez false
            return false;
        }

        // Ensuite, ajoutez vos autres règles de validation
        return !typeField.getText().isEmpty() && !adresseField.getText().isEmpty();
    }


    public void retour(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("ChoosePrd.fxml"));
        Parent root=loader.load();
        typeField.getScene().setRoot(root);
    }
}

