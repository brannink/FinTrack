package fintrack.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Motor inicial da aplicação
 * FinApp
 */
public class FinApp extends Application{
    public void start(Stage stage) throws Exception{
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/fintrack/view/Principal.fxml"));
        Scene scene = new Scene(loader.load(), 800, 500);
        stage.setTitle("Fintrack");
        scene.getStylesheets().add(getClass().getResource("/fintrack/view/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static void main (String[] args) {
        launch(args);
    }
}

