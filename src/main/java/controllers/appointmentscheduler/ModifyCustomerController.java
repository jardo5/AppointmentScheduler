package controllers.appointmentscheduler;

import database.appointmentscheduler.CountriesSQL;
import database.appointmentscheduler.DivisionSQL;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
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

  public void custSaveButtonClick(ActionEvent actionEvent) {}

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
    try {
      custCountryBox.getItems().addAll(CountriesSQL.getAllCountriesName());
      custDivisionBox.getItems().addAll(DivisionSQL.getAllDivisionNames());
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
  }
}
