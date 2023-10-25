/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static gui.EvenementFXMain.event;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Event;
import models.Participation;
import services.EventServices;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class AffichageEventUserController implements Initializable {

    @FXML
    private ListView<Event> lvEvent;
    @FXML
    private TextField search;

    /**
     * Initializes the controller class.
     */
    EventServices es = new EventServices ();
            ObservableList<Event> evn = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
           ObservableList<Event> evn = FXCollections.observableArrayList();

        loadUsers();
        search.textProperty().addListener((observable, oldValue, newValue) -> {
searchEvent(newValue.toLowerCase());
        });
    }



    @FXML
    private void Reserver(ActionEvent event) throws IOException {
         search.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("ReserverFXML.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
        
    }
    private void searchEvent(String searchValue) {
        ObservableList<Event> filteredUsers = FXCollections.observableArrayList();

        for (Event user :evn ) {
            if (user.getTitreEvent().toLowerCase().contains(searchValue) ||
                user.getNomCoach().toLowerCase().contains(searchValue) 
               )   
            
                filteredUsers.add(user);
            
        }

        lvEvent.setItems(filteredUsers);
    }
    private void loadUsers() {
        evn.setAll(es.getAll());
        lvEvent.setItems(evn);
    }
    
}
