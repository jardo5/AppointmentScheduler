package controllers.appointmentscheduler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;



public class LoginController implements Initializable {


    public Button loginButton;
    public Button exitButton;

    public void loginButtonClick(ActionEvent actionEvent) throws Exception{
        //open new window
        try {
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/jarod/appointmentscheduler/main.fxml"));
            Scene scene = new Scene(loader.load(), 1200, 605);
            stage.setTitle("Appointment Scheduler");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void exitButtonClick(ActionEvent actionEvent){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Appointment Scheduler");
        alert.setHeaderText("Are you sure you want to exit?");
        alert.setContentText("Click OK to exit.");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Stage stage = (Stage) exitButton.getScene().getWindow();
                System.out.println("Jarod Schupp - C195 - 2023");
                stage.close();
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    };

}
