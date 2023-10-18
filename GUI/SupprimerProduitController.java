/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.GUI;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import pi.entities.Produit;
import pi.services.serviceProduit;
import pi.tools.DataS;

/**
 * FXML Controller class
 *
 * @author hama
 */
public class SupprimerProduitController implements Initializable {
    private Connection cnx;
    /**
     * Initializes the controller class.
     */
    
      public  SupprimerProduitController(){
        this.sp = new serviceProduit();
    }

    private serviceProduit sp; 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
       @FXML
    private TextField productIdField;

    @FXML
    private Button supp;

    @FXML


// ...

public void supprimer(ActionEvent event) {
    // Récupérez l'identifiant du produit à partir de l'interface utilisateur (par exemple, d'un champ de texte).
    int productId = Integer.parseInt( productIdField.getText());

    Produit toDEl=new Produit();
    toDEl.setId(productId);
    
        boolean isDeleted = this.sp.supprimer(toDEl);

        if (isDeleted) {
            // La suppression a réussi
            System.out.println("Produit supprimé avec succès !");
        } else {
            // La suppression a échoué
            System.out.println("La suppression du produit a échoué.");
    
}

}
}

