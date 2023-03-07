module com.example.appointmentscheduler {
    requires javafx.controls;
    requires javafx.fxml;


    opens jarod.appointmentscheduler to javafx.fxml;
    exports jarod.appointmentscheduler;
    exports controllers.appointmentscheduler;
    opens controllers.appointmentscheduler to javafx.fxml;
}