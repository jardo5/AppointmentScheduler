package controllers.appointmentscheduler;

import database.appointmentscheduler.AppSQL;
import database.appointmentscheduler.ContactsSQL;
import database.appointmentscheduler.CustomersSQL;
import database.appointmentscheduler.UserSQL;
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

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ModifyAppointmentController implements Initializable {
  public Button appSaveButton;
  public Button appCancelButton;

  public TextField appID;
  public TextField appTitle;
  public TextField appDescription;
  public TextField appLocation;
  public TextField appType;

  public DatePicker appStartDate;
  public ComboBox appStartTimeHours;
  public ComboBox appStartTimeMinutes;

  public DatePicker appEndDate;
  public ComboBox appEndTimeHours;
  public ComboBox appEndTimeMinutes;

  public ComboBox appContactBox;
  public ComboBox appCustomerID;
  public ComboBox appUserID;

  private static Appointments selectedAppointment = null;

  public static void retrieveAppointments(Appointments appointments) {
    selectedAppointment = appointments;
  }


  public void appSaveButtonClick(ActionEvent actionEvent) throws SQLException {
    // if anything is empty, throw an error
    if (appTitle.getText().isEmpty() || appDescription.getText().isEmpty() || appLocation.getText().isEmpty() || appType.getText().isEmpty() || appStartDate.getValue() == null || appStartTimeHours.getValue() == null || appStartTimeMinutes.getValue() == null || appEndDate.getValue() == null || appEndTimeHours.getValue() == null || appEndTimeMinutes.getValue() == null || appContactBox.getValue() == null || appCustomerID.getValue() == null || appUserID.getValue() == null) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("Please fill out all fields.");
      alert.setContentText("Click OK to continue.");
      alert.showAndWait();
      return;
    }
    int tempID = Integer.parseInt(appID.getText());
    String tempTitle = appTitle.getText();
    String tempDescription = appDescription.getText();
    String tempLocation = appLocation.getText();
    int tempContactID = ((Contacts) appContactBox.getSelectionModel().getSelectedItem()).getContact_ID();
    String tempType = appType.getText();
    int tempCustomerID = ((Customers) appCustomerID.getSelectionModel().getSelectedItem()).getCustomer_ID();
    int tempUserID = ((Users) appUserID.getSelectionModel().getSelectedItem()).getUser_ID();
    LocalDateTime tempStart = LocalDateTime.of(appStartDate.getValue(), LocalTime.parse(appStartTimeHours.getValue() + ":" + appStartTimeMinutes.getValue()));
    LocalDateTime tempEnd = LocalDateTime.of(appEndDate.getValue(), LocalTime.parse(appEndTimeHours.getValue() + ":" + appEndTimeMinutes.getValue()));


    // if the start time is after the end time, throw an error
    if (tempStart.isAfter(tempEnd)) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("Start time cannot be after end time.");
      alert.setContentText("Click OK to continue.");
      alert.showAndWait();
      return;
    }
    // If start and end time are not between 8am and 10pm
    if (tempStart.getHour() < 8 || tempStart.getHour() > 22 || tempEnd.getHour() < 8 || tempEnd.getHour() > 22) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("Start and end time must be between 8am and 10pm.");
      alert.setContentText("Click OK to continue.");
      alert.showAndWait();
      return;
    }
    //If start and end date are in the same day and start time is after end time
    if (tempStart.getDayOfYear() == tempEnd.getDayOfYear() && tempStart.isAfter(tempEnd)) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("Start time cannot be after end time.");
      alert.setContentText("Click OK to continue.");
      alert.showAndWait();
      return;
    }
    ObservableList<Appointments> tempAppointments = AppSQL.getAppointmentsContacts(tempContactID);
    for (Appointments app : tempAppointments){
      if (app.getStart().isBefore(tempStart) && app.getEnd().isAfter(tempStart) && app.getAppointment_ID() != tempID){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Contact is busy at this time.");
        alert.setContentText("Click OK to continue.");
        alert.showAndWait();
        return;
      }
    }
    //If ID is not empty add all to database
    if (!appID.getText().isEmpty()) {
      try {
        AppSQL.updateAppointment(tempID, tempTitle, tempDescription, tempLocation, tempContactID, tempType, tempStart, tempEnd, tempCustomerID, tempUserID);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Appointment successfully updated.");
        alert.setContentText("Click OK to continue.");
        alert.showAndWait().ifPresent(rs -> {
          if (rs == ButtonType.OK) {
            try {
              Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene()
                .getWindow();
              FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/jarod/appointmentscheduler/main.fxml")
              );
              Scene scene = new Scene(loader.load(), 1300, 650);
              stage.setTitle("Appointment Scheduler");
              stage.setScene(scene);
              stage.show();
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        });
      } catch (Exception e) {
        e.printStackTrace();
      }

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
            Scene scene = new Scene(loader.load(), 1300, 650);
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
      appContactBox.getItems().addAll(ContactsSQL.getAllContacts()); // works
      appContactBox.setValue(ContactsSQL.getContactName(selectedAppointment.getContact_ID())); // works
      //Test
      System.out.println(ContactsSQL.getContactName(selectedAppointment.getContact_ID()));


      appCustomerID.getItems().addAll(CustomersSQL.getAllCustomers()); // works
      appCustomerID.setValue(CustomersSQL.getCustomer(selectedAppointment.getCustomer_ID())); // works

      //Test
      System.out.println(selectedAppointment.getCustomer_ID()); //TODO: Fix, only shows customer_ID not customer name

      appUserID.getItems().addAll(UserSQL.getAllUsers());

      appUserID.setValue(UserSQL.getUser(selectedAppointment.getUser_ID())); // TODO: 4/26/2021 Fix this only shows user_ID not user name

    } catch (SQLException e) {
      throw new RuntimeException(e);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    if (selectedAppointment != null){
        appID.setText(String.valueOf(selectedAppointment.getAppointment_ID()));
        appTitle.setText(selectedAppointment.getTitle());
        appDescription.setText(selectedAppointment.getDescription());
        appLocation.setText(selectedAppointment.getLocation());
        appType.setText(selectedAppointment.getType());

        appStartDate.setValue(selectedAppointment.getStart().toLocalDate());
        appStartTimeHours.setValue(String.format("%02d", selectedAppointment.getStart().toLocalTime().getHour()) + ":00");
        appStartTimeMinutes.setValue(String.format("%02d", selectedAppointment.getStart().toLocalTime().getMinute()));

        appEndDate.setValue(selectedAppointment.getEnd().toLocalDate());
        appEndTimeHours.setValue(String.format("%02d", selectedAppointment.getEnd().toLocalTime().getHour()) + ":00");
        appEndTimeMinutes.setValue(String.format("%02d", selectedAppointment.getEnd().toLocalTime().getMinute()));

        appCustomerID.setValue(selectedAppointment.getCustomer_ID());
        appUserID.setValue(selectedAppointment.getUser_ID());
    } else {
      //Test
      System.out.println("Failed");
    }

  }


}
