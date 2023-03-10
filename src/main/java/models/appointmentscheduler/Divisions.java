package models.appointmentscheduler;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Divisions {

  private int Division_ID;
  private String Division;
  private Timestamp Create_Date;
  private String Created_By;
  private Timestamp Last_Update;
  private String Updated_By;
  private int Country_ID;

  public Divisions(
    int division_ID,
    String division,
    Timestamp create_Date,
    String created_By,
    Timestamp last_Update,
    String updated_By,
    int country_ID
  ) {
    Division_ID = division_ID;
    Division = division;
    Create_Date = create_Date;
    Created_By = created_By;
    Last_Update = last_Update;
    Updated_By = updated_By;
    Country_ID = country_ID;
  }

  public int getDivision_ID() {
    return Division_ID;
  }

  public void setDivision_ID(int division_ID) {
    Division_ID = division_ID;
  }

  public String getDivision() {
    return Division;
  }

  public void setDivision(String division) {
    Division = division;
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

  public int getCountry_ID() {
    return Country_ID;
  }

  public void setCountry_ID(int country_ID) {
    Country_ID = country_ID;
  }

  @Override
  // Necessary to display the division name in the ComboBox
    public String toString() {
        return Division;
    }
}
