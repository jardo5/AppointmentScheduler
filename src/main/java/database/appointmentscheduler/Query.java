package database.appointmentscheduler;

import java.sql.*;

public class Query {

  private static String sqlQuery;
  private static Statement statement;
  private static ResultSet results;

  public static PreparedStatement createQuery(String query) {
    PreparedStatement statement = null;
    try {
      statement = JDBC.connection.prepareStatement(query);
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
    return statement;
  }

  public static ResultSet getResults() {
    return results;
  }
}
