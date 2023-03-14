package database.appointmentscheduler;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountriesSQL {

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

    public static Object findCountryID(int divisionId) {
        String sql = "SELECT Country_ID FROM first_level_divisions WHERE Division_ID = " + divisionId;
        try {
            Connection conn = JDBC.connection;
            ResultSet result = conn.createStatement().executeQuery(sql);
            while (result.next()) {
                return result.getInt("Country_ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object getCountry_Name(int divisionId) {
        String sql = "SELECT Country FROM countries WHERE Country_ID = " + findCountryID(divisionId);
        try {
            Connection conn = JDBC.connection;
            ResultSet result = conn.createStatement().executeQuery(sql);
            while (result.next()) {
                return result.getString("Country");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object getCountryName(int countryID) throws SQLException {
        String sql = "SELECT Country from countries where Country_ID = '" + countryID + "'";
        Connection conn = JDBC.connection;
        ResultSet result = conn.createStatement().executeQuery(sql);
        result.next();
        return result.getString("Country");
    }

}
