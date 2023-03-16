package database.appointmentscheduler;

import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import models.appointmentscheduler.Users;

public class UserSQL {
  /**
   * Gets all users from database
   * @return allUsers
   * @throws SQLException
   */
  public static ObservableList<Users> getAllUsers() throws SQLException {
    ObservableList<Users> allUsers = FXCollections.observableArrayList();
    String query = "SELECT * FROM users";
    try (PreparedStatement statement = Query.createQuery(query);) {
      try (ResultSet result = statement.executeQuery()) {
        while (result.next()) {
          int resUserId = result.getInt("User_ID");
          String resUserName = result.getString("User_Name");
          Users queryResults = new Users(resUserId, resUserName);
          allUsers.add(queryResults);
        }
        return allUsers;
      }
    }
  }

  /**
   * Logs user into application. Checks if user exists in database and if password matches.
   * @param userName Logged in userName
   * @param password
   * @return loggedUser
   * @throws SQLException
   */

  public static Users loggedUser(String userName, String password)
    throws SQLException {
    String query =
      "SELECT * FROM users WHERE User_Name = ? AND BINARY Password = ? LIMIT 1";
    try (PreparedStatement statement = Query.createQuery(query)) {
      statement.setString(1, userName);
      statement.setString(2, password);
      try (ResultSet result = statement.executeQuery()) {
        if (result.next()) {
          int resUserId = result.getInt("User_ID");
          String resUserName = result.getString("User_Name");
          Users loggedUser = new Users(resUserId, resUserName);
          Users.setLoggedUser(loggedUser);
          return loggedUser;
        } else {
          //Test
          throw new SQLException("Invalid username or password");
        }
      }
    } catch (SQLException e) {
      //Test
      System.out.println("Error: " + e.getMessage());
      System.out.println("Error: " + e.getSQLState());
    }
    return null;
  }

  /**
   * Gets current logged in user
   * @return Users.getUser_Name()
   */

  public static String getCurrentUser() {
    return Users.getUser_Name();
  }

}
