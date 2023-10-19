/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Event;
import models.Participation;
import services.EventServices;
import services.ParticipationServices;

/**
 * FXML Controller class
 *
 * @author rayen
 */

public class ReserverFXMLController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfNTEL;
    @FXML
    private TextField tfPrenom;
    @FXML
    private ComboBox<Event> tfEvent;
    @FXML
    private DatePicker tfDatePart;
    @FXML
    private Button af;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        EventServices es = new EventServices();
    List<Event> eventList = es.afficherEvent();
    ObservableList<Event> items = FXCollections.observableArrayList(eventList);
    tfEvent.setItems(items);
    }    

    @FXML
    private void retourHome(ActionEvent event) {
    }

    @FXML
    private void afficherPart(ActionEvent event) throws IOException {
         af.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("afficherPartFXML.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();

    }

    @FXML
    private void EnregisterPart(ActionEvent event) {
         ParticipationServices sp = new ParticipationServices();

        
        Event ev = (Event) tfEvent.getValue();

        LocalDate date = tfDatePart.getValue();
        String nom = tfNom.getText();
        String prenom = tfPrenom.getText();
        String ntel = tfNTEL.getText();
 try {
            Alert alert;

            if (tfNom.getText().isEmpty()
                    || tfPrenom.getText().isEmpty() || tfNTEL.getText().isEmpty() 
                    || tfDatePart.getValue() == null 
            ) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Les champs sont obligatoires");
                alert.showAndWait();
            } else if ( tfDatePart.getValue().isBefore(LocalDate.now())){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Date invalide");
                alert.showAndWait();
                
            }else {
        
        Participation part = new Participation(ev,date,nom,prenom,ntel);
        sp.ajouterParticipation(part);
    } } catch (Exception e) {
            e.printStackTrace();
        }
    
}}
    

