package models.appointmentscheduler;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Divisions Model
 */
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

    /**
     * Get Division ID
     * @return Division_ID
     */

  public int getDivision_ID() {
    return Division_ID;
  }

    /**
     * Set Division ID
     * @param division_ID
     */

  public void setDivision_ID(int division_ID) {
    Division_ID = division_ID;
  }

    /**
     * Get Division
     * @return Division
     */

  public String getDivision() {
    return Division;
  }

    /**
     * Set Division
     * @param division
     */

  public void setDivision(String division) {
    Division = division;
  }

    /**
     * Get Create Date
     * @return Create_Date
     */

  public Timestamp getCreate_Date() {
    return Create_Date;
  }

    /**
     * Set Create Date
     * @param create_Date
     */

  public void setCreate_Date(Timestamp create_Date) {
    Create_Date = create_Date;
  }

    /**
     * Gets created by
     * @return Created_By
     */

  public String getCreated_By() {
    return Created_By;
  }

     /**
      * Sets created by
      * @param created_By
      */

  public void setCreated_By(String created_By) {
    Created_By = created_By;
  }

    /**
     * Get Last Update
     * @return Last_Update
     */

  public Timestamp getLast_Update() {
    return Last_Update;
  }

    /**
     * Set Last Update
     * @param last_Update
     */

  public void setLast_Update(Timestamp last_Update) {
    Last_Update = last_Update;
  }

    /**
     * Get Updated By
     * @return Updated_By
     */

  public String getUpdated_By() {
    return Updated_By;
  }

    /**
     * Set Updated By
     * @param updated_By
     */

  public void setUpdated_By(String updated_By) {
    Updated_By = updated_By;
  }

    /**
     * Get Country ID
     * @return Country_ID
     */

  public int getCountry_ID() {
    return Country_ID;
  }

    /**
     * Set Country ID
     * @param country_ID
     */

  public void setCountry_ID(int country_ID) {
    Country_ID = country_ID;
  }

  /**
   * Necessary to display the division name in the ComboBox
   * @return Division
   */

  @Override
  // Necessary to display the division name in the ComboBox
    public String toString() {
        return Division;
    }
}
