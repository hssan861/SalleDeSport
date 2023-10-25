/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import API.SendSMS;
import static gui.EvenementFXMain.event;
import static gui.ParticipationFXMain.part;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javafx.scene.control.ButtonType;
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
    int nbr=0;
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
    String regex = "^(\\+[0-9]+)?[0-9-]*$";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(ntel);

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
        } else if (tfDatePart.getValue().isBefore(LocalDate.now())) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Date invalide");
            alert.showAndWait();
        } else if (!matcher.matches()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer un numéro de téléphone valide.");
            alert.showAndWait();
        } else {
            if (ev.getNombrePlacesDisponibles() > 0) {
                Participation part = new Participation(ev, date, nom, prenom, ntel);
                participer();
                sp.ajouterParticipation(part);
                ev.incrementNombrePlacesReservees(); // Incrémente le nombre de participants
                // Mettez à jour l'événement dans la base de données
                EventServices es = new EventServices();
                es.modifierEvent(ev);
                 try {
        SendSMS sm = new SendSMS();
        sm.sendSMS(part);
        System.out.println("SMS envoyé avec succès");
    } catch (Exception e) {
        e.printStackTrace();
    }
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de participation");
                alert.setHeaderText(null);
                alert.setContentText("Aucune place disponible pour cet événement.");
                alert.showAndWait();
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

   
}

   /* @FXML
    private void EnregisterPart(ActionEvent event) {
         ParticipationServices sp = new ParticipationServices();

        
        Event ev = (Event) tfEvent.getValue();

        LocalDate date = tfDatePart.getValue();
        String nom = tfNom.getText();
        String prenom = tfPrenom.getText();
        String ntel = tfNTEL.getText();
        String regex = "^(\\+[0-9]+)?[0-9-]*$";

Pattern pattern = Pattern.compile(regex);
Matcher matcher = pattern.matcher(ntel);
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
                
            } else if (!matcher.matches()) {
  
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez entrer un numéro de téléphone valide.");
                alert.showAndWait();
            } else {
                
        Participation part = new Participation(ev,date,nom,prenom,ntel);
        nbr=nbr+1;
        participer();
        sp.ajouterParticipation(part);
        
       
        } 
    } catch (Exception e) {
               e.printStackTrace();
           }
    try {
            SendSMS sm = new SendSMS();
            sm.sendSMS(part);
            System.out.println("SMS envoyé avec succès");
        } catch (Exception e) {
            // handle the exception here
            e.printStackTrace();
        }
    
}*/
 public void participer(){
        try {
            Alert alert;

            if (nbr>5
            ) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("L'évènement "+event.getTitreEvent()+" n a plus des places disponibles");
                alert.showAndWait();
//            } else if ( evenement_userServiceImp.getUsersByEvent(event1).stream().map(user->user.getId()).collect(Collectors.toList()).contains(serviceuser.getCurrentUser().getId())){
//                alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Error Message");
//                alert.setHeaderText(null);
//                alert.setContentText("Vous etes deja participer à l'évènement "+event1.getTitre());
//                alert.showAndWait();

            }else {


                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Cofirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Êtes-vous sûr de vouloir participer à l'événement : " + event.getTitreEvent() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {

                   nbr=nbr-1;
                    
//                    evenement_userServiceImp.addUserEvent(user,event1);
//
//                    System.out.println(evenement_userServiceImp.getUsersByEvent(event1));


                    //ParticipationServices.participerEvent(event.getIdEvent(),nbr,event.getPrixEvent());
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Participer avec Succes!");
                    alert.showAndWait();





                }




            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }}
    

