package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import models.Stream;
import models.Dao;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class StreamSelectionController {
    @FXML private ComboBox<Stream> streamComboBox;
    
    private Dao dao = new Dao();

    @FXML
    private void initialize() {
        List<Stream> streams = dao.getAllStreams();
        streamComboBox.getItems().setAll(streams);
        streamComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                loadScholarshipDetailsView();
            }
        });
    }

    private void loadScholarshipDetailsView() {
        try {
            Parent scholarshipDetailsViewParent = FXMLLoader.load(getClass().getResource("/views/ScholarshipDetailsView.fxml"));
            Scene scholarshipDetailsViewScene = new Scene(scholarshipDetailsViewParent);

            Stage window = (Stage) streamComboBox.getScene().getWindow();
            window.setScene(scholarshipDetailsViewScene);
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
