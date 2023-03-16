package models.appointmentscheduler;

import java.sql.Timestamp;

/**
 * Country Model
 */

public class Countries {

  private int Country_ID;
  private String Country;
  private Timestamp Create_Date;
  private String Created_By;
  private Timestamp Last_Update;
  private String Updated_By;

    /**
     * Constructor
     * @param country_ID
     * @param country
     * @param create_Date
     * @param created_By
     * @param last_Update
     * @param updated_By
     */

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
     * Get Country
     * @return Country
     */

  public String getCountry() {
    return Country;
  }

    /**
     * Set Country
     * @param country
     */

  public void setCountry(String country) {
    Country = country;
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
     * Get Created By
     * @return Created_By
     */

  public String getCreated_By() {
    return Created_By;
  }

    /**
     * Set Created By
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
   * Sets combo box to display country name
   * @return
   */

  //Necessary for ComboBox
  @Override
    public String toString() {
        return Country;
    }
}
