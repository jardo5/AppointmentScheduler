package database.appointmentscheduler;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This is the Class to hold the Country queries
 */

public class CountriesSQL {

    /**
     * Get all countries names
     * @return allCountriesName
     * @throws SQLException
     */

    public static ObservableList<String> getAllCountriesName() throws SQLException, Exception {
        ObservableList<String> allCountriesNames = FXCollections.observableArrayList();
        String sql = "SELECT * from Countries";
        Connection conn = JDBC.connection;
        ResultSet result = conn.createStatement().executeQuery(sql);
        while (result.next()) {
            allCountriesNames.add(result.getString("Country"));
        }
        return allCountriesNames;
    }

    /**
     * Get Country ID by Division Name
     * @return Country ID
     * @throws SQLException
     */

    public static int getCountryIDByDivision(String divisionName) {
        String sql = "SELECT Country_ID from first_level_divisions where Division = '" + divisionName + "'";
        try {
            PreparedStatement statement = JDBC.connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            result.next();
            return result.getInt("Country_ID");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Get Country Name by Division Name
     * @return ObservableList of Countries
     * @throws SQLException
     */

    public static Object getCountryNameByDivision(String divisionName) {
        String sql = "SELECT Country from countries where Country_ID = (SELECT Country_ID from first_level_divisions where Division = '" + divisionName + "')";
        try {
            PreparedStatement statement = JDBC.connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            result.next();
            return result.getString("Country");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
