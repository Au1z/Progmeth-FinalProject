package game;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class summaryPage implements Initializable {
    public ImageView endBackground;
    public ImageView player2Win;
    public ImageView player1Win;
    public ImageView retryButton;
    public ImageView exitButton;
    private AudioClip backgroundSound;
    private AudioClip buttonSound;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        endBackground.setImage(Config.EndBackground);
        retryButton.setImage(Config.RetryText);
        exitButton.setImage(Config.ExitText);
        backgroundSound = new AudioClip(Objects.requireNonNull(getClass().getResource("/audio/summaryPageSound.mp3")).toString());
        backgroundSound.setVolume(1);
        buttonSound = new AudioClip(Objects.requireNonNull(getClass().getResource("/audio/buttonSound.mp3")).toString());
        buttonSound.setVolume(1);
        Thread soundThread = new Thread(() -> {
            if (backgroundSound != null) {
                backgroundSound.play();
            } else {
                System.err.println("AudioClip (backgroundSound) is null.");
            }
        });
        soundThread.start();
    }

    public void setPlayer1Win(Boolean check) {
        if(check){
            player1Win.setImage(Config.Player1WinImage);
            player2Win.setImage(null);
        }
        else{

            player1Win.setImage(null);
            player2Win.setImage(Config.Player2WinImage);
        }
    }

    @FXML
    public void expandImage1() {
        Thread effect = new Thread(() -> {
            if (buttonSound != null) {
                buttonSound.play();
            } else {
                System.err.println("AudioClip (backgroundSound) is null.");
            }
        });
        effect.start();
        retryButton.setScaleX(1.2);
        retryButton.setScaleY(1.2);
    }

    @FXML
    public void expandImage2() {
        Thread effect = new Thread(() -> {
            if (buttonSound != null) {
                buttonSound.play();
            } else {
                System.err.println("AudioClip (backgroundSound) is null.");
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
        backgroundSound.stop();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Board.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) retryButton.getScene().getWindow();

            stage.setTitle(Config.Title);
            stage.setScene(new Scene(root, 1200, 700));
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void exit() {
        backgroundSound.stop();
        System.exit(0);
    }
}