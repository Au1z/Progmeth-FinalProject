package game;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Other implements Initializable {

    public ImageView cms;
    public ImageView back;
    public ImageView bg;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cms.setImage(new Image("image/cms.png"));
        back.setImage(new Image("image/back.png"));
        bg.setImage(new Image("image/homeBg.jpg"));
    }

    public void goBack(MouseEvent mouseEvent) {
//        System.out.println("asdada");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
//            System.out.println("sadasds");
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) back.getScene().getWindow();

            stage.setTitle("Castle of Bloodlines: The Monopoly of Nightmares");
            stage.setScene(new Scene(root, 1200, 675));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
