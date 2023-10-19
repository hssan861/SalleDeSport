/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gym;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static models.Role.Admin;
import static models.Role.Coach;
import static models.Role.Utilisateur;
import models.User;
import services.UserService;

/**
 *
 * @author USER
 */
public class Gym extends Application{
 @Override 
    public void start(Stage primaryStage) {
           
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("User Management");
            primaryStage.show();
            
        
            // Create a sample User object with valid data
  
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * @param args the command line arguments
    */
    public static void main(String[] args) {
          launch(args);
        //UserService su =new UserService();
        //su.getuserbyemailandpass("hssan.ghorbel@esprit.tn","123456789");
          //su.getRoleByEmail("aziz.souissi@esprit.tn");
        // TODO code application logic here
     
       /*User u= new User("haythem","mekki","hahaha",Coach,"haythem.mekki@esprit.tn","haythem.jpg",24);
        UserService us=new UserService();
       // us.ajouterUser(u);
        User userToUpdate = new User();
        userToUpdate.setId(34);
        userToUpdate.setNom("Hssouna");
        userToUpdate.setPrenom("Ghorbel");
        userToUpdate.setMdp("lamia");
        userToUpdate.setRole(Coach);
        userToUpdate.setEmail("hssan.ghorbel@esprit.tn");
        userToUpdate.setImg("hhh.jpg");
        userToUpdate.setAge(22);
        //us.modfierUser(userToUpdate);
        User userToDelete=new User();
        userToDelete.setId(34);
        us.deleteUser(userToDelete);
        System.out.println(us.afficherUser());*/
        
        
        
        
        
    }
    
}
