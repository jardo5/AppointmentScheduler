package database.appointmentscheduler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import models.appointmentscheduler.Appointments;
import java.sql.*;
import java.time.LocalDateTime;

public class AppSQL {

    public static ObservableList<Appointments> getAllAppointments() throws SQLException {
        Connection conn = JDBC.connection;
        ObservableList<Appointments> appointmentList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM appointments";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            Appointments appointment = new Appointments(
                    result.getInt("Appointment_ID"),
                    result.getString("Title"),
                    result.getString("Description"),
                    result.getString("Location"),
                    result.getString("Type"),
                    result.getTimestamp("Start").toLocalDateTime(),
                    result.getTimestamp("End").toLocalDateTime(),
                    result.getTimestamp("Create_Date").toLocalDateTime(),
                    result.getString("Created_By"),
                    result.getTimestamp("Last_Update").toLocalDateTime(),
                    result.getString("Last_Updated_By"),
                    result.getInt("Customer_ID"),
                    result.getInt("User_ID"),
                    result.getInt("Contact_ID")
            );
            appointmentList.add(appointment);
        }

        if (result == null) {
            throw new SQLException("Query did not return any results.");
        }
        return appointmentList;
    }

    public static ObservableList<Appointments> getWeeklyAppointments() throws SQLException {
        Connection conn = JDBC.connection;
        ObservableList<Appointments> appointmentList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM appointments WHERE WEEKOFYEAR(Start) = WEEKOFYEAR(NOW())";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            Appointments appointment = new Appointments(
                    result.getInt("Appointment_ID"),
                    result.getString("Title"),
                    result.getString("Description"),
                    result.getString("Location"),
                    result.getString("Type"),
                    result.getTimestamp("Start").toLocalDateTime(),
                    result.getTimestamp("End").toLocalDateTime(),
                    result.getTimestamp("Create_Date").toLocalDateTime(),
                    result.getString("Created_By"),
                    result.getTimestamp("Last_Update").toLocalDateTime(),
                    result.getString("Last_Updated_By"),
                    result.getInt("Customer_ID"),
                    result.getInt("User_ID"),
                    result.getInt("Contact_ID")
            );
            appointmentList.add(appointment);
        }

        if (result == null) {
            throw new SQLException("Query did not return any results.");
        }
        return appointmentList;
    }

    public static ObservableList<Appointments> getMonthlyAppointments() throws SQLException {
        Connection conn = JDBC.connection;
        ObservableList<Appointments> appointmentList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM appointments WHERE MONTH(Start) = MONTH(NOW())";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            Appointments appointment = new Appointments(
                    result.getInt("Appointment_ID"),
                    result.getString("Title"),
                    result.getString("Description"),
                    result.getString("Location"),
                    result.getString("Type"),
                    result.getTimestamp("Start").toLocalDateTime(),
                    result.getTimestamp("End").toLocalDateTime(),
                    result.getTimestamp("Create_Date").toLocalDateTime(),
                    result.getString("Created_By"),
                    result.getTimestamp("Last_Update").toLocalDateTime(),
                    result.getString("Last_Updated_By"),
                    result.getInt("Customer_ID"),
                    result.getInt("User_ID"),
                    result.getInt("Contact_ID")
            );
            appointmentList.add(appointment);
        }

        if (result == null) {
            throw new SQLException("Query did not return any results.");
        }
        return appointmentList;
    }

}



