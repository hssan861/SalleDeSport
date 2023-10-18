/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import pi.entities.Commande;
import pi.services.serviceCommande;
import pi.tools.DataS;
import sun.applet.Main;

/**
 * FXML Controller class
 *
 * @author hama
 */
public class SupprimerCController implements Initializable {
    

    /**
     * Initializes the controller class.
     */
        serviceCommande sc = new  serviceCommande();
      public SupprimerCController(){
        this.sc = new serviceCommande();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private Button confirmerButton;

    @FXML
    
void AnnulerS(ActionEvent event) throws IOException {
    boolean isCanceled = false; // Indicateur d'annulation
    // Effectuez des opérations d'annulation ici, si nécessaire

    if (isCanceled) {
        // L'opération d'annulation a réussi
        confirmerButton.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("AfficheC.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    } else {
        // L'opération d'annulation a échoué, affichez un message d'erreur
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur d'annulation");
        alert.setHeaderText(null);
        alert.setContentText("L'annulation a échoué. Veuillez réessayer.");
        alert.showAndWait();
    }
}

@FXML
void CONFS(ActionEvent event) throws IOException {
    boolean isDeleted = sc.supprimer(mainFx.commande); // Assurez-vous que votre service retourne un booléen

    if (isDeleted) {
        // La suppression a réussi
        confirmerButton.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("AfficheC.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    } else {
        // La suppression a échoué, affichez un message d'erreur
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de suppression");
        alert.setHeaderText(null);
        alert.setContentText("La suppression a échoué. Veuillez réessayer.");
        alert.showAndWait();
    }
}

}
