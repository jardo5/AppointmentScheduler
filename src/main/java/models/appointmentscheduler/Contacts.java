package models.appointmentscheduler;

/**
 * Contact Model
 */

public class Contacts {

  private int Contact_ID;
  private String Contact_Name;
  private String Email;

  /**
   * Constructor
   * @param contact_ID
   * @param contact_Name
   * @param email
   */

  public Contacts(int contact_ID, String contact_Name, String email) {
    this.Contact_ID = contact_ID;
    this.Contact_Name = contact_Name;
    this.Email = email;
  }

    /**
     * Gets Contact ID
     * @return Contact_ID
     */

  public int getContact_ID() {
    return Contact_ID;
  }

    /**
     * Sets Contact ID
     * @param contact_ID
     */

  public void setContact_ID(int contact_ID) {
    this.Contact_ID = contact_ID;
  }

    /**
     * Gets Contact Name
     * @return Contact_Name
     */

  public String getContact_Name() {
    return Contact_Name;
  }

    /**
     * Sets Contact Name
     * @param contact_Name
     */

  public void setContact_Name(String contact_Name) {
    this.Contact_Name = contact_Name;
  }

    /**
     * Gets Email
     * @return Email
     */

  public String getEmail() {
    return Email;
  }

    /**
     * Sets Email
     * @param email
     */

  public void setEmail(String email) {
    this.Email = email;
  }

    /**
     * Returns Contact ID and Contact Name (Allows ComboBox to display Contact_ID and Contact_Name)
     * @return Contact_ID and Contact_Name
     */

  // Necessary for ComboBox
  @Override
  public String toString() {
    return (Integer.toString(Contact_ID) + " " + Contact_Name);
  }
}
