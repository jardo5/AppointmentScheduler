package models.appointmentscheduler;

public class Contacts {

  private int Contact_ID;
  private String Contact_Name;
  private String Email;

  public Contacts(int contact_ID, String contact_Name, String email) {
    this.Contact_ID = contact_ID;
    this.Contact_Name = contact_Name;
    this.Email = email;
  }

  public int getContact_ID() {
    return Contact_ID;
  }

  public void setContact_ID(int contact_ID) {
    this.Contact_ID = contact_ID;
  }

  public String getContact_Name() {
    return Contact_Name;
  }

  public void setContact_Name(String contact_Name) {
    this.Contact_Name = contact_Name;
  }

  public String getEmail() {
    return Email;
  }

  public void setEmail(String email) {
    this.Email = email;
  }

  // Necessary for ComboBox
  @Override
  public String toString() {
    return (Integer.toString(Contact_ID) + " " + Contact_Name);
  }
}
