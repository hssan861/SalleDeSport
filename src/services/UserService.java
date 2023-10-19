package services;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import models.Role;
import models.User;
import util.MyConnection;
import java.io.*;
import java.util.Properties;

public class UserService {
    private MyConnection myConnection = MyConnection.getInstance();
    private Connection connection = myConnection.getCnx();

    public boolean UserExists(int userId) {
        String query = "SELECT COUNT(*) FROM user WHERE Id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void ajouterUser(User user) {
        String query = "INSERT INTO user(`Nom`, `Prenom`, `Mdp`, `Role`, `Email`, `Img`, `Age`) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getNom());
            preparedStatement.setString(2, user.getPrenom());
            preparedStatement.setString(3, user.getMdp());
            preparedStatement.setString(4, user.getRole().toString());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getImg());
            preparedStatement.setInt(7, user.getAge());
            preparedStatement.executeUpdate();
            System.out.println("User added successfully!");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<User> afficherUser() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM user";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("Id"));
                user.setNom(resultSet.getString("Nom"));
                user.setPrenom(resultSet.getString("Prenom"));
                user.setMdp(resultSet.getString("Mdp"));
                user.setRole(Role.valueOf(resultSet.getString("Role")));
                user.setEmail(resultSet.getString("Email"));
                user.setImg(resultSet.getString("Img"));
                user.setAge(resultSet.getInt("Age"));
                users.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

    public void modifierUser(User user) {
        String query = "UPDATE `user` SET `Nom`=?, `Prenom`=?, `Mdp`=?,`Role`=?, `Email`=?, `Img`=?, `Age`=? WHERE `Id`=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getNom());
            preparedStatement.setString(2, user.getPrenom());
            preparedStatement.setString(3, user.getMdp());
            preparedStatement.setString(4, user.getRole().toString());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getImg());
            preparedStatement.setInt(7, user.getAge());
            preparedStatement.setInt(8, user.getId());

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("User updated successfully!");
            } else {
                System.out.println("No user found with the given ID.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteUser(User user) {
        String query = "DELETE FROM user WHERE Id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, user.getId());

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("User deleted successfully!");
            } else {
                System.out.println("No user found with the given ID.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public User getUserById(int userId) {
        User user = null;
        String query = "SELECT * FROM user WHERE Id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("Id"));
                user.setNom(resultSet.getString("Nom"));
                user.setPrenom(resultSet.getString("Prenom"));
                user.setMdp(resultSet.getString("Mdp"));
                user.setRole(Role.valueOf(resultSet.getString("Role")));
                user.setEmail(resultSet.getString("Email"));
                user.setImg(resultSet.getString("Img"));
                user.setAge(resultSet.getInt("Age"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public int getIdByEmail(String email) {
        int userId = -1; // Default value indicating no user found with the provided email

        String query = "SELECT Id FROM user WHERE Email = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                userId = resultSet.getInt("Id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userId;
    }
     public List<User> getAll() {
    List<User> list = new ArrayList<>();
    try {
      

        String req = "SELECT * FROM `user`";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(req);

        while (rs.next()) {
            User u = new User(
                rs.getInt("Id"),
                rs.getString("Nom"),
                rs.getString("Prenom"),
                rs.getString("Mdp"),
                Role.valueOf(rs.getString("role")), // Assuming role is stored as a String
                rs.getString("Email"),
                rs.getString("img"),
                rs.getInt("Age")
            );
            list.add(u);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return list;
}
     
      /***********************************Create login files****************************************/
         public void createiniFile(String path, String user, String pass) {
        try {
            Properties properties = new Properties();
            properties.setProperty("Email", user);
            properties.setProperty("Password", pass);

            try (OutputStream outputStream = new FileOutputStream(path)) {
                properties.store(outputStream, "Login data");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void readinifile(String path, TextField userId, PasswordField passId) {
        try {
            File file = new File(path);
            if (file.exists()) {
                Properties properties = new Properties();

                try (InputStream inputStream = new FileInputStream(path)) {
                    properties.load(inputStream);
                }

                String username = properties.getProperty("Email");
                String password = properties.getProperty("Password");

                if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
                    userId.setText(username);
                    passId.setText(password);
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void Deleteinfo(String path) {
        File file = new File(path);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("File deleted successfully.");
            } else {
                System.out.println("Failed to delete the file.");
            }
        }
    }

    public void updateIniFile(String path, String newUser, String newPass) {
        try {
            Properties properties = new Properties();

            try (InputStream inputStream = new FileInputStream(path)) {
                properties.load(inputStream);
            }

            properties.setProperty("Email", newUser);
            properties.setProperty("Password", newPass);

            try (OutputStream outputStream = new FileOutputStream(path)) {
                properties.store(outputStream, "Login data");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
       public String getRoleByEmail(String email) {
    String role = "Unknown"; // Default value

    String query = "SELECT `Role` FROM user WHERE Email = ?";

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setString(1, email);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            role = resultSet.getString("Role");
                    }
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle the exception as needed
    }

    return role;
}

    
    
    
  public User getuserbyemailandpass(String email, String Mdp) {
    User user = null;
    try {
        String query = "SELECT * FROM user WHERE Email = ? AND Mdp = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, email);
        statement.setString(2, Mdp);
        ResultSet result = statement.executeQuery();

        if (result.next()) {
            user = new User();
            user.setId(result.getInt("id"));
            user.setEmail(result.getString("email"));
            user.setMdp(result.getString("Mdp"));
            // Set other user attributes from the database
            user.setNom(result.getString("nom"));
            user.setPrenom(result.getString("prenom"));
            user.setRole(Role.valueOf(result.getString("Role")));
             user.setImg(result.getString("Img"));
                user.setAge(result.getInt("Age"));
            // Add more attributes as needed

            // Print the user for debugging or verification
            System.out.println("User Retrieved: " + user.toString());
        }
        statement.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return user;
}




}
