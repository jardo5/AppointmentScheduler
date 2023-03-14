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
import java.util.ResourceBundle;

public class ModifyAppointmentController implements Initializable {
  public Button appSaveButton;
  public Button appCancelButton;

  public TextField appID;
  public TextField appTitle;
  public TextField appDescription;
  public TextField appLocation;
  public ComboBox appContactBox;
  public TextField appType;

  public DatePicker appStartDate;
  public ComboBox appStartTimeHours;
  public ComboBox appStartTimeMinutes;

  public DatePicker appEndDate;
  public ComboBox appEndTimeHours;
  public ComboBox appEndTimeMinutes;

  public ComboBox appCustomerID;
  public ComboBox appUserID;

  private static Appointments selectedAppointment = null;

  public static void retrieveAppointments(Appointments appointments) {
    selectedAppointment = appointments;
  }


  public void appSaveButtonClick(ActionEvent actionEvent) {

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
      appContactBox.getItems().addAll(ContactsSQL.getAllContacts());
      appContactBox.setValue(ContactsSQL.getContact(selectedAppointment.getContact_ID()));

      appCustomerID.getItems().addAll(CustomersSQL.getAllCustomers());
      appCustomerID.setValue(CustomersSQL.getCustomer(selectedAppointment.getCustomer_ID()));//TODO: 4/26/2021 Fix this

      appUserID.getItems().addAll(UserSQL.getAllUsers());
      appUserID.setValue(UserSQL.getUser(selectedAppointment.getUser_ID())); // TODO: 4/26/2021 Fix this
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
