package database.appointmentscheduler;

import java.sql.*;
import java.time.LocalDateTime;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.appointmentscheduler.Customers;

/**
 * Customers SQL for Database
 */

public class CustomersSQL {

    /**
     * Get all customers
     * @return ObservableList of Customers
     * @throws SQLException
     */

  public static ObservableList<Customers> getAllCustomers() throws SQLException {
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

    /**
     * Auto generates customer ID
     * @return customer
     * @throws SQLException
     */

  public static int autoCustomerID() throws SQLException {
    Connection conn = JDBC.connection;
    String sql = "SELECT MAX(Customer_ID) FROM customers";
    PreparedStatement statement = conn.prepareStatement(sql);
    ResultSet result = statement.executeQuery();
    int customerID = 0;
    while (result.next()) {
      customerID = result.getInt("MAX(Customer_ID)");
    }
    return ++customerID;
  }

    /**
     * Add customer
     * @throws SQLException
     */

  public static void addCustomer(int custID, String custName, String custAddress, String custZipCode, String custPhoneNumber,String custDivision) {
        try {
            Connection conn = JDBC.connection;
            String sql = "INSERT INTO customers VALUES (?, ?, ?, ?, ?, NOW(), ?, NOW(), ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, custID);
            statement.setString(2, custName);
            statement.setString(3, custAddress);
            statement.setString(4, custZipCode);
            statement.setString(5, custPhoneNumber);
            statement.setString(6, UserSQL.getCurrentUser());
            statement.setString(7, UserSQL.getCurrentUser());
            Integer divisionID = DivisionSQL.getDivisionID(custDivision);
            if (divisionID == null) {
                throw new Exception("Division ID not found for division: " + custDivision);
            }
            statement.setInt(8, divisionID);
            statement.executeUpdate();
            System.out.println("Customer added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding customer: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Update customer
     * @throws SQLException
     */

    public static void updateCustomer(int custID, String custName, String custAddress, String custZipCode, String custPhoneNumber, int custDivision) throws Exception {
        Connection conn = JDBC.connection;
        String sql = "Update Customers set Customer_Name=?, Address=?, Postal_Code=?, Phone=?, Last_Update=?, Last_Updated_By=?, Division_ID=? WHERE Customer_ID=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, custName);
        statement.setString(2, custAddress);
        statement.setString(3, custZipCode);
        statement.setString(4, custPhoneNumber);
        statement.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
        statement.setString(6, UserSQL.getCurrentUser());
        statement.setInt(7, custDivision);
        statement.setInt(8, custID);
        statement.executeUpdate();



    }

    /**
     * Delete customer
     * @throws SQLException
     */

    public static void deleteCustomer(int customerId) {
        try {
            Connection conn = JDBC.connection;
            String sql = "DELETE FROM customers WHERE Customer_ID = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, customerId);
            statement.executeUpdate();
            System.out.println("Customer deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting customer: " + e.getMessage());
        }
    }
}
