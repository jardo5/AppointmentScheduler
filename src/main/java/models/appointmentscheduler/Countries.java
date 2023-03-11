package models.appointmentscheduler;

import java.sql.Timestamp;

public class Countries {

  private int Country_ID;
  private String Country;
  private Timestamp Create_Date;
  private String Created_By;
  private Timestamp Last_Update;
  private String Updated_By;

  public Countries(
    int country_ID,
    String country,
    Timestamp create_Date,
    String created_By,
    Timestamp last_Update,
    String updated_By
  ) {
    Country_ID = country_ID;
    Country = country;
    Create_Date = create_Date;
    Created_By = created_By;
    Last_Update = last_Update;
    Updated_By = updated_By;
  }

  public int getCountry_ID() {
    return Country_ID;
  }

  public void setCountry_ID(int country_ID) {
    Country_ID = country_ID;
  }

  public String getCountry() {
    return Country;
  }

  public void setCountry(String country) {
    Country = country;
  }

  public Timestamp getCreate_Date() {
    return Create_Date;
  }

  public void setCreate_Date(Timestamp create_Date) {
    Create_Date = create_Date;
  }

  public String getCreated_By() {
    return Created_By;
  }

  public void setCreated_By(String created_By) {
    Created_By = created_By;
  }

  public Timestamp getLast_Update() {
    return Last_Update;
  }

  public void setLast_Update(Timestamp last_Update) {
    Last_Update = last_Update;
  }

  public String getUpdated_By() {
    return Updated_By;
  }

  public void setUpdated_By(String updated_By) {
    Updated_By = updated_By;
  }
}
