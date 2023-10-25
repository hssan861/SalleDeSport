/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gym;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import models.Activities;

/**
 *
 * @author HP
 */
public class pi_salle_de_sport_fxml extends Application {
        static Activities activite =new Activities();

    @Override
    public void start(Stage primaryStage) {
        try {
             //Load the icon image from the resources package
            Image appIcon = new Image(getClass().getResourceAsStream("../Images/logo.png"));

            // Set the application's icon
            //primaryStage.getIcons().add(appIcon);
            Parent root=FXMLLoader.load(getClass().getResource("/gui/listeactitvitiesFXML.fxml"));
            //pi_salle_de_sport.GUI
            Scene scene = new Scene(root);
            primaryStage.setTitle("FitFlex");
            primaryStage.setScene(scene);
            primaryStage.show();
            
        } catch (IOException ex) {
            System.out.println("Err"+ex.getMessage());
        }
       
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
