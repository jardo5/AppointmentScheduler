package controllers.appointmentscheduler;

import database.appointmentscheduler.AppSQL;
import database.appointmentscheduler.ContactsSQL;
import database.appointmentscheduler.CustomersSQL;
import database.appointmentscheduler.UserSQL;
import java.net.URL;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.appointmentscheduler.Appointments;
import models.appointmentscheduler.Contacts;
import models.appointmentscheduler.Customers;
import models.appointmentscheduler.Users;

public class AddAppointmentController implements Initializable {

  public TextField appID;
  public TextField appTitle;
  public TextField appDescription;
  public TextField appLocation;
  public ComboBox appContactBox;
  public TextField appType;

  public ComboBox appStartTimeHours;
  public ComboBox appStartTimeMinutes;
  public DatePicker appStartDate;

  public DatePicker appEndDate;
  public ComboBox appEndTimeHours;
  public ComboBox appEndTimeMinutes;

  public ComboBox appCustomerID;
  public ComboBox appUserID;

  public Button appSaveButton;
  public Button appCancelButton;

  public void appSaveButtonClick(ActionEvent actionEvent) {
    int tempID = Integer.parseInt(appID.getText());
    String tempTitle = appTitle.getText();
    String tempDescription = appDescription.getText();
    String tempLocation = appLocation.getText();
    String tempType = appType.getText();

    try {
      LocalDateTime tempStart = appStartDate
        .getValue()
        .atTime(
          Integer.parseInt(
            appStartTimeHours
              .getSelectionModel()
              .getSelectedItem()
              .toString()
              .substring(0, 2)
          ),
          Integer.parseInt(
            appStartTimeMinutes
              .getSelectionModel()
              .getSelectedItem()
              .toString()
              .substring(0, 2)
          )
        );

      LocalDateTime tempEnd = appEndDate
        .getValue()
        .atTime(
          Integer.parseInt(
            appEndTimeHours
              .getSelectionModel()
              .getSelectedItem()
              .toString()
              .substring(0, 2)
          ),
          Integer.parseInt(
            appEndTimeMinutes
              .getSelectionModel()
              .getSelectedItem()
              .toString()
              .substring(0, 2)
          )
        );
      int tempCustomerID =
        (
          (Customers) appCustomerID.getSelectionModel().getSelectedItem()
        ).getCustomer_ID();
      int tempUserID =
        ((Users) appUserID.getSelectionModel().getSelectedItem()).getUser_ID();
      int tempContactID = appContactBox.getSelectionModel().getSelectedItem() ==
        null
        ? -1
        : (
          (Contacts) appContactBox.getSelectionModel().getSelectedItem()
        ).getContact_ID();

      try {
        if (tempEnd.isBefore(tempStart) || tempEnd.isEqual(tempStart)) {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Error");
          alert.setHeaderText("Invalid End Date");
          alert.setContentText("The end date must be after the start date.");
          alert.showAndWait();
          return;
        }
        LocalDateTime startEST = AppSQL.localToEST(tempStart);
        LocalDateTime endEST = AppSQL.localToEST(tempEnd);
        Duration duration = Duration.between(startEST, endEST);
        if (duration.toHours() > 14) {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Error");
          alert.setHeaderText("Invalid");
          alert.setContentText(
            "Meeting must be between 8:00 AM and 10:00 PM EST."
          );
          alert.showAndWait();
          return;
        }
        if (
          startEST.getHour() < 8 ||
          startEST.getHour() >= 22 ||
          endEST.getHour() < 8 ||
          endEST.getHour() >= 22
        ) {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Error");
          alert.setHeaderText("Invalid Time");
          alert.setContentText(
            "The meeting time must be between 8:00 AM and 10:00 PM EST."
          );
          alert.showAndWait();
          return;
        }

        ObservableList<Appointments> custAppointments = AppSQL.getAppointmentsContacts(
          tempContactID
        );
        for (Appointments app : custAppointments) {
          LocalDateTime appStart = app.getStart();
          LocalDateTime appEnd = app.getEnd();
          LocalDate appDate = appStart.toLocalDate();
          LocalTime appStartTime = appStart.toLocalTime();
          LocalTime appEndTime = appEnd.toLocalTime();
          if (
            tempContactID == app.getContact_ID() &&
            appDate.equals(tempStart.toLocalDate())
          ) {
            if (
              tempStart.toLocalTime().isAfter(appStartTime.minusMinutes(1)) &&
              tempStart.toLocalTime().isBefore(appEndTime.plusMinutes(1))
            ) {
              Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.setTitle("Error");
              alert.setHeaderText("Invalid");
              alert.setContentText(
                "Contact is already scheduled for another meeting during this time."
              );
              alert.showAndWait();
              return;
            }
            if (
              tempEnd.toLocalTime().isAfter(appStartTime.minusMinutes(1)) &&
              tempEnd.toLocalTime().isBefore(appEndTime.plusMinutes(1))
            ) {
              Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.setTitle("Error");
              alert.setHeaderText("Invalid");
              alert.setContentText(
                "Contact is already scheduled for another meeting during this time."
              );
              alert.showAndWait();
              return;
            }
          }
        }

        if (
          tempTitle != "" &&
          tempDescription != "" &&
          tempLocation != "" &&
          tempType != "" &&
          tempStart != null &&
          tempEnd != null
        ) {
          AppSQL.addAppointment(
            tempID,
            tempTitle,
            tempDescription,
            tempLocation,
            tempContactID,
            tempType,
            tempStart,
            tempEnd,
            tempCustomerID,
            tempUserID
          );
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Success");
          alert.setHeaderText("Appointment Added");
          alert.setContentText("Appointment has been added to the database.");
          alert.showAndWait();
          Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene()
            .getWindow();
          FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/jarod/appointmentscheduler/main.fxml")
          );
          Scene scene = new Scene(loader.load());
          stage.setScene(scene);
          stage.show();
        } else {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Error");
          alert.setHeaderText("Invalid");
          alert.setContentText("Please fill out all fields.");
          alert.showAndWait();
          return;
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    } catch (Exception e) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("Invalid");
      alert.setContentText("Please fill out all fields.");
      alert.showAndWait();
      return;
    }
  }

