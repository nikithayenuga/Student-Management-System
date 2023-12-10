package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Scholarship;
import models.Dao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import models.Scholarship;
import models.Dao;
import controllers.ScholarshipController;
import java.io.IOException;
import java.util.List;
import utils.UserRoleHolder;

public class ScholarshipDetailsController {
    @FXML private TableView<Scholarship> scholarshipTableView;
    @FXML private TableColumn<Scholarship, String> nameColumn;
    @FXML private TableColumn<Scholarship, Double> amountColumn;
    @FXML private TableColumn<Scholarship, String> descriptionColumn;
    @FXML private Button addButton;
    @FXML private Button editButton;
    @FXML private Button deleteButton;
    private Dao dao = new Dao();


    @FXML
    private void initialize() {
        configureTable();
        loadScholarships();
        configureButtons();
    }

    private void configureTable() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("scholarshipName"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        loadScholarships();
    }

    private void loadScholarships() {
        List<Scholarship> scholarships = dao.getAllScholarships();
        scholarshipTableView.getItems().setAll(scholarships);
    }

    private void refreshTable() {
        loadScholarships();
    }

    private void configureButtons() {
        boolean isAdmin = "Admin".equals(UserRoleHolder.userRole);
        addButton.setVisible(isAdmin);
        editButton.setVisible(isAdmin);
        deleteButton.setVisible(isAdmin);
    }

    @FXML
    private void handleAdd(ActionEvent event) throws IOException {
        loadScholarshipView(event, null);
    }

    @FXML
    private void handleEdit(ActionEvent event) throws IOException {
        Scholarship selected = scholarshipTableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            loadScholarshipView(event, selected);
        }
    }

    private void loadScholarshipView(ActionEvent event, Scholarship scholarship) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ScholarshipView.fxml"));
        Parent root = loader.load();

        ScholarshipController controller = loader.getController();
        controller.initializeWithData(scholarship); // New method to initialize with data

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }


    @FXML
    private void handleDelete() {
        Scholarship selected = scholarshipTableView.getSelectionModel().getSelectedItem();
        if (selected != null && dao.deleteScholarship(selected.getScholarshipId())) {
            refreshTable(); // Refresh the table
        } else {
            showAlert("Error", "Deletion failed");
        }
    }

    @FXML
    private void handleLogout(ActionEvent event) throws IOException {
        Parent loginViewParent = FXMLLoader.load(getClass().getResource("/views/LoginView.fxml"));
        Scene loginViewScene = new Scene(loginViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginViewScene);
        window.show();
    }
    
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
