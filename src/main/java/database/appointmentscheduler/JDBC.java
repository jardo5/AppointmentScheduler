package database.appointmentscheduler;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Connection to database
 */

public abstract class JDBC {

  private static final String protocol = "jdbc";
  private static final String vendor = ":mysql:";
  private static final String location = "//localhost/";
  private static final String databaseName = "client_schedule";
  private static final String jdbcUrl =
    protocol +
    vendor +
    location +
    databaseName +
    "?connectionTimeZone = SERVER"; // LOCAL
  private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
  private static final String userName = "sqlUser"; // Username
  private static String password = "Passw0rd!"; // Password
  public static Connection connection; // Connection Interface

    /**
     * Opens a connection to the database
     */

  public static void openConnection() {
    try {
      Class.forName(driver);
      connection = DriverManager.getConnection(jdbcUrl, userName, password);
      System.out.println("DB Connection successful!");
    } catch (Exception e) {
      System.out.println("Error:" + e.getMessage());
    }
  }

    /**
     * Closes the connection to the database
     */

  public static void closeConnection() {
    try {
      connection.close();
      System.out.println("DB Connection closed!");
    } catch (Exception e) {
      System.out.println("Error:" + e.getMessage());
    }
  }
}
