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

public class ModifyCustomerController implements Initializable {

  public TextField custID;
  public TextField custName;
  public TextField custAddress;
  public TextField custZipCode;
  public TextField custPhoneNumber;
  public ComboBox custCountryBox;
  public ComboBox custDivisionBox;

  public static Customers modifyCustomer = null;

  public static void getModifyCustomer(Customers customers){
    modifyCustomer = customers;
  }

  public void custSaveButtonClick(ActionEvent actionEvent) throws Exception {
    int custID = modifyCustomer.getCustomer_ID();
    String custName = this.custName.getText();
    String custAddress = this.custAddress.getText();
    String custZipCode = this.custZipCode.getText();
    String custPhoneNumber = this.custPhoneNumber.getText();
    String custCountry = this.custCountryBox.getSelectionModel().getSelectedItem().toString();

    int custDivision = DivisionSQL.getDivisionID(String.valueOf(custDivisionBox.getSelectionModel().getSelectedItem()));

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

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    custID.setText(String.valueOf(modifyCustomer.getCustomer_ID()));
    custName.setText(modifyCustomer.getCustomer_Name());
    custAddress.setText(modifyCustomer.getAddress());
    custZipCode.setText(modifyCustomer.getPostal_Code());
    custPhoneNumber.setText(modifyCustomer.getPhone());
    custCountryBox.getSelectionModel();
    custDivisionBox.getSelectionModel();
    try {
      custCountryBox.getItems().addAll(CountriesSQL.getAllCountriesName());

      // set the selected country for the customer
      String divisionName = (String) DivisionSQL.getDivisionName(modifyCustomer.getDivision_ID());
      custCountryBox.setValue(CountriesSQL.getCountryNameByDivision(divisionName));

      // get the country ID based on the selected country name
      int countryID = CountriesSQL.getCountryIDByDivision(divisionName);

      // populate the division box based on the selected country
      custDivisionBox.getItems().addAll(DivisionSQL.getDivisionNameByCountry(String.valueOf(countryID)));
      custDivisionBox.setValue(DivisionSQL.getDivisionName(modifyCustomer.getDivision_ID()));


    } catch (SQLException e) {
      throw new RuntimeException(e);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
  }
}
