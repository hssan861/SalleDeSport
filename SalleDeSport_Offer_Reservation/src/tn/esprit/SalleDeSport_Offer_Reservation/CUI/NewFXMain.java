/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package tn.esprit.SalleDeSport_Offer_Reservation.CUI;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author chayma2
 */
public class NewFXMain extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {

        /*Parent  root = FXMLLoader.load(getClass().getResource("/tn/esprit/SalleDeSport_Offer_Reservation/CUI/Affiche_Offer_FXML.fxml"));
        Scene scene = new Scene(root, 1100, 600); // Adjust the width and height as needed
        primaryStage.setTitle("Offer");
        primaryStage.setScene(scene);
        primaryStage.show();*/

        
        /*Parent  root = FXMLLoader.load(getClass().getResource("List_Offres_FXML.fxml"));
        Scene scene = new Scene(root, 1377, 783); 
        primaryStage.setTitle("User Reservation");
        primaryStage.setScene(scene);
        primaryStage.show();*/
        
        Parent  root = FXMLLoader.load(getClass().getResource("Afficher_Reservation_FXML.fxml"));
        Scene scene = new Scene(root, 1100, 600); 
        primaryStage.setTitle("Afficher Reservation");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        /*Parent  root = FXMLLoader.load(getClass().getResource("/tn/esprit/SalleDeSport_Offer_Reservation/CUI/Abonnement.fxml"));
        Scene scene = new Scene(root, 730, 720); 
        primaryStage.setTitle("Abonnement");
        primaryStage.setScene(scene);
        primaryStage.show();*/
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
