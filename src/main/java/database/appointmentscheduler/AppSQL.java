package database.appointmentscheduler;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import models.appointmentscheduler.Appointments;

public class AppSQL {

  public static ObservableList<Appointments> getAllAppointments()
    throws SQLException {
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

  public static ObservableList<Appointments> getWeeklyAppointments()
    throws SQLException {
    Connection conn = JDBC.connection;
    ObservableList<Appointments> appointmentList = FXCollections.observableArrayList();
    String sql =
      "SELECT * FROM appointments WHERE WEEKOFYEAR(Start) = WEEKOFYEAR(NOW())";
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

  public static ObservableList<Appointments> getMonthlyAppointments()
    throws SQLException {
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

  public static int autoAppointmentID() throws SQLException {
    Connection conn = JDBC.connection;
    String sql = "SELECT MAX(Appointment_ID) FROM appointments";
    PreparedStatement statement = conn.prepareStatement(sql);
    ResultSet result = statement.executeQuery();
    int appointmentID = 0;
    while (result.next()) {
      appointmentID = result.getInt("MAX(Appointment_ID)");
    }
    return ++appointmentID;
  }

  public static LocalDateTime localToEST(LocalDateTime localDateTime) {
    ZonedDateTime localZDT = localDateTime.atZone(
      ZoneId.of(ZoneId.systemDefault().toString())
    );
    ZonedDateTime estZDT = localZDT.withZoneSameInstant(
      ZoneId.of("America/New_York")
    );
    LocalDateTime estDateTime = estZDT.toLocalDateTime();

    return estDateTime;
  }

  public static ObservableList<Appointments> getAppointmentsContacts(
    int contactId
  ) throws SQLException {
    ObservableList<Appointments> appointmentList = FXCollections.observableArrayList();
    Connection conn = JDBC.connection;
    String sql = "SELECT * FROM appointments WHERE Contact_ID = ?";
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

  public static void addAppointment(
    int tempID,
    String tempTitle,
    String tempDescription,
    String tempLocation,
    int tempContactID,
    String tempType,
    LocalDateTime tempStart,
    LocalDateTime tempEnd,
    int tempCustomerID,
    int tempUserID
  ) {
    try {
      Connection conn = JDBC.connection;
      String sql =
        "INSERT INTO appointments (Appointment_ID, Title, Description, Location, Contact_ID, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, NOW(), ?, NOW(), ?, ?, ?)";
      PreparedStatement statement = conn.prepareStatement(sql);
      statement.setInt(1, tempID);
      statement.setString(2, tempTitle);
      statement.setString(3, tempDescription);
      statement.setString(4, tempLocation);
      statement.setInt(5, tempContactID);
      statement.setString(6, tempType);
      statement.setTimestamp(7, Timestamp.valueOf(tempStart));
      statement.setTimestamp(8, Timestamp.valueOf(tempEnd));
      statement.setString(9, UserSQL.getCurrentUser());
      statement.setString(10, UserSQL.getCurrentUser());
      statement.setInt(11, tempCustomerID);
      statement.setInt(12, tempUserID);
      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static void updateAppointment(
    int tempID,
    String tempTitle,
    String tempDescription,
    String tempLocation,
    int tempContactID,
    String tempType,
    LocalDateTime tempStart,
    LocalDateTime tempEnd,
    int tempCustomerID,
    int tempUserID
  ) {
    try {
      Connection conn = JDBC.connection;
      String sql =
        "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Contact_ID = ?, Type = ?, Start = ?, End = ?, Last_Update = NOW(), Last_Updated_By = ?, Customer_ID = ?, User_ID = ? WHERE Appointment_ID = ?";
      PreparedStatement statement = conn.prepareStatement(sql);
      statement.setString(1, tempTitle);
      statement.setString(2, tempDescription);
      statement.setString(3, tempLocation);
      statement.setInt(4, tempContactID);
      statement.setString(5, tempType);
      statement.setTimestamp(6, Timestamp.valueOf(tempStart));
      statement.setTimestamp(7, Timestamp.valueOf(tempEnd));
      statement.setString(8, (UserSQL.getCurrentUser()));
      statement.setInt(9, tempCustomerID);
      statement.setInt(10, tempUserID);
      statement.setInt(11, tempID);
      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

    public static void deleteAppointment(int tempID) {
        try {
        Connection conn = JDBC.connection;
        String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, tempID);
        statement.executeUpdate();
        } catch (SQLException e) {
        e.printStackTrace();
        }
    }
}
