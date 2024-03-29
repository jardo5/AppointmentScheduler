package database.appointmentscheduler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.appointmentscheduler.Contacts;

/**
 * holds the Contact queries
 */

public class ContactsSQL {

    /**
     * Get all contacts
     * @return contactList
     * @throws SQLException
     */

  public static ObservableList<Contacts> getAllContacts() throws SQLException {
    ObservableList<Contacts> contactList = FXCollections.observableArrayList();
    String sql = "SELECT * FROM contacts";
    Connection conn = JDBC.connection;
    PreparedStatement statement = conn.prepareStatement(sql);
    ResultSet result = statement.executeQuery();
    while (result.next()) {
      Contacts contact = new Contacts(
        result.getInt("Contact_ID"),
        result.getString("Contact_Name"),
        result.getString("Email")
      );
      contactList.add(contact);
    }
    return contactList;
  }

    /**
     * Get contact by ID
     * @return contact
     * @throws SQLException
     */

  public static Contacts getContactName(int contactID) throws SQLException {
    String sql = "SELECT * FROM contacts WHERE Contact_ID = ?";
    Connection conn = JDBC.connection;
    PreparedStatement statement = conn.prepareStatement(sql);
    statement.setInt(1, contactID);
    ResultSet result = statement.executeQuery();
    if (result.next()) {
      Contacts contact = new Contacts(
        result.getInt("Contact_ID"),
        result.getString("Contact_Name"),
        result.getString("Email")
      );
      return contact;
    }
    return null;
  }

}
