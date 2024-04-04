package game;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import game.Main;
import java.io.IOException;

public class HomePage {
    public void gotoBoard() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Board.fxml"));


        try {
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Castle of Bloodlines: The Monopoly of Nightmares");
            stage.show();


        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

}