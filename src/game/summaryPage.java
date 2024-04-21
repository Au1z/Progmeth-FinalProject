package game;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
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
    private AudioClip bgSound;
    private AudioClip buttonEffect;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        endBg.setImage(new Image("image/endBg.png"));
        retryButton.setImage(new Image("image/retry.png"));
        exitButton.setImage(new Image("image/exit.png"));
        bgSound = new AudioClip(getClass().getResource("/audio/sumPage.mp3").toString());
        bgSound.setVolume(1);
        buttonEffect = new AudioClip(getClass().getResource("/audio/buttonEffect2.mp3").toString());
        buttonEffect.setVolume(1);
        Thread soundThread = new Thread(() -> {
            if (bgSound != null) {
                bgSound.play();
            } else {
                System.err.println("AudioClip (bgSound) is null.");
            }
        });
        soundThread.start();
    }

    public void setPlayer1Win(Boolean check) {
        if(check){
            p1Wins.setImage(new Image("image/p1Win.png"));
            p2Wins.setImage(null);
        }
        else{
            p1Wins.setImage(null);
            p2Wins.setImage(new Image("image/p2Win.png"));
        }
    }

    @FXML
    public void expandImage1(MouseEvent mouseEvent) {
        Thread effect = new Thread(() -> {
            if (buttonEffect != null) {
                buttonEffect.play();
            } else {
                System.err.println("AudioClip (bgSound) is null.");
            }
        });
        effect.start();
        retryButton.setScaleX(1.2);
        retryButton.setScaleY(1.2);
    }

    @FXML
    public void expandImage2(MouseEvent mouseEvent) {
        Thread effect = new Thread(() -> {
            if (buttonEffect != null) {
                buttonEffect.play();
            } else {
                System.err.println("AudioClip (bgSound) is null.");
            }
        });
        effect.start();
        exitButton.setScaleX(1.2);
        exitButton.setScaleY(1.2);
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
        bgSound.stop();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Board.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) retryButton.getScene().getWindow();

            stage.setTitle("Castle of Bloodlines: The Monopoly of Nightmares");
            stage.setScene(new Scene(root, 1200, 700));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exit(MouseEvent mouseEvent) {

    }
}