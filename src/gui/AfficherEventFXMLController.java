/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import static java.util.Collections.list;
import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

     EventServices es = new EventServices();
    @FXML
    private Button Searchbutton;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       EventServices es = new EventServices();
        ObservableList<Event> items = FXCollections.observableArrayList();
               items.addAll(es.afficherEvent());
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
              Parent root = FXMLLoader.load(getClass().getResource("SupprimerEventFXML.fxml"));
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
                     eventRech.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("AjoutEventFXML.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show(); 
    
    }
   

    @FXML
    private void rechercher(ActionEvent event) {
        ObservableList<Event> allEvents = FXCollections.observableList(es.afficherEvent());
        FilteredList<Event> filter = new FilteredList<>( allEvents, e -> true);
        eventRech.textProperty().addListener((Observable, oldValue, newValue) -> {
            filter.setPredicate(predicateAssociation -> {
 if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String searchKey = newValue.toLowerCase();
                if (predicateAssociation.getTitreEvent().toLowerCase().contains(searchKey)) {
                    return true;
                }
                return false;
            });
        });
        
ListView<Event> LvAfficherEvent = new ListView<>(allEvents);
        allEvents.sort((event1, event2) -> event1.getTitreEvent().compareTo(event2.getTitreEvent()));
        
        }
    }
     

    
