package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Scholarship;
import models.Dao;

import java.io.IOException;

public class ScholarshipController {
    @FXML private TextField scholarshipNameField;
    @FXML private TextField amountField;
    @FXML private TextArea descriptionField;
    private Scholarship currentScholarship;
    private boolean isEditMode = false;

    private Dao dao = new Dao();

    @FXML
    private void addScholarship(ActionEvent event) throws IOException {
        String name = scholarshipNameField.getText();
        double amount = Double.parseDouble(amountField.getText());
        String description = descriptionField.getText();
        
        if (isEditMode && currentScholarship != null) {
            currentScholarship.setScholarshipName(name);
            currentScholarship.setAmount(amount);
            currentScholarship.setDescription(description);
            boolean isSuccess = dao.updateScholarship(currentScholarship);
            if (isSuccess) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Scholarship updated successfully!");
                loadScholarshipDetailsView(event);
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to update scholarship. Please try again.");
            }
            
        } else {
            Scholarship newScholarship = new Scholarship(0, name, description, amount);
            boolean isSuccess = dao.addScholarship(newScholarship);
            
            if (isSuccess) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Scholarship added successfully!");
                loadScholarshipDetailsView(event);
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to add scholarship. Please try again.");
            }
        }

    }

    private void loadScholarshipDetailsView(ActionEvent event) throws IOException {
        Parent scholarshipDetailsViewParent = FXMLLoader.load(getClass().getResource("/views/ScholarshipDetailsView.fxml"));
        Scene scholarshipDetailsViewScene = new Scene(scholarshipDetailsViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scholarshipDetailsViewScene);
        window.show();
    }
    
    public void setScholarship(Scholarship scholarship) {
        this.currentScholarship = scholarship;

        // Populate the fields with scholarship details
        if (scholarship != null) {
            scholarshipNameField.setText(scholarship.getScholarshipName());
            amountField.setText(String.valueOf(scholarship.getAmount()));
            descriptionField.setText(scholarship.getDescription());
        }
    }

    public void initializeWithData(Scholarship scholarship) {
        if (scholarship != null) {
            this.currentScholarship = scholarship;
            scholarshipNameField.setText(scholarship.getScholarshipName());
            amountField.setText(String.valueOf(scholarship.getAmount()));
            descriptionField.setText(scholarship.getDescription());
            isEditMode = true;
        }
    }
    
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
