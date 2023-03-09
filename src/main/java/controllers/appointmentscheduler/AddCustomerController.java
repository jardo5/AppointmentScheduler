package controllers.appointmentscheduler;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AddCustomerController {
    public TextField custID;
    public TextField custName;
    public TextField custAddress;
    public TextField custZipCode;
    public TextField custPhoneNumber;
    public ComboBox custCountryBox;
    public ComboBox custDivisionBox;

    public void appSaveButtonClick(ActionEvent actionEvent) {
    }

    public void appCancelButtonClick(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cancel");
        alert.setHeaderText("Are you sure you want to cancel?");
        alert.setContentText("Click OK to cancel. All progress will be lost.");
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
    }
}