  public void appCancelButtonClick(ActionEvent actionEvent) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Cancel");
    alert.setHeaderText("Are you sure you want to cancel?");
    alert.setContentText("Click OK to cancel. All progress will be lost.");
    alert
      .showAndWait()
      .ifPresent(rs -> {
        if (rs == ButtonType.OK) {
          try {
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene()
              .getWindow();
            FXMLLoader loader = new FXMLLoader(
              getClass().getResource("/jarod/appointmentscheduler/main.fxml")
            );
            Scene scene = new Scene(loader.load(), 1200, 605);
            stage.setTitle("Appointment Scheduler");
            stage.setScene(scene);
            stage.show();
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      });
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    //Make a list of hours and a list of minutes for the combobox
    ObservableList<String> hours = FXCollections.observableArrayList();
    ObservableList<String> minutes = FXCollections.observableArrayList();
    for (int i = 0; i < 24; i++) {
      if (i < 10) {
        hours.add("0" + i + ":00");
        hours.add("0" + i + ":30");
      } else {
        hours.add(i + ":00");
        hours.add(i + ":30");
      }
    }
    for (int i = 0; i < 60; i++) {
      if (i < 10) {
        minutes.add("0" + i);
      } else {
        minutes.add(i + "");
      }
    }
    appStartTimeHours.setItems(hours);
    appStartTimeMinutes.setItems(minutes);
    appEndTimeHours.setItems(hours);
    appEndTimeMinutes.setItems(minutes);
    try {
      appID.setText(AppSQL.autoAppointmentID() + "");
      appContactBox.getItems().addAll(ContactsSQL.getAllContacts()); //Shows models.Contacts@1c4c4b1?? TODO: Fix this
      appCustomerID.getItems().addAll(CustomersSQL.getAllCustomers()); // Shows models.Customers@1c4c4b1?? TODO: Fix this
      appUserID.getItems().addAll(UserSQL.getAllUsers()); // Shows models.Users@1c4c4b1?? TODO: Fix this
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
