package controllers.appointmentscheduler;

import database.appointmentscheduler.AppSQL;
import database.appointmentscheduler.CustomersSQL;
import jarod.appointmentscheduler.MainApplication;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.appointmentscheduler.Appointments;
import models.appointmentscheduler.Customers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import static controllers.appointmentscheduler.ModifyCustomerController.modifyCustomer;
import static database.appointmentscheduler.AppSQL.getAllAppointments;
import static database.appointmentscheduler.CustomersSQL.getAllCustomers;

public class MainController implements Initializable {

  public Button logoutButton;

  public ToggleGroup appointmentGroup;
  public RadioButton radioAll;

  /* Appointment Table */
  public TableView <Appointments> AppointmentsTable;
  public TableColumn Appointment_ID;
  public TableColumn Title;
  public TableColumn Description;
  public TableColumn Location;
  public TableColumn Type;
  public TableColumn Start;
  public TableColumn End;
  public TableColumn Create_Date;
  public TableColumn Created_By;
  public TableColumn Last_Update;
  public TableColumn Last_Updated_By;
  public TableColumn Customer_ID;
  public TableColumn User_ID;
  public TableColumn Contact_ID;

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


  public void radioWeekClick() throws SQLException {
    AppointmentsTable.setItems(AppSQL.getWeeklyAppointments());

    Appointment_ID.setCellValueFactory(
      new PropertyValueFactory<>("Appointment_ID")
    );
    Title.setCellValueFactory(new PropertyValueFactory<>("Title"));
    Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
    Location.setCellValueFactory(new PropertyValueFactory<>("Location"));
    Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
    Start.setCellValueFactory(new PropertyValueFactory<>("Start"));
    End.setCellValueFactory(new PropertyValueFactory<>("End"));
    Create_Date.setCellValueFactory(new PropertyValueFactory<>("Create_Date"));
    Created_By.setCellValueFactory(new PropertyValueFactory<>("Created_By"));
    Last_Update.setCellValueFactory(new PropertyValueFactory<>("Last_Update"));
    Last_Updated_By.setCellValueFactory(
      new PropertyValueFactory<>("Updated_By")
    );
    Customer_ID.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
    User_ID.setCellValueFactory(new PropertyValueFactory<>("User_ID"));
    Contact_ID.setCellValueFactory(new PropertyValueFactory<>("Contact_ID"));
  }

  public void radioMonthClick() throws SQLException {
    AppointmentsTable.setItems(AppSQL.getMonthlyAppointments());

    Appointment_ID.setCellValueFactory(
      new PropertyValueFactory<>("Appointment_ID")
    );
    Title.setCellValueFactory(new PropertyValueFactory<>("Title"));
    Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
    Location.setCellValueFactory(new PropertyValueFactory<>("Location"));
    Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
    Start.setCellValueFactory(new PropertyValueFactory<>("Start"));
    End.setCellValueFactory(new PropertyValueFactory<>("End"));
    Create_Date.setCellValueFactory(new PropertyValueFactory<>("Create_Date"));
    Created_By.setCellValueFactory(new PropertyValueFactory<>("Created_By"));
    Last_Update.setCellValueFactory(new PropertyValueFactory<>("Last_Update"));
    Last_Updated_By.setCellValueFactory(
      new PropertyValueFactory<>("Updated_By")
    );
    Customer_ID.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
    User_ID.setCellValueFactory(new PropertyValueFactory<>("User_ID"));
    Contact_ID.setCellValueFactory(new PropertyValueFactory<>("Contact_ID"));
  }

