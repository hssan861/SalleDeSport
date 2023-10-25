/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static gui.ParticipationFXMain.part;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Event;
import models.Participation;
import services.ParticipationServices;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class AfficherPartFXMLController implements Initializable {

    @FXML
    private ListView<Participation> LvPart;
    @FXML
    private TextField rechField;
    @FXML
    private Button Brech;

    /**
     * Initializes the controller class.
     */
            ParticipationServices ps = new ParticipationServices();
            private List<PartFXMLController> controllers;
                       ObservableList<Participation> part = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
           ObservableList<Participation> part = FXCollections.observableArrayList();

        loadUsers();
        rechField.textProperty().addListener((observable, oldValue, newValue) -> {
searchEvent(newValue.toLowerCase());
        });
        
    }    

    @FXML
    private void add(ActionEvent event) throws IOException {
          rechField.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("ReserverFXML.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show(); 
    }

    @FXML
    private void update(ActionEvent event) {
         try {
                  ParticipationFXMain.part = LvPart.getSelectionModel().getSelectedItem();
                  if (ParticipationFXMain.part != null) {
                 rechField.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("ModifierPartFXML.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show(); 
            }
                 
    
        } catch (Exception e) {
        }
    }

    @FXML
    private void delete(ActionEvent event) {
         try {
                 ParticipationFXMain.part = LvPart.getSelectionModel().getSelectedItem();
                  if (ParticipationFXMain.part != null) {
                 rechField.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("SuppPartFXML.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show(); 
                  }
      
        } catch (Exception e) {
        }
    }

    @FXML
    private void rechercher(ActionEvent event) {
    }
    private void searchEvent(String searchValue) {
        ObservableList<Participation> filteredUsers = FXCollections.observableArrayList();

        for (Participation user : part) {
            if (user.getNom().toLowerCase().contains(searchValue) ||
                user.getPrenom().toLowerCase().contains(searchValue) 
               )   
            
                filteredUsers.add(user);
            
        }

        LvPart.setItems(filteredUsers);
    }
    private void loadUsers() {
        part.setAll(ps.getAll());
        LvPart.setItems(part);
    }
    
}
