package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.University;
import models.Dao;

import java.io.IOException;
import java.util.List;

public class UniversitySelectionController {
    @FXML private ComboBox<University> universityComboBox;
    
    private Dao dao = new Dao();

    @FXML
    private void initialize() {
        List<University> universities = dao.getAllUniversities();
        universityComboBox.getItems().setAll(universities);
        universityComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                loadCourseView();
            }
        });
    }

    private void loadCourseView() {
        try {
            Parent courseViewParent = FXMLLoader.load(getClass().getResource("/views/CourseSelectionView.fxml"));
            Scene courseViewScene = new Scene(courseViewParent);

            Stage window = (Stage) universityComboBox.getScene().getWindow();
            window.setScene(courseViewScene);
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
