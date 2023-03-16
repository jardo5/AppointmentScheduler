package models.appointmentscheduler;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * ReportCountry Model for Reports Customers By Country
 */

public class ReportCountry {
    private final SimpleStringProperty country;
    private final SimpleIntegerProperty count;

    /**
     * Constructor
     * @param country
     * @param count
     */

    public ReportCountry(String country, int count) {
        this.country = new SimpleStringProperty(country);
        this.count = new SimpleIntegerProperty(count);
    }

    /**
     * Get Country
     * @return country
     */

    public String getCountry() {
        return country.get();
    }

    /**
     * Set Country
     * @param country
     */

    public void setCountry(String country) {
        this.country.set(country);
    }

    /**
     * Get Count
     * @return count
     */

    public int getCount() {
        return count.get();
    }

    /**
     * Set Count
     * @param count
     */

    public void setCount(int count) {
        this.count.set(count);
    }
}
