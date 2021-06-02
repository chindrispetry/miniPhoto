package sample;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.FileNotFoundException;

public class Laborator extends Application {

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        Scene scena = new Scene(new Window().getPanou(), 750, 500);
        primaryStage.setScene(scena);
        primaryStage.setTitle("miniPhoto");
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}

