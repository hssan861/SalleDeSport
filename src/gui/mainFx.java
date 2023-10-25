/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import models.Commande;

/**s
 *
 * @author hama
 */
public class mainFx extends Application {
    
static Commande commande = new Commande();
    private Stage stage;
    private Parent parent;
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.stage=primaryStage;
        parent=FXMLLoader.load(getClass().getResource("ChoosePrd.fxml"));
        Scene scene=new Scene(parent);
        primaryStage.setTitle("Gestion E-commerce");
        primaryStage.getIcons().add(new Image("gui/385564839_742907983963047_7604035126350803380_n.png"));
        stage.setScene(scene);
        stage.show();      
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}