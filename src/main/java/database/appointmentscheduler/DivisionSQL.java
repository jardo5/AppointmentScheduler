package database.appointmentscheduler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.appointmentscheduler.Divisions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DivisionSQL {

    public static int getDivisionID(String divisionName) throws Exception {
        String sql = "SELECT Division_ID FROM first_level_divisions WHERE Division = ?";
        PreparedStatement statement = JDBC.connection.prepareStatement(sql);
        statement.setString(1, divisionName);
        ResultSet result = statement.executeQuery();
        if (!result.next()) {
            throw new Exception("No results found for division name: " + divisionName);
        }
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


}
