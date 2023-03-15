package database.appointmentscheduler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.appointmentscheduler.Appointments;
import models.appointmentscheduler.ReportCountry;
import models.appointmentscheduler.ReportType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Reports {

    public static ObservableList<ReportType> getAppTypeByMonth() throws SQLException {
        ObservableList<ReportType> reportList = FXCollections.observableArrayList();
        String sql = "SELECT DATE_FORMAT(Start, '%Y-%m') AS Month, Type, COUNT(*) AS Count " + "FROM appointments " + "GROUP BY Month, Type " + "ORDER BY Month, Type";
        Connection conn = JDBC.connection;
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            ReportType report = new ReportType(
                    result.getString("Month"),
                    result.getString("Type"),
                    result.getInt("Count")
            );
            reportList.add(report);
        }
        return reportList;
    }

    public static ObservableList<ReportCountry> getCustomerCountByCountry() throws SQLException{
        ObservableList<ReportCountry> reportList = FXCollections.observableArrayList();
        String sql = "SELECT countries.Country, COUNT(*) as Count " + "FROM customers " + "INNER JOIN first_level_divisions ON customers.Division_ID = first_level_divisions.Division_ID " + "INNER JOIN countries ON first_level_divisions.Country_ID = countries.Country_ID " + "GROUP BY countries.Country";
        Connection conn = JDBC.connection;
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            ReportCountry report = new ReportCountry(
                    result.getString("Country"),
                    result.getInt("Count")
                                    );
            reportList.add(report);
        }
        return reportList;
    }

    public static ObservableList<Appointments> getAppointmentsByContact(int contactId) throws SQLException {
        ObservableList<Appointments> appointmentList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM appointments WHERE Contact_ID = ?";
        Connection conn = JDBC.connection;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, contactId);
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

        return appointmentList;
    }

}
