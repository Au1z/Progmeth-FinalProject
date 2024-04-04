package game;

import item.Dice;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Board implements Initializable {
    public TextField P1;
    public TextField P2;
    public ImageView bg;
    public ImageView dice01;
    public ImageView dice02;
    public Button roll;
    public TextField dice;
    public ImageView chracterImage2;
    public ImageView chracterImage1;
    public ImageView area5;
    public ImageView area10;
    public ImageView area15;
    public ImageView area0;
    public ImageView player2Area0;
    public ImageView player1Area0;
    private final int totalDistance = 100;
    private final int moveIncrement = 10;


    private void movePlayer(ImageView playerArea) {
        Thread moveThread = new Thread(() -> {
            try {
                while (playerArea.getTranslateX() > -totalDistance) {
                    Thread.sleep(10);
                    playerArea.setTranslateX(playerArea.getTranslateX() - moveIncrement);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        moveThread.start();
    }

    public void rollDicesAndPutImage(ActionEvent actionEvent) throws InterruptedException {
        Dice dice1 = new Dice();
        Dice dice2 = new Dice();
        dice1.randomFaceValue();
        dice2.randomFaceValue();
        System.out.println("Dice 1: " + dice1.getFaceValue());
        System.out.println("Dice 2: " + dice2.getFaceValue());

        ArrayList<String> diceImages = new ArrayList<>();
        diceImages.addAll(Arrays.asList("one.png", "two.png", "three.png", "four.png", "five.png", "six.png"));
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 6; j++) {
                if (i == 0) {
                    if (dice1.getFaceValue() == j + 1) {
                        dice01.setImage(new Image(diceImages.get(j)));
                    }
                } else if (i == 1) {
                    if (dice2.getFaceValue() == j + 1) {
                        dice02.setImage(new Image(diceImages.get(j)));
                    }
                }
            }
        }

        movePlayer(player1Area0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bg.setImage(new Image("bg.png"));
        chracterImage1.setImage(new Image("character1.png"));
        chracterImage2.setImage(new Image("character2.png"));
        P1.setText("Player1");
        P2.setText("Player2");

        area0.setImage(new Image("cardstart.png"));
        area5.setImage(new Image("cardrandom.png"));
        area10.setImage(new Image("cardrandom.png"));
        area15.setImage(new Image("cardrandom.png"));

        player1Area0.setImage(new Image("character1mini.png"));
        player2Area0.setImage(new Image("character2mini.png"));
    }
}
