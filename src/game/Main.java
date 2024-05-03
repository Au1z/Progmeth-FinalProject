package game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle(Config.Title);
            primaryStage.setScene(new Scene(root,1200,675));
            primaryStage.show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}