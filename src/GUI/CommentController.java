/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

//import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
//import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
//import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
//import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import models.Comment;
import models.Post;
import services.CommentService;
import services.PostService;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import GUI.badwords;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class CommentController implements Initializable {

    @FXML
    private TextArea tfcommenter;
    
    
    @FXML
    private Button kk;
    @FXML
    private Button nnn;
    @FXML
    private Label errorMessageLabel;
    @FXML
    private ComboBox<Post> postcomm;
    @FXML
    private Button pp;
    private Comment commentData;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  /*PostService es = new PostService();
    List<Post> postList = es.afficherPost();
    ObservableList<Post> items = FXCollections.observableArrayList(postList);
    postcomm.setItems(items);*/
    
    PostService es = new PostService();
    List<Post> postList = es.afficherPost();
    ObservableList<Post> items = FXCollections.observableArrayList(postList);

    // Clear the ComboBox and set its items to the 'items' list
    postcomm.getItems().clear();
   postcomm.setItems(items);

    // Optionally, you can set a default selection
    postcomm.getSelectionModel().selectFirst();
    
   
    }
    

    @FXML
    private void AjouterComm(ActionEvent event) throws IOException {
        String comment = tfcommenter.getText();
    if (comment.isEmpty()) {
        errorMessageLabel.setText("Le champ de commentaire est vide. Veuillez entrer un commentaire.");
        return;
    }
             CommentService sp = new CommentService();

        // Get the selected post from the ComboBox
        Post ev = (Post) postcomm.getValue();
        TextInputDialog dialog = new TextInputDialog();
        // Create a comment object with the selected post
       // Optional<String> result = dialog.showAndWait();
        String result = tfcommenter.getText();
    if (!result.isEmpty()) {
        //String content = result.get();
        badwords filter = new badwords();

        if ( filter.containsBadWord(tfcommenter.getText())) {
            // Display an error message or take other actions as needed
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("The input text contains a bad word.");
            alert.showAndWait();
            System.out.println("The input text contains a bad word.");
        } else {
            // Proceed with normal processing
            System.out.println("The input text is clean.");
             Comment part = new Comment(ev,comment);
        sp.ajouterComment(part);
        }
    }
    
     pp.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("AfficherComment.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    
       
    
      
        
    }

    @FXML
    private void annulercomm(ActionEvent event) throws IOException {
        nnn.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("AfficherPost.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    
    }

    @FXML
    private void AfficherComm(ActionEvent event) throws IOException {
         kk.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("AfficherComment.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
        
        
    }
    
}
