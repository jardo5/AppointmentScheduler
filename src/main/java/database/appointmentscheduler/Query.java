package database.appointmentscheduler;

import java.sql.*;

/**
 * Query Class generates a prepared statement
 */

public class Query {

  private static String sqlQuery;
  private static Statement statement;
  private static ResultSet results;

  /**
   * Creates a prepared statement
   * @param query SQL query
   * @return statement prepared statement
   */

  public static PreparedStatement createQuery(String query) {
    PreparedStatement statement = null;
    try {
      statement = JDBC.connection.prepareStatement(query);
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
    return statement;
  }

  /**
   * Gets the results of the query
   * @return results
   */

  public static ResultSet getResults() {
    return results;
  }
}
