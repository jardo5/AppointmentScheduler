package database.appointmentscheduler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.appointmentscheduler.Appointments;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class AppSQL {


    public static ObservableList<Appointments> getAllAppointments(String interval) throws SQLException {
        ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();
        String sqlQuery;

        switch(interval) {
            case "week": sqlQuery = "SELECT *" + "FROM appointments" + "WHERE Start BETWEEN NOW() AND NOW() + INTERVAL 1 WEEK;";
                break;
                case "month": sqlQuery = "SELECT *" + "FROM appointments" + "WHERE Start BETWEEN NOW() AND NOW() + INTERVAL 1 MONTH;";
                break;
            default: sqlQuery = "SELECT *" + "FROM appointments;";
                break;
        }

        return allAppointments;
    }
}
