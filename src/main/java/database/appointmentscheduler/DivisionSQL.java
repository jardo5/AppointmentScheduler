package database.appointmentscheduler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.appointmentscheduler.Divisions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DivisionSQL {

    public static int getDivisionID(String divisionName) throws Exception, SQLException {
        System.out.println("divisionName: " + divisionName);
        String sql = "SELECT Division_ID from first_level_divisions where Division = '" + divisionName + "'";
        PreparedStatement statement = JDBC.connection.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        result.next();
        return result.getInt("Division_ID");
    }

    public static ObservableList<Divisions> getAllDivisionNames() throws SQLException{
        ObservableList<Divisions> divisionList = FXCollections.observableArrayList();
        String sql = "SELECT Division_ID, Division, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID FROM first_level_divisions"; // Column 'Division_ID' not found??.
        Connection conn = JDBC.connection;
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            Divisions division = new Divisions(
                    result.getInt("Division_ID"),
                    result.getString("Division"),
                    result.getTimestamp("Create_Date"),
                    result.getString("Created_By"),
                    result.getTimestamp("Last_Update"),
                    result.getString("Last_Updated_By"),
                    result.getInt("COUNTRY_ID")
                    );
            divisionList.add(division);
        }
        return divisionList;
    }

    public static Object getDivisionName(int divisionId) throws SQLException {
        String sql = "SELECT Division FROM first_level_divisions WHERE Division_ID = ?";
        Connection conn = JDBC.connection;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, divisionId);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            String division = result.getString("Division");
            return division;
        }
        return null;
    }

    public static ObservableList<String> getDivisionNameByCountry(String countryId) throws SQLException {
        ObservableList<String> divisionList = FXCollections.observableArrayList();
        String sql = "SELECT Division FROM first_level_divisions WHERE Country_ID = ?";
        Connection conn = JDBC.connection;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, countryId);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            divisionList.add(result.getString("Division"));
        }
        return divisionList;
    }


}