  public void radioAllClick(ActionEvent actionEvent) throws Exception {
    AppointmentsTable.setItems(getAllAppointments());

    Appointment_ID.setCellValueFactory(
      new PropertyValueFactory<>("Appointment_ID")
    );
    Title.setCellValueFactory(new PropertyValueFactory<>("Title"));
    Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
    Location.setCellValueFactory(new PropertyValueFactory<>("Location"));
    Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
    Start.setCellValueFactory(new PropertyValueFactory<>("Start"));
    End.setCellValueFactory(new PropertyValueFactory<>("End"));
    Create_Date.setCellValueFactory(new PropertyValueFactory<>("Create_Date"));
    Created_By.setCellValueFactory(new PropertyValueFactory<>("Created_By"));
    Last_Update.setCellValueFactory(new PropertyValueFactory<>("Last_Update"));
    Last_Updated_By.setCellValueFactory(
      new PropertyValueFactory<>("Updated_By")
    );
    Customer_ID.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
    User_ID.setCellValueFactory(new PropertyValueFactory<>("User_ID"));
    Contact_ID.setCellValueFactory(new PropertyValueFactory<>("Contact_ID"));
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    try {
      AppointmentsTable.setItems(getAllAppointments());

      Appointment_ID.setCellValueFactory(
        new PropertyValueFactory<>("Appointment_ID")
      );
      Title.setCellValueFactory(new PropertyValueFactory<>("Title"));
      Description.setCellValueFactory(
        new PropertyValueFactory<>("Description")
      );
      Location.setCellValueFactory(new PropertyValueFactory<>("Location"));
      Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
      Start.setCellValueFactory(new PropertyValueFactory<>("Start"));
      End.setCellValueFactory(new PropertyValueFactory<>("End"));
      Create_Date.setCellValueFactory(
        new PropertyValueFactory<>("Create_Date")
      );
      Created_By.setCellValueFactory(new PropertyValueFactory<>("Created_By"));
      Last_Update.setCellValueFactory(
        new PropertyValueFactory<>("Last_Update")
      );
      Last_Updated_By.setCellValueFactory(
        new PropertyValueFactory<>("Updated_By")
      );
      Customer_ID.setCellValueFactory(
        new PropertyValueFactory<>("Customer_ID")
      );
      User_ID.setCellValueFactory(new PropertyValueFactory<>("User_ID"));
      Contact_ID.setCellValueFactory(new PropertyValueFactory<>("Contact_ID"));

      CustomersTable.setItems(getAllCustomers());

      custID.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
      custName.setCellValueFactory(new PropertyValueFactory<>("Customer_Name"));
      custAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
      custZipCode.setCellValueFactory(
        new PropertyValueFactory<>("Postal_Code")
      );
      custPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("Phone"));
      custDivision.setCellValueFactory(new PropertyValueFactory<>("Division"));
      custAddDate.setCellValueFactory(
        new PropertyValueFactory<>("Create_Date")
      );
      custAddedBy.setCellValueFactory(new PropertyValueFactory<>("Created_By"));
      custLastUpdate.setCellValueFactory(
        new PropertyValueFactory<>("Last_Update")
      );
      custLastUpdatedBy.setCellValueFactory(
        new PropertyValueFactory<>("Last_Updated_By")
      );
      custDivision.setCellValueFactory(
        new PropertyValueFactory<>("Division_ID")
      );
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void reportButtonClick(ActionEvent actionEvent) {
    try {
      Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene()
        .getWindow();
      FXMLLoader loader = new FXMLLoader(
        getClass().getResource("/jarod/appointmentscheduler/report.fxml")
      );
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
    alert
      .showAndWait()
      .ifPresent(rs -> {
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

  public void addAppButtonClick(ActionEvent actionEvent) {
    try {
      Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene()
        .getWindow();
      FXMLLoader loader = new FXMLLoader(
        getClass()
          .getResource("/jarod/appointmentscheduler/addAppointment.fxml")
      );
      Scene scene = new Scene(loader.load(), 600, 310);
      stage.setTitle("Add Appointment");
      stage.setScene(scene);
      stage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void appModifyButtonClick(ActionEvent actionEvent) {
    Appointments selectedAppointment = AppointmentsTable.getSelectionModel().getSelectedItem();

    if (selectedAppointment == null) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("No Appointment Selected");
      alert.setContentText("Please select an appointment to modify.");
      alert.showAndWait();
    } else {
      try {
        ModifyAppointmentController.retrieveAppointments(selectedAppointment);

        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene()
          .getWindow();
        FXMLLoader loader = new FXMLLoader(
          getClass()
            .getResource("/jarod/appointmentscheduler/modifyAppointment.fxml")
        );
        Scene scene = new Scene(loader.load(), 600, 310);
        stage.setTitle("Modify Appointment");
        stage.setScene(scene);
        stage.show();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

  }

  public void addCustButtonClick(ActionEvent actionEvent) {
    try {
      Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene()
        .getWindow();
      FXMLLoader loader = new FXMLLoader(
        getClass().getResource("/jarod/appointmentscheduler/addCustomer.fxml")
      );
      Scene scene = new Scene(loader.load(), 600, 375);
      stage.setTitle("Add Customer");
      stage.setScene(scene);
      stage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void modifyCustButtonClick(ActionEvent actionEvent) {
    Customers modifyCustomer = (Customers) CustomersTable.getSelectionModel().getSelectedItem();
    ModifyCustomerController.getModifyCustomer(modifyCustomer);
    try {
      Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene()
        .getWindow();
      FXMLLoader loader = new FXMLLoader(
        getClass()
          .getResource("/jarod/appointmentscheduler/modifyCustomer.fxml")
      );
      Scene scene = new Scene(loader.load(), 600, 375);
      stage.setTitle("Modify Customer");
      stage.setScene(scene);
      stage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void onClickDeleteApp(ActionEvent actionEvent){
    Appointments selectedAppointment = AppointmentsTable.getSelectionModel().getSelectedItem();
    if (selectedAppointment != null) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Appointment");
        alert.setHeaderText("Are you sure you want to delete this appointment?");
        alert.setContentText("Click OK to delete.");
        alert.showAndWait().ifPresent(rs -> {
          if (rs == ButtonType.OK) {
            System.out.println("Deleting Appointment");
            AppSQL.deleteAppointment(selectedAppointment.getAppointment_ID());
            try {
              AppointmentsTable.setItems(getAllAppointments());
            } catch (SQLException e) {
              throw new RuntimeException(e);
            }
          }
        });
    } else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("No Appointment Selected");
        alert.setContentText("Please select an appointment to delete.");
        alert.showAndWait();
    }
  }

    public void onClickDeleteCust(ActionEvent actionEvent){
      Customers selectedCustomer = (Customers) CustomersTable.getSelectionModel().getSelectedItem();
      if (selectedCustomer != null) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Customer");
        alert.setHeaderText("Are you sure you want to delete this customer?");
        alert.setContentText("Click OK to delete.");
        alert.showAndWait().ifPresent(rs -> {
          if (rs == ButtonType.OK) {
            System.out.println("Deleting Customer");
            CustomersSQL.deleteCustomer(selectedCustomer.getCustomer_ID());
            try {
              CustomersTable.setItems(getAllCustomers());
            } catch (SQLException e) {
              throw new RuntimeException(e);
            }
          }
        });
      } else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("No Customer Selected");
        alert.setContentText("Please select a customer to delete.");
        alert.showAndWait();
    }
  }
}
