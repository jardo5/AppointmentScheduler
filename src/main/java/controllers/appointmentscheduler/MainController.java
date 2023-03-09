package controllers.appointmentscheduler;

import jarod.appointmentscheduler.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public Button logoutButton;
    public ToggleGroup appointmentGroup;

    /* Appointment Table */
    public TableView AppointmentsTable;
    public TableColumn appID;
    public TableColumn appTitle;
    public TableColumn appDescription;
    public TableColumn appLocation;
    public TableColumn appContact;
    public TableColumn appType;
    public TableColumn appStartDateTime;
    public TableColumn appEndDateTime;
    public TableColumn appCustomerID;
    public TableColumn appUserID;

    public Button addAppButton;
    public Button modifyAppButton;

    /* Customer Table */
    public TableView CustomersTable;
    public TableColumn custID;
    public TableColumn custName;
    public TableColumn custAddress;
    public TableColumn custZipCode;
    public TableColumn custPhoneNumber;
    public TableColumn custDivision;
    public TableColumn custAddDate;
    public TableColumn custAddedBy;
    public TableColumn custLastUpdate;
    public TableColumn custLastUpdatedBy;

    public Button addCustButton;
    public Button modifyCustButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void reportButtonClick(ActionEvent actionEvent){
        try {
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/jarod/appointmentscheduler/report.fxml"));
            Scene scene = new Scene(loader.load(), 600, 300);
            stage.setTitle("Reports");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void logoutButtonClick(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("Are you sure you want to logout?");
        alert.setContentText("Click OK to logout.");
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Logging Out");
                MainApplication loginScene = new MainApplication();
                try {
                    loginScene.start(new Stage());
                    Stage stage = (Stage) logoutButton.getScene().getWindow();
                    stage.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void addAppButtonClick(ActionEvent actionEvent){
        try {
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/jarod/appointmentscheduler/addAppointment.fxml"));
            Scene scene = new Scene(loader.load(), 600, 300);
            stage.setTitle("Add Appointment");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void appModifyButtonClick(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/jarod/appointmentscheduler/modifyAppointment.fxml"));
            Scene scene = new Scene(loader.load(), 600, 300);
            stage.setTitle("Modify Appointment");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addCustButtonClick(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/jarod/appointmentscheduler/addCustomer.fxml"));
            Scene scene = new Scene(loader.load(), 600, 375);
            stage.setTitle("Add Customer");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void modifyCustButtonClick(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/jarod/appointmentscheduler/modifyCustomer.fxml"));
            Scene scene = new Scene(loader.load(), 600, 375);
            stage.setTitle("Modify Customer");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}