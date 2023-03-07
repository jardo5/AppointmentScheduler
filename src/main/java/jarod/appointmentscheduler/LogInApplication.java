package jarod.appointmentscheduler;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class LogInApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 550);
        stage.setTitle("Appointment Scheduler Login");
        stage.setScene(scene);
        scene.setUserAgentStylesheet("loginStyles.css");
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
