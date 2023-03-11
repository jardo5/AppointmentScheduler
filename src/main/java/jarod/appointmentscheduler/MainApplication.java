package jarod.appointmentscheduler;

import database.appointmentscheduler.JDBC;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {

  @Override
  public void start(Stage stage) throws IOException {
    ResourceBundle Language = ResourceBundle.getBundle("Language");
    System.out.println("You're language is set to " + Locale.getDefault());
    FXMLLoader fxmlLoader = new FXMLLoader(
      MainApplication.class.getResource("login.fxml")
    );
    fxmlLoader.setResources(Language);
    Scene scene = new Scene(fxmlLoader.load(), 400, 550);
    scene.setUserAgentStylesheet("cssStyles/loginStyles.css");
    stage.setTitle(Language.getString("Login"));
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) throws SQLException {
    JDBC.openConnection();
    launch();
    JDBC.closeConnection();
  }
}

//TODO: Delete Button for Customer/Appointment
//TODO Resize windows.
//TODO Fix Customer Delete Button
