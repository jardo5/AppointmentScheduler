package models.appointmentscheduler;

import java.time.LocalDateTime;

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

  public int getCustomer_ID() {
    return Customer_ID;
  }

  public void setCustomer_ID(int customer_ID) {
    this.Customer_ID = customer_ID;
  }

  public String getCustomer_Name() {
    return Customer_Name;
  }

  public void setCustomer_Name(String customer_Name) {
    this.Customer_Name = customer_Name;
  }

  public String getAddress() {
    return Address;
  }

  public void setAddress(String address) {
    this.Address = address;
  }

  public String getPostal_Code() {
    return Postal_Code;
  }

  public void setPostal_Code(String postal_Code) {
    this.Postal_Code = postal_Code;
  }

  public String getPhone() {
    return Phone;
  }

  public void setPhone(String phone) {
    this.Phone = phone;
  }

  public LocalDateTime getCreate_Date() {
    return Create_Date;
  }

  public void setCreate_Date(LocalDateTime create_Date) {
    this.Create_Date = create_Date;
  }

  public String getCreated_By() {
    return Created_By;
  }

  public void setCreated_By(String created_By) {
    this.Created_By = created_By;
  }

  public LocalDateTime getLast_Update() {
    return Last_Update;
  }

  public void setLast_Update(LocalDateTime last_Update) {
    this.Last_Update = last_Update;
  }

  public String getLast_Updated_By() {
    return Last_Updated_By;
  }

  public void setLast_Updated_By(String last_Updated_By) {
    this.Last_Updated_By = last_Updated_By;
  }

  public int getDivision_ID() {
    return Division_ID;
  }

  public void setDivision_ID(int division_ID) {
    this.Division_ID = division_ID;
  }

  // Necessary for ComboBox
  @Override
  public String toString() {
    return Customer_Name;
  }
}
