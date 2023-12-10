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
import models.Stream;
import models.Dao;

import java.io.IOException;

public class StreamController {
    @FXML private TextField streamNameField;
    private Dao dao = new Dao();

    @FXML
    private void addStream(ActionEvent event) throws IOException {
        String name = streamNameField.getText();
        Stream stream = new Stream(0, name);
        boolean isSuccess = dao.addStream(stream);

        if (isSuccess) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "Stream added successfully!");
            loadScholarshipDetailsView(event);
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to add Stream. Please try again.");

        }
    }

    private void loadScholarshipDetailsView(ActionEvent event) throws IOException {
        Parent scholarshipDetailsViewParent = FXMLLoader.load(getClass().getResource("/views/ScholarshipView.fxml"));
        Scene scholarshipDetailsViewScene = new Scene(scholarshipDetailsViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scholarshipDetailsViewScene);
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
