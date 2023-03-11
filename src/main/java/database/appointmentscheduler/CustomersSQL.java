package database.appointmentscheduler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.appointmentscheduler.Customers;

public class CustomersSQL {

  public static ObservableList<Customers> getAllCustomers()
    throws SQLException {
    ObservableList<Customers> customerList = FXCollections.observableArrayList();
    String sql = "SELECT * FROM customers";
    Connection conn = JDBC.connection;
    PreparedStatement statement = conn.prepareStatement(sql);
    ResultSet result = statement.executeQuery();
    while (result.next()) {
      Customers customer = new Customers(
        result.getInt("Customer_ID"),
        result.getString("Customer_Name"),
        result.getString("Address"),
        result.getString("Postal_Code"),
        result.getString("Phone"),
        result.getTimestamp("Create_Date").toLocalDateTime(),
        result.getString("Created_By"),
        result.getTimestamp("Last_Update").toLocalDateTime(),
        result.getString("Last_Updated_By"),
        result.getInt("Division_ID")
      );
      customerList.add(customer);
    }
    return customerList;
  }
}
