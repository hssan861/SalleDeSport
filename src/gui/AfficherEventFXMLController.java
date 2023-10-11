/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Event;
import services.EventServices;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class AfficherEventFXMLController implements Initializable {

    @FXML
    private ListView<Event> LvAfficherEvent;
    @FXML
    private TextField eventRech;

    /**
     * Initializes the controller class.
     */
    EventServices es = new EventServices();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       EventServices es = new EventServices();
        ObservableList<Event> items = FXCollections.observableArrayList();
       

        // Remplissez la liste observable "items" avec les données de votre service EventServices
        items.addAll(es.afficherEvent());

        // Utilisez la ListView définie dans FXML pour afficher les données
        LvAfficherEvent.setItems(items);
        
        
    }    

    @FXML
    private void ModiferEvent(ActionEvent event) {
         try {
                  EvenementFXMain.event = LvAfficherEvent.getSelectionModel().getSelectedItem();
                  if (EvenementFXMain.event != null) {
                 eventRech.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("ModifierEventFXML.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show(); 
            }
                 
    
        } catch (Exception e) {
        }
    }

    @FXML
    private void SupprimerEvent(ActionEvent event) {
         try {
                 EvenementFXMain.event = LvAfficherEvent.getSelectionModel().getSelectedItem();
                  if (EvenementFXMain.event != null) {
                 eventRech.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("ModifierEventFXML.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show(); 
                  }
      
        } catch (Exception e) {
        }
    }

    @FXML
    private void AjoutEvent(ActionEvent event) throws IOException {
         /*MainKais.role = "admin";
                     searchavis.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("AjoutEventFXML.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show(); 
    
*/
    }
    @FXML
    private void rechByidEvent(ActionEvent event) {
    }
}
    
