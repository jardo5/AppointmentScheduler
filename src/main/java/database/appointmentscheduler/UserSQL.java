package database.appointmentscheduler;

import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import models.appointmentscheduler.Users;

public class UserSQL {

  public static Users retrieveUsers(int userId) throws SQLException {
    String query = "SELECT * FROM users WHERE User_ID = ? LIMIT 1";
    try (PreparedStatement statement = Query.createQuery(query);) {
      statement.setInt(1, userId);
      try (ResultSet result = statement.executeQuery()) {
        if (result.next()) {
          int resUserId = result.getInt("User_ID");
          String resUserName = result.getString("User_Name");
          Users queryResults = new Users(resUserId, resUserName);
          return queryResults;
        }
        return null;
      }
    }
  }

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

  public static String getCurrentUser() {
    return Users.getUser_Name();
  }

  public static Users getUser(int Id) throws SQLException, Exception{
    String sqlStatement = "SELECT * FROM users WHERE User_ID = '" + Id + "'";
    Connection conn = JDBC.connection;
    ResultSet result = conn.createStatement().executeQuery(sqlStatement);
    if (result.next()) {
      int resUserId = result.getInt("User_ID");
      String resUserName = result.getString("User_Name");
      Users queryResults = new Users(resUserId, resUserName);
      return queryResults;
    }
    return null;
  }

}
