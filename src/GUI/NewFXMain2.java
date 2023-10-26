/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.NewFXMain2.comment;
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
import models.Comment;
import models.Post;

/**
 *
 * @author lenovo
 */
public class NewFXMain2 extends Application {
    static Comment comment = new Comment();
    
    @Override
    public void start(Stage primaryStage) {
       try {
            Parent root = FXMLLoader.load(getClass().getResource("AffichageComment.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("/*******Comment/******");
            primaryStage.show();

            
           
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        
       try {
            Parent root = FXMLLoader.load(getClass().getResource("AfficherComment.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("affichage");
            primaryStage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } 
       
       
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
