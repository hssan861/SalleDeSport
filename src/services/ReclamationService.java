package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Reclamation;
import models.User;
import util.MyConnection;
import services.UserService;

public class ReclamationService {
    private MyConnection myConnection = MyConnection.getInstance();
    private Connection connection = myConnection.getCnx();

   public void ajouterReclamation(Reclamation reclamation) {
    // Check if the user exists before inserting the reclamation
    int userId = reclamation.getUser().getId(); // Assuming getUser() returns the user associated with the reclamation
    UserService us = new UserService();
    if (us.UserExists(userId)) {
        // Proceed with inserting the reclamation
        String req = "INSERT INTO reclamation (Description, IdUser) VALUES (?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, reclamation.getDescription());
            preparedStatement.setInt(2, userId);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Reclamation ajoutée avec succès!");
            } else {
                System.out.println("Failed to add reclamation.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    } else {
        System.out.println("User with the specified ID does not exist.");
    }
}
   public void modifierReclamation(Reclamation reclamation) {
       // Check if the user exists before inserting the reclamation
    int userId = reclamation.getUser().getId(); // Assuming getUser() returns the user associated with the reclamation
    UserService us = new UserService();
    if (us.UserExists(userId)) {
        // Proceed with inserting the reclamation
    String req = "UPDATE `reclamation` SET `Description`=? WHERE `Id`=?";
    try {
        PreparedStatement preparedStatement = connection.prepareStatement(req);
        preparedStatement.setString(1, reclamation.getDescription());
        preparedStatement.setInt(2, reclamation.getId());
        
        int rowsUpdated = preparedStatement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Reclamation updated successfully!");
        } else {
            System.out.println("No reclamation found with the given ID.");
        }
    } catch (SQLException ex) {
        Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
    }
     } else {
        System.out.println("User with the specified ID does not exist.");
    }
}



public void deleteReclamation(Reclamation reclamation) {
    String req = "DELETE FROM `reclamation` WHERE `Id`=?";
    try {
        PreparedStatement preparedStatement = connection.prepareStatement(req);
        preparedStatement.setInt(1, reclamation.getId());
        
        int rowsDeleted = preparedStatement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Reclamation deleted successfully!");
        } else {
            System.out.println("No reclamation found with the given ID.");
        }
    } catch (SQLException ex) {
        Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
    }
}


    // Add other methods as needed (modify, delete, retrieve, etc.)

    public List<Reclamation> afficherReclamation() {
        List<Reclamation> reclamations = new ArrayList<>();
        String req = "SELECT * FROM `reclamation`";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                Reclamation reclamation = new Reclamation();
                reclamation.setId(resultSet.getInt("Id"));
                reclamation.setDescription(resultSet.getString("Description"));
                
                // Fetch the associated User for the Reclamation
                int userId = resultSet.getInt("IdUser");
                UserService userService = new UserService();
                User user = userService.getUserById(userId);
                reclamation.setUser(user);

                reclamations.add(reclamation);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reclamations;
    }
    // Helper method to check if a user with the given ID exists
    
   public List<Reclamation> getAll() {
    List<Reclamation> list = new ArrayList<>();
    try {
        String query = "SELECT * FROM reclamation";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            int reclamationId = resultSet.getInt("Id");
            String description = resultSet.getString("Description");
            int userId = resultSet.getInt("IdUser");
            
            // Retrieve the associated user
            UserService userService = new UserService();
            User user = userService.getUserById(userId);
            
            Reclamation reclamation = new Reclamation(reclamationId, description, user);
            list.add(reclamation);
        }
    } catch (SQLException ex) {
        System.out.println("Error: " + ex.getMessage());
    }

    return list;
}


}