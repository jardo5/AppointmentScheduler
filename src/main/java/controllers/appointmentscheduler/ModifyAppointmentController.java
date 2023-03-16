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
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * handles the logic of the Modify Appointment screen
 */

public class ModifyAppointmentController implements Initializable {

    /**
     * Appointment Save Button
     */
  public Button appSaveButton;

    /**
     * Appointment Cancel Button
     */
  public Button appCancelButton;

    /**
     * Appointment ID field
     */
  public TextField appID;

    /**
     * Appointment Title field
     */
  public TextField appTitle;

    /**
     * Appointment Description field
     */
  public TextField appDescription;

    /**
     * Appointment Location field
     */
  public TextField appLocation;

    /**
     * Appointment Type field
     */
  public TextField appType;

    /**
     * Appointment Start Date field
     */

  public DatePicker appStartDate;

    /**
     * Appointment Start Time Hours field
     */
  public ComboBox appStartTimeHours;

    /**
     * Appointment Start Time Minutes field
     */
  public ComboBox appStartTimeMinutes;

    /**
     * Appointment End Date field
     */
  public DatePicker appEndDate;

    /**
     * Appointment End Time Hours field
     */
  public ComboBox appEndTimeHours;

    /**
     * Appointment End Time Minutes field
     */
  public ComboBox appEndTimeMinutes;

    /**
     * Appointment Contact Combo Box
     */

  public ComboBox appContactBox;

    /**
     * Appointment Customer ID Combo Box
     */
  public ComboBox appCustomerID;

    /**
     * Appointment User ID Combo Box
     */
  public ComboBox appUserID;

    /**
     * Appointment Created By field
     */

  /**
   * Allows to retrieve the selected appointment from the main screen
   */

  private static Appointments selectedAppointment = null;

  /**
   * Retrieves the selected appointment from the main screen
   * @param appointments
   */

  public static void retrieveAppointments(Appointments appointments) {
    selectedAppointment = appointments;
  }

  /**
   * On save click, saves the appointment to the database
   * @param actionEvent save button click
   * @throws SQLException if the database cannot be reached
   */

  public void appSaveButtonClick(ActionEvent actionEvent) throws SQLException {

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
    int tempCustomerID = Integer.parseInt(appCustomerID.getValue().toString().split(" - ")[0]);
    int tempUserID = Integer.parseInt(appUserID.getValue().toString().split(" - ")[0]);
    LocalDateTime tempStart = LocalDateTime.of(appStartDate.getValue(), LocalTime.parse(appStartTimeHours.getValue() + ":" + appStartTimeMinutes.getValue()));
    LocalDateTime tempEnd = LocalDateTime.of(appEndDate.getValue(), LocalTime.parse(appEndTimeHours.getValue() + ":" + appEndTimeMinutes.getValue()));

    ZoneId estZoneId = ZoneId.of("America/New_York");
    ZonedDateTime tempStartEST = tempStart.atZone(ZoneId.systemDefault()).withZoneSameInstant(estZoneId);
    ZonedDateTime tempEndEST = tempEnd.atZone(ZoneId.systemDefault()).withZoneSameInstant(estZoneId);

    if (tempStart.isAfter(tempEnd)) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("Start time cannot be after end time.");
      alert.setContentText("Click OK to continue.");
      alert.showAndWait();
      return;
    }
    // If start and end time are not between 8am and 10pm EST
    if (tempStartEST.getHour() < 8 || tempStartEST.getHour() >= 22 || tempEndEST.getHour() < 8 || tempEndEST.getHour() >= 22) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("Start and end time must be between 8am and 10pm EST.");
      alert.setContentText("Click OK to continue.");
      alert.showAndWait();
      return;
    }

    ObservableList<Appointments> customerAppointments = AppSQL.getAppointments(tempCustomerID);
    for (Appointments app : customerAppointments) {
      if (tempID != app.getAppointment_ID() && (
              (tempStart.isAfter(app.getStart()) && tempStart.isBefore(app.getEnd())) ||
                      (tempEnd.isAfter(app.getStart()) && tempEnd.isBefore(app.getEnd())) ||
                      (tempStart.isBefore(app.getStart()) && tempEnd.isAfter(app.getEnd())) ||
                      (tempStart.isEqual(app.getStart()) && tempEnd.isEqual(app.getEnd()))
      )) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Overlapping appointments for customer.");
        alert.setContentText("Click OK to continue.");
        alert.showAndWait();
        return;
      }
    }

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

  /**
   * On cancel click, returns to the main screen
   * @param actionEvent
   */


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

  /**
   * Initializes and populates the comboboxes and text fields
   * @param url
   * @param resourceBundle
   */
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


      for (Customers customer : CustomersSQL.getAllCustomers()) {
        appCustomerID.getItems().add(customer.getCustomer_ID() + " - " + customer.getCustomer_Name());
      }


      //Test
      System.out.println(selectedAppointment.getCustomer_ID()); //TODO: Fix, only shows customer_ID not customer name

      for (Users user : UserSQL.getAllUsers()){
        appUserID.getItems().add(Users.getUser_ID() + " - " + Users.getUser_Name());
      }

      System.out.println(selectedAppointment.getUser_ID()); //TODO: Fix, only shows user_ID not user name

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
