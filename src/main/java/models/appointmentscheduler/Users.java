package models.appointmentscheduler;

import java.time.LocalDateTime;

/**
 * Users Model
 */

public class Users {

  private static Users loggedUser;

  private static int User_ID;
  private static String User_Name;
  private String Password;
  private LocalDateTime Create_Date;
  private String Created_By;
  private LocalDateTime Last_Update;
  private String Updated_By;

    /**
     * Constructor
     * @param user_ID
     * @param user_Name
     */

  public Users(int user_ID, String user_Name) {
    this.User_ID = user_ID;
    this.User_Name = user_Name;
  }

    /**
     * Get User ID
     * @return User_ID
     */

  public static void setLoggedUser(Users loggedUser) {
    Users.loggedUser = loggedUser;
  }

    /**
     * Set User ID
     * @param
     */

  public static int getUser_ID() {
    return User_ID;
  }

    /**
     * Get User Name
     * @return User_Name
     */

  public void setUser_ID(int user_ID) {
    this.User_ID = user_ID;
  }

    /**
     * Set User Name
     */

  public static String getUser_Name() {
    return User_Name;
  }

    /**
     * Get Password
     * @return Password
     */

  public void setUser_Name(String user_Name) {
    this.User_Name = user_Name;
  }

    /**
     * Set Password
     */

  public String getPassword() {
    return Password;
  }

    /**
     * Get Create Date
     * @return Create_Date
     */

  public void setPassword(String password) {
    this.Password = password;
  }

    /**
     * Set Create Date
     */

  public LocalDateTime getCreate_Date() {
    return Create_Date;
  }

    /**
     * Get Created By
     * @return Created_By
     */

  public void setCreate_Date(LocalDateTime create_Date) {
    this.Create_Date = create_Date;
  }

    /**
     * Set Created By
     */

  public String getCreated_By() {
    return Created_By;
  }

    /**
     * Get Last Update
     * @return Last_Update
     */

  public void setCreated_By(String created_By) {
    this.Created_By = created_By;
  }

    /**
     * Set Last Update
     */

  public LocalDateTime getLast_Update() {
    return Last_Update;
  }

    /**
     * Get Last Updated By
     * @return Last_Updated_By
     */

  public void setLast_Update(LocalDateTime last_Update) {
    this.Last_Update = last_Update;
  }

    /**
     * Set Last Updated By
     */

  public String getUpdated_By() {
    return Updated_By;
  }

    /**
     * Set Last Updated By
     */

  public void setUpdated_By(String updated_By) {
    this.Updated_By = updated_By;
  }

    /**
     * Sets combo box to display User ID and User Name
     * @return User_ID + User_Name
     */

  //Necessary for ComboBox
  @Override
  public String toString() {
    return (Integer.toString(User_ID) + " " + User_Name);
  }
}
