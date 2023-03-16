package models.appointmentscheduler;

import java.time.LocalDateTime;

/**
 * Customer Model
 */

public class Customers {

  private int Customer_ID;
  private String Customer_Name;
  private String Address;
  private String Postal_Code;
  private String Phone;
  private LocalDateTime Create_Date;
  private String Created_By;
  private LocalDateTime Last_Update;
  private String Last_Updated_By;
  private int Division_ID;
  private int Country_ID;

    /**
     * Constructor
     * @param customer_ID
     * @param customer_Name
     * @param address
     * @param postal_Code
     * @param phone
     * @param create_Date
     * @param created_By
     * @param last_Update
     * @param last_Updated_By
     * @param division_ID
     */

  public Customers(
    int customer_ID,
    String customer_Name,
    String address,
    String postal_Code,
    String phone,
    LocalDateTime create_Date,
    String created_By,
    LocalDateTime last_Update,
    String last_Updated_By,
    int division_ID
  ) {
    this.Customer_ID = customer_ID;
    this.Customer_Name = customer_Name;
    this.Address = address;
    this.Postal_Code = postal_Code;
    this.Phone = phone;
    this.Create_Date = create_Date;
    this.Created_By = created_By;
    this.Last_Update = last_Update;
    this.Last_Updated_By = last_Updated_By;
    this.Division_ID = division_ID;
  }

    /**
     * Get Customer ID
     * @return Customer_ID
     */

  public int getCustomer_ID() {
    return Customer_ID;
  }

    /**
     * Set Customer ID
     * @param customer_ID
     */

  public void setCustomer_ID(int customer_ID) {
    this.Customer_ID = customer_ID;
  }

    /**
     * Get Customer Name
     * @return Customer_Name
     */

  public String getCustomer_Name() {
    return Customer_Name;
  }

    /**
     * Set Customer Name
     * @param customer_Name
     */

  public void setCustomer_Name(String customer_Name) {
    this.Customer_Name = customer_Name;
  }

    /**
     * Get Address
     * @return Address
     */

  public String getAddress() {
    return Address;
  }

    /**
     * Set Address
     * @param address
     */

  public void setAddress(String address) {
    this.Address = address;
  }

    /**
     * Get Postal Code
     * @return Postal_Code
     */

  public String getPostal_Code() {
    return Postal_Code;
  }

    /**
     * Set Postal Code
     * @param postal_Code
     */

  public void setPostal_Code(String postal_Code) {
    this.Postal_Code = postal_Code;
  }

    /**
     * Get Phone
     * @return Phone
     */

  public String getPhone() {
    return Phone;
  }

    /**
     * Set Phone
     * @param phone
     */

  public void setPhone(String phone) {
    this.Phone = phone;
  }

    /**
     * Get Create Date
     * @return Create_Date
     */

  public LocalDateTime getCreate_Date() {
    return Create_Date;
  }

    /**
     * Set Create Date
     * @param create_Date
     */

  public void setCreate_Date(LocalDateTime create_Date) {
    this.Create_Date = create_Date;
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
    this.Created_By = created_By;
  }

    /**
     * Get Last Update
     * @return Last_Update
     */

  public LocalDateTime getLast_Update() {
    return Last_Update;
  }

    /**
     * Set Last Update
     * @param last_Update
     */

  public void setLast_Update(LocalDateTime last_Update) {
    this.Last_Update = last_Update;
  }

    /**
     * Get Last Updated By
     * @return Last_Updated_By
     */

  public String getLast_Updated_By() {
    return Last_Updated_By;
  }

    /**
     * Set Last Updated By
     * @param last_Updated_By
     */

  public void setLast_Updated_By(String last_Updated_By) {
    this.Last_Updated_By = last_Updated_By;
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
    this.Division_ID = division_ID;
  }

  /**
   * Sets combo box to display Customer ID and Customer Name
   * @return
   */

  // Necessary for ComboBox
  @Override
  public String toString() {
    return (Integer.toString(Customer_ID) + " " + Customer_Name);
  }

}
