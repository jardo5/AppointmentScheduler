module com.example.appointmentscheduler {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens jarod.appointmentscheduler to javafx.fxml;
    opens database.appointmentscheduler to javafx.fxml, javafx.base;
    opens models.appointmentscheduler to javafx.base;
    exports models.appointmentscheduler;
    exports database.appointmentscheduler;
    exports jarod.appointmentscheduler;
    exports controllers.appointmentscheduler;
    opens controllers.appointmentscheduler to javafx.fxml;
}