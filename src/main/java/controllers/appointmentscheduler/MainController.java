package controllers.appointmentscheduler;

import jarod.appointmentscheduler.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    public Button logoutButton;
    public ToggleGroup appointmentGroup;

    public void logoutButtonClick(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("Are you sure you want to logout?");
        alert.setContentText("Click OK to logout.");
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Logging Out");
                MainApplication loginScene = new MainApplication();
                try {
                    loginScene.start(new Stage());
                    Stage stage = (Stage) logoutButton.getScene().getWindow();
                    stage.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
    public void addAppButtonClick(ActionEvent actionEvent){
        try {
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/jarod/appointmentscheduler/addAppointment.fxml"));
            Scene scene = new Scene(loader.load(), 600, 300);
            stage.setTitle("Add Appointment");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void appModifyButtonClick(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/jarod/appointmentscheduler/modifyAppointment.fxml"));
            Scene scene = new Scene(loader.load(), 600, 300);
            stage.setTitle("Modify Appointment");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}