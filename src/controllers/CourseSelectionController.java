package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.util.List;

import models.Course;
import models.Dao;

public class CourseSelectionController {
    @FXML private ComboBox<Course> courseComboBox;
    
    private Dao dao = new Dao();

    @FXML
    private void initialize() {
        List<Course> courses = dao.getAllCourses();
        courseComboBox.getItems().setAll(courses);
        courseComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                loadStreamSelectionView();
            }
        });
    }

    private void loadStreamSelectionView() {
        try {
            Parent streamSelectionViewParent = FXMLLoader.load(getClass().getResource("/views/StreamSelectionView.fxml"));
            Scene streamSelectionViewScene = new Scene(streamSelectionViewParent);

            Stage window = (Stage) courseComboBox.getScene().getWindow();
            window.setScene(streamSelectionViewScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Unable to load course view");
        }
    }
    
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
