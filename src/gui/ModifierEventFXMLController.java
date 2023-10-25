/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Event;
import services.EventServices;
import javafx.scene.image.Image;


/**
 * FXML Controller class
 *
 * @author rayen
 */
public class ModifierEventFXMLController implements Initializable {

    @FXML
    private TextField tfTitre;
    @FXML
    private TextField tfCoach;
    @FXML
    private TextField tfType;
    @FXML
    private TextField tfAdresse;
    @FXML
    private TextField tfPrix;
    private ImageView tfImage;
    @FXML
    private DatePicker tfDate;

    /**
     * Initializes the controller class.
     */EventServices es = new EventServices();
       private String url_image=null;
    private File file = null;
private Image image = null;
    @FXML
    private Button bb;
    @FXML
    private AnchorPane root2;
    @FXML
    private Button RetourHome;
    @FXML
    private ImageView tfImageMod;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
              tfTitre.setText(EvenementFXMain.event.getTitreEvent());
      tfCoach.setText(EvenementFXMain.event.getNomCoach());
       tfType.setText(EvenementFXMain.event.getTypeEvent());
       tfAdresse.setText(EvenementFXMain.event.getAdresseEvent());
       double prix = EvenementFXMain.event.getPrixEvent();
       LocalDate date = tfDate.getValue();
      tfDate.setValue(EvenementFXMain.event.getDateEvent());
       tfPrix.setText(Double.toString(prix));
       EvenementFXMain.event.setDateEvent(date);
    String imagePath = EvenementFXMain.event.getImgEvent(); 
   // System.out.println("Image Path: " + imagePath);
    Image image = new Image(new File(imagePath).toURI().toString());       
       tfImageMod.setImage(image);
       //System.out.println("Image: " + image);
           }    


    @FXML
    private void AfficherEv(ActionEvent event) throws IOException {
         bb.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("AfficherEventFXML.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }

    @FXML
    private void clearEvents(ActionEvent event) {
         tfTitre.setText("");
        tfCoach.setText("");
        tfType.setText("");
        tfAdresse.setText("");
        tfDate.setValue(null);
        tfPrix.setText("");
        tfImageMod.setImage(null);
    }

    @FXML
    
        private void ModifierEv(ActionEvent event) throws IOException {
              

           double prix = Double.parseDouble(tfPrix.getText());
 LocalDate date = tfDate.getValue();
 String imagePath = EvenementFXMain.event.getImgEvent();
    Image image = new Image(new File(imagePath).toURI().toString());
        try {
            Alert alert;

            if (tfTitre.getText().isEmpty()
                    || tfType.getText().isEmpty() || tfCoach.getText().isEmpty() || tfAdresse.getText().isEmpty()
                    || tfDate.getValue() == null || tfPrix.getText().isEmpty()
            ) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Les champs sont obligatoires");
                alert.showAndWait();
            } else if ( tfDate.getValue().isBefore(LocalDate.now())){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Date invalide");
                alert.showAndWait();
                } else if ( prix < 0.0){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("prix invalide");
                alert.showAndWait();
            }else {
                    if(file!=null){
                       imagePath=file.getAbsolutePath();
                       
                       
                   } else{
                       alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("image vide");
                alert.showAndWait();
                       return;
                   }

    
    Event ev = new Event (EvenementFXMain.event.getIdEvent(),tfTitre.getText(),tfCoach.getText(), tfType.getText(),tfAdresse.getText(),date, prix, imagePath);
    es.modifierEvent(ev);
    
    tfTitre.getScene().getWindow().hide();
    Parent root = FXMLLoader.load(getClass().getResource("AfficherEventFXML.fxml"));
    Stage mainStage = new Stage();
    Scene scene = new Scene(root);
    mainStage.setScene(scene);
    mainStage.show();
 
                } }catch (Exception e) {
   
    e.printStackTrace();
    
}
}  

    @FXML
    private void loadImg2(ActionEvent event) {
         FileChooser open = new FileChooser();
        file = open.showOpenDialog(root2.getScene().getWindow());

        if (file != null) {
            String imagePath = EvenementFXMain.event.getImgEvent();
Image image = new Image(new File(imagePath).toURI().toString());
            image = new Image(file.toURI().toString(), 200, 119, false, true) {};
            url_image= file.getName();
            tfImageMod.setImage(image);
        }
    }
   


   
}   
        
    
    

