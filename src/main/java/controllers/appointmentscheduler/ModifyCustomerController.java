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
import javafx.util.Pair;
import models.appointmentscheduler.Customers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * handles the logic of the Modify Customer screen
 */

public class ModifyCustomerController implements Initializable {

  /**
   * Customer ID field
   */

  public TextField custID;

    /**
     * Customer Name field
     */
  public TextField custName;

    /**
     * Customer Address field
     */
  public TextField custAddress;

    /**
     * Customer Zip Code field
     */
  public TextField custZipCode;

    /**
     * Customer Phone Number field
     */
  public TextField custPhoneNumber;

    /**
     * Customer Country Combo Box
     */
  public ComboBox custCountryBox;

    /**
     * Customer Division Combo Box
     */
  public ComboBox custDivisionBox;


  /**
   * Allows the passing of the selected customer from the main screen to this screen
   */
  public static Customers modifyCustomer = null;

  /**
   * Allows the passing of the selected customer from the main screen to this screen
   * @param customers selected customer
   */
  public static void getModifyCustomer(Customers customers){
    modifyCustomer = customers;
  }

  /**
   * Saves the modified customer to the database
   * Exits back to the main screen
   * @param actionEvent save button click
   * @throws Exception if the customer cannot be saved
   */

  public void custSaveButtonClick(ActionEvent actionEvent) throws Exception {
    int custID = modifyCustomer.getCustomer_ID();
    String custName = this.custName.getText();
    String custAddress = this.custAddress.getText();
    String custZipCode = this.custZipCode.getText();
    String custPhoneNumber = this.custPhoneNumber.getText();
    String custCountry = this.custCountryBox.getSelectionModel().getSelectedItem().toString();

    int custDivision = DivisionSQL.getDivisionID(custDivisionBox.getSelectionModel().getSelectedItem().toString());

    System.out.println(custID + " " + custName + " " + custAddress + " " + custZipCode + " " + custPhoneNumber + " " + custCountry + " " + custDivision);

    if (custName.isEmpty() || custAddress.isEmpty() || custZipCode.isEmpty() || custPhoneNumber.isEmpty() || custCountry.isEmpty() || custDivision == 0){
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("Please fill out all fields.");
      alert.showAndWait();
      return;
    }

    CustomersSQL.updateCustomer(modifyCustomer.getCustomer_ID(), custName, custAddress, custZipCode, custPhoneNumber, custDivision);
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Success");
    alert.setHeaderText("Customer added successfully.");
    alert.showAndWait().ifPresent(rs -> {
      if (rs == ButtonType.OK) {
        try {
          Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/jarod/appointmentscheduler/main.fxml"));
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
     * Cancels the modification of the customer
     * Exits back to the main screen
     * @param actionEvent action event
     */

  public void custCancelButtonClick(ActionEvent actionEvent) {
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
   * Populates the fields and combo boxes with the selected customer's information
   * @param url url
   * @param resourceBundle  resource bundle
   */

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    custID.setText(String.valueOf(modifyCustomer.getCustomer_ID()));
    custName.setText(modifyCustomer.getCustomer_Name());
    custAddress.setText(modifyCustomer.getAddress());
    custZipCode.setText(modifyCustomer.getPostal_Code());
    custPhoneNumber.setText(modifyCustomer.getPhone());

    try {
      custCountryBox.getItems().addAll(CountriesSQL.getAllCountriesName());

      String divisionName = (String) DivisionSQL.getDivisionName(modifyCustomer.getDivision_ID());
      String countryName = (String) CountriesSQL.getCountryNameByDivision(divisionName);
      custCountryBox.setValue(countryName);

      int countryID = CountriesSQL.getCountryIDByDivision(divisionName);
      custDivisionBox.getItems().addAll(DivisionSQL.getDivisionNameByCountry(String.valueOf(countryID)));
      custDivisionBox.setValue(divisionName);


      /**
       * Lambda Expression 2
       * Makes code more readable and easier to understand. As well as more efficient.
       * custCountryBox ComboBox selection changes, updating custDivisionBox accordingly.
       *
       * Sets a listener on the custCountryBox ComboBox to update the custDivisionBox ComboBox.
       * Whenever a new country is selected, the divisions associated with the selected country
       * are fetched from the database and displayed in the custDivisionBox ComboBox.
       */

      custCountryBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue != null) {
          try {
            custDivisionBox.getItems().clear();
            int newCountryID = CountriesSQL.getCountryIDByDivision(newValue.toString());
            custDivisionBox.getItems().addAll(DivisionSQL.getDivisionNameByCountry(String.valueOf(newCountryID)));
          } catch (SQLException e) {
            e.printStackTrace();
          }
        }
      });


    } catch (SQLException e) {
      throw new RuntimeException(e);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }



}
