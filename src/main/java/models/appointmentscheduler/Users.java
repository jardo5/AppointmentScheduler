package models.appointmentscheduler;

import java.time.LocalDateTime;

public class Users {

  private static Users loggedUser;

  private int User_ID;
  private static String User_Name;
  private String Password;
  private LocalDateTime Create_Date;
  private String Created_By;
  private LocalDateTime Last_Update;
  private String Updated_By;

  public Users(int user_ID, String user_Name) {
    this.User_ID = user_ID;
    this.User_Name = user_Name;
  }

  public static void setLoggedUser(Users loggedUser) {
    Users.loggedUser = loggedUser;
  }

  public int getUser_ID() {
    return User_ID;
  }

  public void setUser_ID(int user_ID) {
    this.User_ID = user_ID;
  }

  public static String getUser_Name() {
    return User_Name;
  }

  public void setUser_Name(String user_Name) {
    this.User_Name = user_Name;
  }

  public String getPassword() {
    return Password;
  }

  public void setPassword(String password) {
    this.Password = password;
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

  public String getUpdated_By() {
    return Updated_By;
  }

  public void setUpdated_By(String updated_By) {
    this.Updated_By = updated_By;
  }

  //Necessary for ComboBox
  @Override
  public String toString() {
    return (Integer.toString(User_ID) + " " + User_Name);
  }
}
