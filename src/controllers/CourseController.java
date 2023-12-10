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
import models.Course;
import models.Dao;

import java.io.IOException;

public class CourseController {
    @FXML private TextField courseNameField;
    @FXML private TextField universityIdField;

    private Dao dao = new Dao();

    @FXML
    private void addCourse(ActionEvent event) throws IOException {
        String courseName = courseNameField.getText();
        int universityId = Integer.parseInt(universityIdField.getText());
        Course course = new Course(0, courseName, universityId);
        boolean isSuccess = dao.addCourse(course);

        if (isSuccess) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "Course added successfully!");
            loadStreamView(event);
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to add course. Please try again.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void loadStreamView(ActionEvent event) throws IOException {
        Parent streamViewParent = FXMLLoader.load(getClass().getResource("/views/StreamView.fxml"));
        Scene streamViewScene = new Scene(streamViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(streamViewScene);
        window.show();
    }

    public void setUniversityId(int universityId) {
        universityIdField.setText(String.valueOf(universityId));
    }
}