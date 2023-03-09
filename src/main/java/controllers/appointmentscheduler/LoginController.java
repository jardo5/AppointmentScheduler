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
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;




public class LoginController implements Initializable {


    public Button loginButton;
    public Button exitButton;
    public TextField usernameField;
    public TextField passwordField;
    public Label languageLabel;
    public Label zoneLabel;

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

    private ResourceBundle Language;
    ZoneId zoneId = ZoneId.systemDefault();

    @Override
    public void initialize(URL url, ResourceBundle Language) {

        this.Language = Language;
        System.out.println("You're language is set to " + Locale.getDefault());
        System.out.println("Your time zone is set to " + TimeZone.getDefault());
        usernameField.setPromptText(Language.getString("Username"));
        passwordField.setPromptText(Language.getString("Password"));
        loginButton.setText(Language.getString("Login"));
        exitButton.setText(Language.getString("Exit"));
        languageLabel.setText(String.valueOf(Locale.getDefault()));
        zoneLabel.setText(String.valueOf(zoneId));
    };
}
