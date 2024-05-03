package game;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Other implements Initializable {

    public ImageView comingSoon;
    public ImageView back;
    public ImageView homeBackground;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comingSoon.setImage(Config.CommingSoonImage);
        back.setImage(Config.BackText);
        homeBackground.setImage(Config.HomeBackground);
    }

    public void goBack() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) back.getScene().getWindow();

            stage.setTitle(Config.Title);
            stage.setScene(new Scene(root, 1200, 675));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
