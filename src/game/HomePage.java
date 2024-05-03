package game;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import utils.Sound;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HomePage implements Initializable {
    public ImageView startButton;
    public ImageView howToPlayButton;
    public ImageView viewStoryButton;
    public ImageView homeBackground;
    public ImageView exitButton;
    private AudioClip homeSound;
    private AudioClip buttonSound;
    private AudioClip startSound;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        startButton.setImage(Config.StartGameText);
        exitButton.setImage(Config.ExitText);
        howToPlayButton.setImage(Config.HowToPlayText);
        viewStoryButton.setImage(Config.ViewStoryText);
        homeBackground.setImage(Config.HomeBackground);

        buttonSound = new AudioClip(Objects.requireNonNull(getClass().getResource("/audio/buttonSound.mp3")).toString());
        buttonSound.setVolume(1);
        startSound = new AudioClip(Objects.requireNonNull(getClass().getResource("/audio/startSound.mp3")).toString());
        startSound.setVolume(1);
        homeSound = new AudioClip(Objects.requireNonNull(getClass().getResource("/audio/homeSound.mp3")).toString());
        homeSound.setVolume(0.5);

        Sound.changeBackgroundSound(null, homeSound);
    }

    public void gotoBoard() {
        Sound.changeBackgroundSound(homeSound, startSound);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Board.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) startButton.getScene().getWindow();

            stage.setTitle(Config.Title);
            stage.setScene(new Scene(root,1200,800));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void gotoHowToPlay() {
        Sound.changeBackgroundSound(homeSound, null);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Other.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) howToPlayButton.getScene().getWindow();

            stage.setTitle(Config.Title);
            stage.setScene(new Scene(root, 1200, 650));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void exit() {
        System.exit(0);
    }

    @FXML
    public void buttonImageEffect() {
        Thread effect = new Thread(() -> {
            if (buttonSound != null) {
                buttonSound.play();
            } else {
                System.err.println("AudioClip (backgroundSound) is null.");
            }
        });
        effect.start();
        startButton.setScaleX(1.1);
        startButton.setScaleY(1.1);
    }

    @FXML
    public void howToPlayButtonEffect() {
        Thread effect = new Thread(() -> {
            if (buttonSound != null) {
                buttonSound.play();
            } else {
                System.err.println("AudioClip (backgroundSound) is null.");
            }
        });
        effect.start();
        howToPlayButton.setScaleX(1.1);
        howToPlayButton.setScaleY(1.1);
    }

    @FXML
    public void viewStoryButtonEffect() {
        Thread effect = new Thread(() -> {
            if (buttonSound != null) {
                buttonSound.play();
            } else {
                System.err.println("AudioClip (backgroundSound) is null.");
            }
        });
        effect.start();
        viewStoryButton.setScaleX(1.1);
        viewStoryButton.setScaleY(1.1);
    }

    @FXML
    public void exitButtonEffect() {
        Thread effect = new Thread(() -> {
            if (buttonSound != null) {
                buttonSound.play();
            } else {
                System.err.println("AudioClip (backgroundSound) is null.");
            }
        });
        effect.start();
        exitButton.setScaleX(1.1);
        exitButton.setScaleY(1.1);
    }

    @FXML
    private void shrinkStartButtonImage() {
        startButton.setScaleX(1.0);
        startButton.setScaleY(1.0);
    }

    @FXML
    private void shrinkHowToPlayButtonImage() {
        howToPlayButton.setScaleX(1.0);
        howToPlayButton.setScaleY(1.0);
    }

    @FXML
    private void shrinkViewStoryButtonImage() {
        viewStoryButton.setScaleX(1.0);
        viewStoryButton.setScaleY(1.0);
    }

    @FXML
    public void shrinkExitButtonImage() {
        exitButton.setScaleX(1.0);
        exitButton.setScaleY(1.0);
    }
}