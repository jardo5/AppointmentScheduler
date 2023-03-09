package database.appointmentscheduler;

import java.sql.ResultSet;
import java.sql.Statement;

public class Query {
    private static String sqlQuery;
    private static Statement statement;
    private static ResultSet results;

    public static void createQuery(String query) {
        sqlQuery = query;
        try {
            statement = JDBC.connection.createStatement();

            if (sqlQuery.toLowerCase().startsWith("select")) {
                results = statement.executeQuery(sqlQuery);
            }
            if (sqlQuery.toLowerCase().startsWith("delete") || sqlQuery.toLowerCase().startsWith("insert") || sqlQuery.toLowerCase().startsWith("update")) {
                statement.executeUpdate(sqlQuery);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static ResultSet getResults() {
        return results;
    }
}
