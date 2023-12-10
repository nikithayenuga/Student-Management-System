package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Dao;
import models.Student;
import utils.UserRoleHolder;

import java.io.IOException;
import utils.SecurityUtil;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label statusLabel;

    private Dao dao = new Dao();
    

    
    public void login(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String hashedPassword = SecurityUtil.hashPassword(passwordField.getText());

        Student student = dao.getUserByUsername(username);

        if (student != null && student.getPassword().equals(hashedPassword)) {
        	UserRoleHolder.userRole = student.getRole();
            statusLabel.setText("Login successful.");

            String viewPath = "";
            if ("Admin".equals(student.getRole())) {
                viewPath = "/views/UniversityView.fxml"; // Path to Admin's university view
            } else if ("Student".equals(student.getRole())) {
                viewPath = "/views/UniversitySelectionView.fxml"; // Path to Student's university selection view
            }

            // Load the determined view
            if (!viewPath.isEmpty()) {
                Parent nextViewParent = FXMLLoader.load(getClass().getResource(viewPath));
                Scene nextViewScene = new Scene(nextViewParent);

                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(nextViewScene);
                window.show();
            } else {
                statusLabel.setText("Role not recognized.");
            }
        } else {
            statusLabel.setText("Invalid username or password.");
        }
    }

    public void openSignup(ActionEvent event) throws IOException {
        Parent signupViewParent = FXMLLoader.load(getClass().getResource("/views/SignupView.fxml"));
        Scene signupViewScene = new Scene(signupViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(signupViewScene);
        window.show();
    }
}
