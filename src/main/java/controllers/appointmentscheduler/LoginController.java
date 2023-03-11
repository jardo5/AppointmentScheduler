package controllers.appointmentscheduler;

import database.appointmentscheduler.Query;
import database.appointmentscheduler.UserSQL;
import java.net.URL;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import models.appointmentscheduler.Users;

public class LoginController implements Initializable {

  public Button loginButton;
  public Button exitButton;
  public TextField usernameField;
  public TextField passwordField;
  public Label languageLabel;
  public Label zoneLabel;

  public void loginButtonClick(ActionEvent actionEvent) throws Exception {
    try {
      Users loggedUser = UserSQL.loggedUser(
        usernameField.getText(),
        passwordField.getText()
      );

      if (loggedUser != null) { // check if a user was successfully logged in
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene()
          .getWindow();
        FXMLLoader loader = new FXMLLoader(
          getClass().getResource("/jarod/appointmentscheduler/main.fxml")
        );
        Scene scene = new Scene(loader.load(), 1300, 605);
        stage.setTitle("Appointment Scheduler");
        stage.setScene(scene);
        stage.show();
      } else {
        // show an error message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Login Error");
        alert.setHeaderText("Invalid username or password");
        alert.setContentText("Please try again");
        alert.showAndWait();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void exitButtonClick(ActionEvent actionEvent) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Exit Appointment Scheduler");
    alert.setHeaderText("Are you sure you want to exit?");
    alert.setContentText("Click OK to exit.");
    alert
      .showAndWait()
      .ifPresent(response -> {
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
    usernameField.setPromptText(Language.getString("Username"));
    passwordField.setPromptText(Language.getString("Password"));
    loginButton.setText(Language.getString("Login"));
    exitButton.setText(Language.getString("Exit"));
    languageLabel.setText(String.valueOf(Locale.getDefault()));
    zoneLabel.setText(String.valueOf(zoneId));
  }
}
