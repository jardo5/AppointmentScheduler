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

public class ReportController implements Initializable {

    public ComboBox contactComboBox;

    public TableView scheduleTable;
    public TableColumn scheduleAppID;
    public TableColumn scheduleTitle;
    public TableColumn scheduleType;
    public TableColumn scheduleDescription;
    public TableColumn scheduleStart;
    public TableColumn scheduleEnd;
    public TableColumn scheduleCustomerID;

    public TableView byTypeTable;
    public TableColumn totalType;
    public TableColumn totalMonth;
    public TableColumn totalAppointmentCount;

    public TableView byCountryTable;
    public TableColumn totalCountry;
    public TableColumn totalCustomerCount;

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {


            contactComboBox.getItems().addAll(ContactsSQL.getAllContacts());

            contactComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                System.out.println("Selected item: " + newValue);
                if (newValue != null) {
                    Contacts selectedContact = (Contacts) newValue;
                    int contactId = selectedContact.getContact_ID();
                    try {
                        scheduleTable.setItems(Reports.getAppointmentsCustomer(contactId));
                        scheduleAppID.setCellValueFactory(new PropertyValueFactory<>("appointment_ID"));
                        scheduleTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
                        scheduleType.setCellValueFactory(new PropertyValueFactory<>("type"));
                        scheduleDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
                        scheduleStart.setCellValueFactory(new PropertyValueFactory<>("start"));
                        scheduleEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
                        scheduleCustomerID.setCellValueFactory(new PropertyValueFactory<>("customer_ID"));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
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
