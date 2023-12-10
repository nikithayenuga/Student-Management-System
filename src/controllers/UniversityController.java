package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Dao;
import models.University;

import java.io.IOException;

public class UniversityController {
    @FXML
    private TextField universityNameField;
    @FXML
    private TextField locationField;

    private Dao dao = new Dao();

    @FXML
    private void addUniversity(ActionEvent event) throws IOException {
        String name = universityNameField.getText();
        String location = locationField.getText();
        University university = new University(0, name, location);
        boolean isSuccess = dao.addUniversity(university);

        if (isSuccess) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "University added successfully!");
            universityNameField.clear();
            locationField.clear();
            // Parse the university ID as integer
            loadCourseView(event, university.getuniversityId());
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to add university. Please try again.");
        }
    }

    private void loadCourseView(ActionEvent event, int universityId) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/CourseView.fxml"));
        Parent courseViewParent = loader.load();

        CourseController courseController = loader.getController();
        courseController.setUniversityId(universityId);

        Scene courseViewScene = new Scene(courseViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(courseViewScene);
        window.show();
    }


    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
