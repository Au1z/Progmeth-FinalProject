package game;

import javafx.fxml.FXML;
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
import game.Board;

public class summaryPage implements Initializable {
    public ImageView endBg;
    public ImageView p2Wins;
    public ImageView p1Wins;
    public ImageView retryButton;
    public ImageView exitButton;
    public Board board;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        if(board.gethpPlayer1()<=0){
//            p2Wins.setVisible(true);
//        } else{
//            p1Wins.setVisible(true);
//        }
        endBg.setImage(new Image("endBg.png"));
        retryButton.setImage(new Image("retry.png"));
        exitButton.setImage(new Image("exit.png"));
    }



    @FXML
    public void expandImage1(MouseEvent mouseEvent) {
        retryButton.setScaleX(1.1);
        retryButton.setScaleY(1.1);
    }

    @FXML
    public void expandImage2(MouseEvent mouseEvent) {
        exitButton.setScaleX(1.1);
        exitButton.setScaleY(1.1);
    }

    @FXML
    private void shrinkImage1() {
        retryButton.setScaleX(1.0);
        retryButton.setScaleY(1.0);
    }

    @FXML
    private void shrinkImage2() {
        exitButton.setScaleX(1.0);
        exitButton.setScaleY(1.0);
    }

    public void gotoBoard() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Board.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) retryButton.getScene().getWindow();

            stage.setTitle("Castle of Bloodlines: The Monopoly of Nightmares");
            stage.setScene(new Scene(root, 1200, 800));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exit(MouseEvent mouseEvent) {

    }
}