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
    @FXML
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
              tfTitre.setText(EvenementFXMain.event.getTitreEvent());
      tfCoach.setText(EvenementFXMain.event.getNomCoach());
       tfType.setText(EvenementFXMain.event.getTypeEvent());
       tfAdresse.setText(EvenementFXMain.event.getAdresseEvent());
       double prix = EvenementFXMain.event.getPrixEvent();
        

       tfDate.setValue(EvenementFXMain.event.getDateEvent());

    LocalDate date = tfDate.getValue();
       tfPrix.setText(Double.toString(prix));
       EvenementFXMain.event.setDateEvent(date);
        //tfImage.setImage(EvenementFXMain.event.getImgEvent());
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
        tfImage.setImage(null);
    }

    @FXML
    private void ModifierEv(ActionEvent event) throws IOException {
           double prix = Double.parseDouble(tfPrix.getText());
 LocalDate date = tfDate.getValue();
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
 
String imagePath = "C:\\Users\\rayen\\OneDrive\\Bureau\\Fun_run_people_logo-removebg-preview.png";
    Image image = new Image(new File(imagePath).toURI().toString());
    es.modifierEvent(new Event(
        EvenementFXMain.ee.getIdEvent(),
        tfTitre.getText(),
        tfCoach.getText(),
        tfType.getText(),
        tfAdresse.getText(),
        date,
        prix,
        imagePath
    ));
    
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
            String imagePath = "C:\\Users\\rayen\\OneDrive\\Bureau\\Fun_run_people_logo-removebg-preview.png";
Image image = new Image(new File(imagePath).toURI().toString());
            image = new Image(file.toURI().toString(), 200, 119, false, true) {};
            url_image= file.getName();
            tfImage.setImage(image);
        }
    }
}   
        
    
    

