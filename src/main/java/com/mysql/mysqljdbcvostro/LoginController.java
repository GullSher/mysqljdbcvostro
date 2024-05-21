/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mysql.mysqljdbcvostro;

import java.net.URL;
import java.sql.Connection;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author GULL SHER
 */

public class LoginController implements Initializable {

    public TextField usernameField; //usernameField
    public PasswordField passwordField;  //passwordField
    private Connection connection;
   /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/wgcsite1", "root", "");
        String dbName = connection.getCatalog(); // Get the name of the connected database
        System.out.println("Database connection established with database"+dbName);
       
             
        
               
          
        
        
    } catch (ClassNotFoundException | SQLException ex) {
        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    // Initialize your database connection here
        
    
    public void login() {
        String username=usernameField.getText();
        String password =passwordField.getText();
        
        try {
            // Prepare SQL statement
            String query = "SELECT * FROM login WHERE username=? AND password=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            
            // Execute query
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                // Login successful
                showAlert("Login Successful", "Welcome, " + username + "!");
                
                openMainForm();
                             
                    
                
                
                
            } else {
                // Login failed
                showAlert("Login Failed", "Invalid username or password.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "An error occurred while trying to log in." +  e.getMessage());
        }
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();



    }


    
    private void openMainForm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainForm.fxml"));
            Parent root = loader.load();
            
            Scene scene = new Scene(root);
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Main Form");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "An error occurred while opening the Main Form: " + e.getMessage());
        }
    }
    
    




    
    
}

