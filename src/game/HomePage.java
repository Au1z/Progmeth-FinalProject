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

public class HomePage implements Initializable {
    public ImageView startButton;
    public ImageView howToPlayButton;
    public ImageView viewStoryButton;
    public ImageView homeBackground;
    private AudioClip homeSound;
    private AudioClip buttonSound;
    private AudioClip startSound;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        startButton.setImage(new Image("image/startGameText.png"));
        howToPlayButton.setImage(new Image("image/howToPlayText.png"));
        viewStoryButton.setImage(new Image("image/viewStory.png"));
        homeBackground.setImage(new Image("image/homeBackground.jpg"));
        buttonSound = new AudioClip(getClass().getResource("/audio/buttonSound.mp3").toString());
        buttonSound.setVolume(1);
        startSound = new AudioClip(getClass().getResource("/audio/startSound.mp3").toString());
        startSound.setVolume(1);
        homeSound = new AudioClip(getClass().getResource("/audio/homeSound.mp3").toString());
        homeSound.setVolume(0.5);

        changeBackgroundSound(null, homeSound);
    }

    public void gotoBoard() {
        changeBackgroundSound(homeSound, startSound);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Board.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) startButton.getScene().getWindow();

            stage.setTitle("Castle of Bloodlines: The Monopoly of Nightmares");
            stage.setScene(new Scene(root,1200,800));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gotoHowToPlay(MouseEvent mouseEvent) {
        changeBackgroundSound(homeSound, null);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Other.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) howToPlayButton.getScene().getWindow();

            stage.setTitle("Castle of Bloodlines: The Monopoly of Nightmares");
            stage.setScene(new Scene(root, 1200, 650));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void changeBackgroundSound(AudioClip stopSound, AudioClip startSound) {
        Thread sound = new Thread(() -> {
            if (startSound != null) {
                startSound.play();
            } else {
                System.err.println("AudioClip (bgSound) is null.");
            }
        });

        sound.start();

        if (stopSound != null) {
            stopSound.stop();
        }
    }

    @FXML
    public void buttonImageEffect(MouseEvent mouseEvent) {
        Thread effect = new Thread(() -> {
            if (buttonSound != null) {
                buttonSound.play();
            } else {
                System.err.println("AudioClip (bgSound) is null.");
            }
        });
        effect.start();
        startButton.setScaleX(1.1);
        startButton.setScaleY(1.1);
    }

    @FXML
    public void howToPlayButtonEffect(MouseEvent mouseEvent) {
        Thread effect = new Thread(() -> {
            if (buttonSound != null) {
                buttonSound.play();
            } else {
                System.err.println("AudioClip (bgSound) is null.");
            }
        });
        effect.start();
        howToPlayButton.setScaleX(1.1);
        howToPlayButton.setScaleY(1.1);
    }

    @FXML
    public void viewStoryButtonEffect(MouseEvent mouseEvent) {
        Thread effect = new Thread(() -> {
            if (buttonSound != null) {
                buttonSound.play();
            } else {
                System.err.println("AudioClip (bgSound) is null.");
            }
        });
        effect.start();
        viewStoryButton.setScaleX(1.1);
        viewStoryButton.setScaleY(1.1);
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
}