package controllers.appointmentscheduler;

import database.appointmentscheduler.CountriesSQL;
import database.appointmentscheduler.CustomersSQL;
import database.appointmentscheduler.DivisionSQL;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.appointmentscheduler.Divisions;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * handles the logic of the Add Customer screen
 */

public class AddCustomerController implements Initializable {

  public TextField custID;
  public TextField custName;
  public TextField custAddress;
  public TextField custZipCode;
  public TextField custPhoneNumber;
  public ComboBox custCountryBox;
  public ComboBox custDivisionBox;

    /**
     * Saves the customer to the database
     * @param actionEvent
     */

  public void appSaveButtonClick(ActionEvent actionEvent) {
    try {
        int custID = Integer.parseInt(this.custID.getText());
        String custName = this.custName.getText();
        String custAddress = this.custAddress.getText();
        String custZipCode = this.custZipCode.getText();
        String custPhoneNumber = this.custPhoneNumber.getText();
        String custCountry = this.custCountryBox.getSelectionModel().getSelectedItem().toString();
        String custDivision = this.custDivisionBox.getSelectionModel().getSelectedItem().toString();

        if (custID == 0 || custName.isEmpty() || custAddress.isEmpty() || custZipCode.isEmpty() || custPhoneNumber.isEmpty() || custCountry.isEmpty() || custDivision.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please fill out all fields.");
            alert.showAndWait();
            return;
        }
        if (custPhoneNumber.length() != 10) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please enter a valid phone number.");
            alert.showAndWait();
            return;
        }
        if (custZipCode.length() != 5) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please enter a valid zip code.");
            alert.showAndWait();
            return;
        }
          //if  everything is valid, add the customer to the database
        CustomersSQL.addCustomer(custID, custName, custAddress, custZipCode, custPhoneNumber, custDivision);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Customer added successfully.");
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
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
        });




    } catch (Exception e) {
      e.printStackTrace();
    }
  }

    /**
     * Cancels the add customer screen and returns to the main screen
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
     * Populates the combo boxes with the countries and divisions and sets the customer ID
     * @param url
     * @param resourceBundle
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            custID.setText(String.valueOf(CustomersSQL.autoCustomerID()));
            custCountryBox.setItems(CountriesSQL.getAllCountriesName());
            custDivisionBox.setItems(DivisionSQL.getAllDivisionNames());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
