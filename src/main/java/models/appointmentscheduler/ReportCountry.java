package models.appointmentscheduler;

public class ReportCountry {
    private int Country_Id;
    private String Country;

    public ReportCountry(int country_Id, String country){
        this.Country_Id = country_Id;
        this.Country = country;
    }

    public int getCountry_Id() {
        return Country_Id;
    }

    public void setCountry_Id(int country_Id) {
        Country_Id = country_Id;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }
}
