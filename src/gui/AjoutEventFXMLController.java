/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

//import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Event;
import javafx.scene.image.Image;

import services.EventServices;

/**
 * FXML Controller class
 *
 * @author rayen
 */

public class AjoutEventFXMLController implements Initializable {

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
    @FXML
    private Button aff;
    @FXML
    private AnchorPane root1;
    private String url_image=null;
    private File file = null;
private Image image = null;
    @FXML
    private ImageView logo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterEv(ActionEvent event) {
          double prix = Double.parseDouble(tfPrix.getText());
    EventServices sp = new EventServices();
    LocalDate date =tfDate.getValue();
    String imagePath = "C:\\Users\\rayen\\OneDrive\\Bureau\\Fun_run_people_logo-removebg-preview.png";
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
        sp.ajouterEvent(new Event(tfTitre.getText(), tfCoach.getText(),tfType.getText(),tfAdresse.getText(),date ,prix,imagePath));
            alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Added!");
                alert.showAndWait();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void AffichageEv(ActionEvent event) throws IOException {
        aff.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("AfficherEventFXML.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }

    @FXML
    private void clearEvent(ActionEvent event) {
        tfTitre.setText("");
        tfCoach.setText("");
        tfType.setText("");
        tfAdresse.setText("");
        tfDate.setValue(null);
        tfPrix.setText("");
        tfImage.setImage(null);
    }

    @FXML
    private void loadImg(ActionEvent event) {
     FileChooser open = new FileChooser();
        file = open.showOpenDialog(root1.getScene().getWindow());

        if (file != null) {
            String imagePath = "C:\\Users\\rayen\\OneDrive\\Bureau\\Fun_run_people_logo-removebg-preview.png";
Image image = new Image(new File(imagePath).toURI().toString());
            image = new Image(file.toURI().toString(), 200, 119, false, true) {};
            url_image= file.getName();
            tfImage.setImage(image);
        }
}
 }
