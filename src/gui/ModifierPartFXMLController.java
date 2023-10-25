/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static gui.EvenementFXMain.event;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
public class ModifierPartFXMLController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfNTEL;
    @FXML
    private TextField tfPrenom;
    @FXML
    private ComboBox<Event> tfEvent;
    @FXML
    private Button afMod;
    @FXML
    private DatePicker tfDatePart;

    /**
     * Initializes the controller class.
     */
                ParticipationServices ps = new ParticipationServices();

    @Override
    public void initialize(URL url, ResourceBundle rb) {


  tfNom.setText(ParticipationFXMain.part.getNom());
    tfPrenom.setText(ParticipationFXMain.part.getPrenom());
    tfNTEL.setText(ParticipationFXMain.part.getNtel());
    tfDatePart.setValue(ParticipationFXMain.part.getDatePart());

    EventServices es = new EventServices();
    List<Event> eventList = es.afficherEvent();
    ObservableList<Event> items = FXCollections.observableArrayList(eventList);

    // Clear the ComboBox and set its items to the 'items' list
    tfEvent.getItems().clear();
    tfEvent.setItems(items);

    // Optionally, you can set a default selection
    tfEvent.getSelectionModel().selectFirst();


    }    

    

    @FXML
    private void afficherPartMod(ActionEvent event) throws IOException {
   
    tfNom.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("afficherPartFXML.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show(); }

    
    
    @FXML
    private void ConfirmerPart(ActionEvent event) {
    String Nom = tfNom.getText();
    String Prenom = tfPrenom.getText();
    LocalDate date = tfDatePart.getValue();
    String ntel = tfNTEL.getText();
    Event ev = tfEvent.getValue();
    
    Participation part = new Participation();
    
     part.setNom(Nom);
    part.setPrenom(Prenom);
    
    part.setDatePart(date);
    part.setEvent(ev);
   part.setNtel(ntel);
   ps.modifierParticipation(part);

    
    }
    
}
