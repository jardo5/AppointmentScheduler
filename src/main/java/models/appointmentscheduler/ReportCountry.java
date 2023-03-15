package models.appointmentscheduler;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ReportCountry {
    private final SimpleStringProperty country;
    private final SimpleIntegerProperty count;

    public ReportCountry(String country, int count) {
        this.country = new SimpleStringProperty(country);
        this.count = new SimpleIntegerProperty(count);
    }

    public String getCountry() {
        return country.get();
    }

    public void setCountry(String country) {
        this.country.set(country);
    }

    public int getCount() {
        return count.get();
    }

    public void setCount(int count) {
        this.count.set(count);
    }
}
