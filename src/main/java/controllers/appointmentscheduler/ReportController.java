package controllers.appointmentscheduler;

import database.appointmentscheduler.ContactsSQL;
import database.appointmentscheduler.Reports;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.appointmentscheduler.Contacts;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * handles the logic of the Report screen
 */

public class ReportController implements Initializable {

    /**
     * Contacts Combo Box
     */
    public ComboBox contactComboBox;

    /**
     * Schedule Table
     */

    public TableView scheduleTable;

    /**
     * Schedule Table Appointment ID
     */

    public TableColumn scheduleAppID;

    /**
     * Schedule Table Title
     */
    public TableColumn scheduleTitle;

    /**
     * Schedule Table Type
     */
    public TableColumn scheduleType;

    /**
     * Schedule Table Description
     */
    public TableColumn scheduleDescription;

    /**
     * Schedule Table Start
     */
    public TableColumn scheduleStart;

    /**
     * Schedule Table End
     */
    public TableColumn scheduleEnd;

    /**
     * Schedule Table Customer ID
     */
    public TableColumn scheduleCustomerID;


    /**
     * Appointment Type/Month Table
     */
    public TableView byTypeTable;

    /**
     * Appointment Type Column
     */
    public TableColumn totalType;

    /**
     * Appointment Month Column
     */
    public TableColumn totalMonth;

    /**
     * Appointment Total Count Column
     */
    public TableColumn totalAppointmentCount;

    /**
     * Customer by Country Table
     */
    public TableView byCountryTable;

    /**
     * Countries Column
     */
    public TableColumn totalCountry;

    /**
     * Total Customers Column
     */
    public TableColumn totalCustomerCount;

    /**
     * OnClick method for the exit button
     * @param actionEvent onClick event
     */

    public void exitButtonClick(ActionEvent actionEvent){
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
     * Initializes and sets the values for the tables
     * @param url url
     * @param resourceBundle resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {


            contactComboBox.setItems(ContactsSQL.getAllContacts());

            contactComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                System.out.println("Selected item: " + newValue);
                if (newValue != null) {
                    Contacts selectedContact = (Contacts) newValue;
                    int contactId = selectedContact.getContact_ID();
                    try {
                        scheduleTable.setItems(Reports.getAppointmentsByContact(contactId));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    scheduleAppID.setCellValueFactory(new PropertyValueFactory<>("appointment_ID"));
                    scheduleTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
                    scheduleType.setCellValueFactory(new PropertyValueFactory<>("type"));
                    scheduleDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
                    scheduleStart.setCellValueFactory(new PropertyValueFactory<>("start"));
                    scheduleEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
                    scheduleCustomerID.setCellValueFactory(new PropertyValueFactory<>("customer_ID"));
                }
            });

            byTypeTable.setItems(Reports.getAppTypeByMonth());
            totalMonth.setCellValueFactory(new PropertyValueFactory<>("month"));
            totalType.setCellValueFactory(new PropertyValueFactory<>("type"));
            totalAppointmentCount.setCellValueFactory(new PropertyValueFactory<>("count"));

            byCountryTable.setItems(Reports.getCustomerCountByCountry());
            totalCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
            totalCustomerCount.setCellValueFactory(new PropertyValueFactory<>("count"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
