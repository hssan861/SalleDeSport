/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import static java.util.Collections.list;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
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
    @Override
  public void initialize(URL url, ResourceBundle rb) {
      LvAfficherEvent.setCellFactory(param -> new ListCell<Event>() {
    private final ImageView imageView = new ImageView();
    
    @Override
    protected void updateItem(Event event, boolean empty) {
        super.updateItem(event, empty);

        if (empty || event == null) {
            setGraphic(null);
        } else {
           
            try {
                File file = new File(event.getImgEvent());
                if (file.exists()) {
                    Image image = new Image(file.toURI().toString());
                    imageView.setImage(image);
                    imageView.setFitWidth(100); // Adjust the width as needed
                    imageView.setPreserveRatio(true);
                    setGraphic(imageView);
                                     String EventInfo = "Titre: " + event.getTitreEvent() + "\n" + "Prix: " + event.getPrixEvent() + "\n" +
              
                            "Date: " + event.getDateEvent();
                    
                           VBox vbox = new VBox( imageView, new Label(EventInfo));
                    vbox.setSpacing(5);

                    // Affichez le VBox dans la cellule
                    setGraphic(vbox);
                } else {
                    setGraphic(null); // Image file not found
                }
            } catch (Exception e) {
                e.printStackTrace();
                setGraphic(null); // Handle any exceptions gracefully
            }
        }
    }
});
              EventServices sp = new EventServices();

       EventServices es = new EventServices();
        ObservableList<Event> items = FXCollections.observableArrayList();
               items.addAll(es.afficherEvent());
        LvAfficherEvent.setItems(items);
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
      
}

   
    
     

    
