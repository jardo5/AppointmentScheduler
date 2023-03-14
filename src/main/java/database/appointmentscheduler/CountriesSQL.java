package database.appointmentscheduler;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

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

    public static int findCountryID(int divisionId) throws SQLException {
        String sql = "SELECT Country_ID FROM first_level_divisions WHERE Division_ID = " + divisionId;
        Connection conn = JDBC.connection;
        ResultSet result = conn.createStatement().executeQuery(sql);
        result.next();
        return result.getInt("Country_ID");
    }


    public static ObservableList<Pair<Integer, String>> getDivisionNameByCountry(String countryId) throws SQLException {
        ObservableList<Pair<Integer, String>> divisionList = FXCollections.observableArrayList();
        String sql = "SELECT Division_ID, Division from first_level_divisions where Country_ID = '" + countryId + "'";
        Connection conn = JDBC.connection;
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            divisionList.add(new Pair<>(result.getInt("Division_ID"), result.getString("Division")));
        }
        return divisionList;
    }


    public static String getCountryName(int countryID) throws SQLException {
        String sql = "SELECT Country from countries where Country_ID = ?";
        Connection conn = JDBC.connection;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, countryID);
        ResultSet result = statement.executeQuery();
        result.next();
        return result.getString("Country");
    }

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
