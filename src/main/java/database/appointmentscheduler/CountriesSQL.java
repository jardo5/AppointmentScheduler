package database.appointmentscheduler;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
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

}
