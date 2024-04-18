package game;

import javafx.event.ActionEvent;
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
import game.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePage implements Initializable {
    public ImageView startButton1;
    public ImageView option1;
    public ImageView option2;
    public ImageView homeBg;
    private AudioClip homeSound;
    private AudioClip buttonEffect;
    private AudioClip playEffect;

    public void gotoBoard() {
        Thread effect = new Thread(() -> {
            if (playEffect != null) {
                playEffect.play();
            } else {
                System.err.println("AudioClip (bgSound) is null.");
            }
        });
        effect.start();
        homeSound.stop();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Board.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) startButton1.getScene().getWindow();

            stage.setTitle("Castle of Bloodlines: The Monopoly of Nightmares");
            stage.setScene(new Scene(root,1200,800));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        startButton1.setImage(new Image("startGameText1.png"));
        option1.setImage(new Image("howToPlayText.png"));
        option2.setImage(new Image("viewStory.png"));
        homeBg.setImage(new Image("homeBg.jpg"));
        buttonEffect = new AudioClip(getClass().getResource("/audio/buttonEffect2.mp3").toString());
        buttonEffect.setVolume(1);
        playEffect = new AudioClip(getClass().getResource("/audio/playEffect.mp3").toString());
        playEffect.setVolume(1);
        homeSound = new AudioClip(getClass().getResource("/audio/homeSound.mp3").toString());
        homeSound.setVolume(0.5);
        Thread soundThread = new Thread(() -> {
            if (homeSound != null) {
                homeSound.play();
            } else {
                System.err.println("AudioClip (bgSound) is null.");
            }
        });
        soundThread.start();
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
        startButton1.setScaleX(1.1);
        startButton1.setScaleY(1.1);
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
        option1.setScaleX(1.1);
        option1.setScaleY(1.1);
    }

    @FXML
    public void expandImage3(MouseEvent mouseEvent) {
        Thread effect = new Thread(() -> {
            if (buttonEffect != null) {
                buttonEffect.play();
            } else {
                System.err.println("AudioClip (bgSound) is null.");
            }
        });
        effect.start();
        option2.setScaleX(1.1);
        option2.setScaleY(1.1);
    }

    @FXML
    private void shrinkImage1() {
        startButton1.setScaleX(1.0);
        startButton1.setScaleY(1.0);
    }

    @FXML
    private void shrinkImage2() {
        option1.setScaleX(1.0);
        option1.setScaleY(1.0);
    }

    @FXML
    private void shrinkImage3() {
        option2.setScaleX(1.0);
        option2.setScaleY(1.0);
    }
}