package models.appointmentscheduler;

import java.time.LocalDateTime;

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

    public Appointments(int appointment_ID, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, LocalDateTime create_Date, String created_By, LocalDateTime last_Update, String updated_By, int customer_ID, int user_ID, int contact_ID) {
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

    public int getAppointment_ID() {
        return Appointment_ID;
    }

    public void setAppointment_ID(int appointment_ID) {
        this.Appointment_ID = appointment_ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        this.Location = location;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        this.Type = type;
    }

    public LocalDateTime getStart() {
        return Start;
    }

    public void setStart(LocalDateTime start) {
        this.Start = start;
    }

    public LocalDateTime getEnd() {
        return End;
    }

    public void setEnd(LocalDateTime end) {
        this.End = end;
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

    public int getCustomer_ID() {
        return Customer_ID;
    }

    public void setCustomer_ID(int customer_ID) {
        this.Customer_ID = customer_ID;
    }

    public int getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(int user_ID) {
        this.User_ID = user_ID;
    }

    public int getContact_ID() {
        return Contact_ID;
    }

    public void setContact_ID(int contact_ID) {
        this.Contact_ID = contact_ID;
    }
}
