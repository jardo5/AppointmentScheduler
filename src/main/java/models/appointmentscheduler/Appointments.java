package models.appointmentscheduler;

import java.time.LocalDateTime;

/**
 * Appointment Model
 */

public class Appointments {

  private int Appointment_ID;
  private String Title;
  private String Description;
  private String Location;
  private String Type;
  private LocalDateTime Start;
  private LocalDateTime End;
  private LocalDateTime Create_Date;
  private String Created_By;
  private LocalDateTime Last_Update;
  private String Updated_By;
  private int Customer_ID;
  private int User_ID;
  private int Contact_ID;

  /**
   * Constructor
   * @param appointment_ID
   * @param title
   * @param description
   * @param location
   * @param type
   * @param start
   * @param end
   * @param create_Date
   * @param created_By
   * @param last_Update
   * @param updated_By
   * @param customer_ID
   * @param user_ID
   * @param contact_ID
   */

  public Appointments(
    int appointment_ID,
    String title,
    String description,
    String location,
    String type,
    LocalDateTime start,
    LocalDateTime end,
    LocalDateTime create_Date,
    String created_By,
    LocalDateTime last_Update,
    String updated_By,
    int customer_ID,
    int user_ID,
    int contact_ID
  ) {
    this.Appointment_ID = appointment_ID;
    this.Title = title;
    this.Description = description;
    this.Location = location;
    this.Type = type;
    this.Start = start;
    this.End = end;
    this.Create_Date = create_Date;
    this.Created_By = created_By;
    this.Last_Update = last_Update;
    this.Updated_By = updated_By;
    this.Customer_ID = customer_ID;
    this.User_ID = user_ID;
    this.Contact_ID = contact_ID;
  }


  /**
   * Get appointment ID
   * @return appointment ID
   */
  public int getAppointment_ID() {
    return Appointment_ID;
  }

  /**
   * Set appointment ID
   * @param appointment_ID set appointment ID
   */

  public void setAppointment_ID(int appointment_ID) {
    this.Appointment_ID = appointment_ID;
  }

    /**
     * Get appointment title
     * @return appointment title
     */

  public String getTitle() {
    return Title;
  }

    /**
     * Set appointment title
     * @param title set appointment title
     */

  public void setTitle(String title) {
    this.Title = title;
  }

    /**
     * Get appointment description
     * @return appointment description
     */

  public String getDescription() {
    return Description;
  }

    /**
     * Set appointment description
     * @param description set appointment description
     */

  public void setDescription(String description) {
    this.Description = description;
  }

    /**
     * Get appointment location
     * @return appointment location
     */

  public String getLocation() {
    return Location;
  }

    /**
     * Set appointment location
     * @param location set appointment location
     */

  public void setLocation(String location) {
    this.Location = location;
  }

    /**
     * Get appointment type
     * @return appointment type
     */

  public String getType() {
    return Type;
  }

    /**
     * Set appointment type
     * @param type set appointment type
     */

  public void setType(String type) {
    this.Type = type;
  }

    /**
     * Get appointment start time
     * @return appointment start time
     */

  public  LocalDateTime getStart() {
    return Start;
  }

    /**
     * Set appointment start time
     * @param start set appointment start time
     */

  public void setStart(LocalDateTime start) {
    this.Start = start;
  }

    /**
     * Get appointment end time
     * @return appointment end time
     */

  public LocalDateTime getEnd() {
    return End;
  }

    /**
     * Set appointment end time
     * @param end set appointment end time
     */

  public void setEnd(LocalDateTime end) {
    this.End = end;
  }

    /**
     * Get appointment create date
     * @return appointment create date
     */

  public LocalDateTime getCreate_Date() {
    return Create_Date;
  }

    /**
     * Set appointment create date
     * @param create_Date set appointment create date
     */

  public void setCreate_Date(LocalDateTime create_Date) {
    this.Create_Date = create_Date;
  }

    /**
     * Get appointment created by
     * @return appointment created by
     */

  public String getCreated_By() {
    return Created_By;
  }

    /**
     * Set appointment created by
     * @param created_By set appointment created by
     */

  public void setCreated_By(String created_By) {
    this.Created_By = created_By;
  }

    /**
     * Get appointment last update
     * @return appointment last update
     */

  public LocalDateTime getLast_Update() {
    return Last_Update;
  }

    /**
     * Set appointment last update
     * @param last_Update set appointment last update
     */

  public void setLast_Update(LocalDateTime last_Update) {
    this.Last_Update = last_Update;
  }

    /**
     * Get appointment updated by
     * @return appointment updated by
     */

  public String getUpdated_By() {
    return Updated_By;
  }

    /**
     * Set appointment updated by
     * @param updated_By set appointment updated by
     */

  public void setUpdated_By(String updated_By) {
    this.Updated_By = updated_By;
  }

    /**
     * Get appointment customer ID
     * @return appointment customer ID
     */

  public int getCustomer_ID() {
    return Customer_ID;
  }

    /**
     * Set appointment customer ID
     * @param customer_ID set appointment customer ID
     */

  public void setCustomer_ID(int customer_ID) {
    this.Customer_ID = customer_ID;
  }

    /**
     * Get appointment user ID
     * @return appointment user ID
     */

  public int getUser_ID() {
    return User_ID;
  }

    /**
     * Set appointment user ID
     * @param user_ID set appointment user ID
     */

  public void setUser_ID(int user_ID) {
    this.User_ID = user_ID;
  }

    /**
     * Get appointment contact ID
     * @return appointment contact ID
     */

  public int getContact_ID() {
    return Contact_ID;
  }

    /**
     * Set appointment contact ID
     * @param contact_ID set appointment contact ID
     */

  public void setContact_ID(int contact_ID) {
    this.Contact_ID = contact_ID;
  }
}
