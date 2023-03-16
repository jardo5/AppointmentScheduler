package controllers.appointmentscheduler;

import database.appointmentscheduler.AppSQL;
import database.appointmentscheduler.Query;
import database.appointmentscheduler.UserSQL;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import models.appointmentscheduler.Appointments;
import models.appointmentscheduler.Users;

/**
 * handles the logic of the Login screen
 */

public class LoginController implements Initializable {

  public Button loginButton;
  public Button exitButton;
  public TextField usernameField;
  public TextField passwordField;
  public Label languageLabel;
  public Label zoneLabel;

  /**
   * This method is called when the login button is called. Validates login and then starts main scene.
   * @param actionEvent
   * @throws Exception
   */

  public void loginButtonClick(ActionEvent actionEvent) throws Exception {
    try {
      Users loggedUser = UserSQL.loggedUser(
        usernameField.getText(),
        passwordField.getText()
      );

      if (loggedUser != null) { // If user is found
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene()
          .getWindow();
        FXMLLoader loader = new FXMLLoader(
          getClass().getResource("/jarod/appointmentscheduler/main.fxml")
        );
        Scene scene = new Scene(loader.load(), 1300, 650);
        stage.setTitle("Appointment Scheduler");
        stage.setScene(scene);
        stage.show();

        //Alert if an appointment is within 15 minutes
        checkAppointmentsWithin15Minutes();
        System.out.println("Current time: " + LocalDateTime.now());

        //login_activity successful attempt
        System.out.println("Successful username or password add to login_activity.txt");
        PrintWriter pw = new PrintWriter( new FileOutputStream(("src/main/java/login_activity.txt"),true));
        pw.append("Success: User: " + loggedUser.getUser_Name() + " logged in at " + LocalDateTime.now() + ".\n");
        pw.close();


      } else {
        System.out.println("Invalid username or password add to login_activity.txt");
        //login_activity failed attempt
        PrintWriter pw = new PrintWriter( new FileOutputStream(("src/main/java/login_activity.txt"),true));
        pw.append("Failed: User: " + usernameField.getText() + " attempted to log in at " + LocalDateTime.now() + ".\n");
        pw.close();

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

    /**
     * This method is called when the exit button is called. Exits the program.
     * @param actionEvent
     */

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

  /**
   * Checks if there are any appointments within 15 minutes of the current time
   * @throws SQLException
   */


  public static void checkAppointmentsWithin15Minutes() throws SQLException {
    ObservableList<Appointments> allAppointments = AppSQL.getAllAppointments();
    ObservableList<Appointments> fifteenMinAppointments = FXCollections.observableArrayList();

    // Get all appointments within 15 minutes
    for (Appointments appointment : allAppointments) {
      if (appointment.getStart().isBefore(LocalDateTime.now().plusMinutes(15)) && appointment.getStart().isAfter(LocalDateTime.now())) {
        fifteenMinAppointments.add(appointment);
      }
    }

    if (fifteenMinAppointments.size() > 0) {
      StringBuilder sb = new StringBuilder();
      for (Appointments appointment : fifteenMinAppointments) {
        sb.append("Appointment ID: ").append(appointment.getAppointment_ID())
                .append(", Date: ").append(appointment.getStart().toLocalDate())
                .append(", Time: ").append(appointment.getStart().toLocalTime())
                .append("\n");
      }

      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Appointment Alert");
      alert.setHeaderText("There is an appointment within 15 minutes");
      alert.setContentText("Upcoming appointments:\n" + sb.toString());
      alert.showAndWait();
    } else {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Appointment Alert");
      alert.setHeaderText("There are no appointments within 15 minutes");
      alert.setContentText("You have no upcoming appointments within the next 15 minutes.");
      alert.showAndWait();
    }
  }

  private ResourceBundle Language;
  ZoneId zoneId = ZoneId.systemDefault();
  /**
   * This method is called when the program is initialized. Sets the language and timezone.
   * @param url
   * @param Language
   */

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
