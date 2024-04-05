package game;

import item.Dice;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import player.Player;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import static game.GameControllers.start;

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
    public Button openCard;
    public Button endTurn;
    public ImageView imagePlayer1;
    public ImageView imagePlayer2;
    public TextField hpPlayer1;
    public TextField hpPlayer2;
    public Button buyArea;

    private Player player1;
    private Player player2;
    private boolean isPlayer1Turn = true;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        player1 = new Player("player 1");
        player2 = new Player("player 2");

        P1.setText(player1.getName());
        P2.setText(player2.getName());

        hpPlayer1.setText(String.valueOf(player1.getHp()));
        hpPlayer2.setText(String.valueOf(player2.getHp()));

        bg.setImage(new Image("bg.png"));
        chracterImage1.setImage(new Image("character1.png"));
        chracterImage2.setImage(new Image("character2.png"));
        area0.setImage(new Image("cardstart.png"));
        area5.setImage(new Image("cardrandom.png"));
        area10.setImage(new Image("cardrandom.png"));
        area15.setImage(new Image("cardrandom.png"));
        imagePlayer1.setImage(new Image("character1mini.png"));
        imagePlayer2.setImage(new Image("character2mini.png"));

        startGameLogic(player1);
    }

    private void startGameLogic(Player currentPlayer) {
        Thread gameThread = new Thread(() -> {
            start(currentPlayer, player2);
        });
        gameThread.setDaemon(true);
        gameThread.start();
    }

    public void rollDicesAndPutImage(ActionEvent actionEvent) {
        Dice dice1 = new Dice();
        Dice dice2 = new Dice();
        dice1.randomFaceValue();
        dice2.randomFaceValue();
        System.out.println("Dice 1: " + dice1.getFaceValue());
        System.out.println("Dice 2: " + dice2.getFaceValue());

        Thread thread = new Thread() {
            public void run() {
                System.out.println("thread start");
                try {
                    for (int i = 0; i < 15; i++) {
                        File file1 = new File("res/dice/dice" + (dice1.getFaceValue()) + ".png");
                        dice01.setImage(new Image(file1.toURI().toString()));
                        File file2 = new File("res/dice/dice" + (dice2.getFaceValue()) + ".png");
                        dice02.setImage(new Image(file2.toURI().toString()));
                        Thread.sleep(50);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };
        dice.setText(String.valueOf(dice1.getFaceValue() + dice2.getFaceValue()));
        movePlayer(isPlayer1Turn ? player1 : player2, dice1.getFaceValue() + dice2.getFaceValue(),isPlayer1Turn ? imagePlayer1 : imagePlayer2);
        startGameLogic(isPlayer1Turn ? player2 : player1);
        isPlayer1Turn = !isPlayer1Turn;
    }

    public void movePlayer(Player player, int sumOfDices, ImageView playerImage) {
        for (int i = 0; i < sumOfDices; i++) {
            if (player.getPosition() >= 0 && player.getPosition() <= 4) {
                playerImage.setTranslateX(playerImage.getTranslateX() - 99);
            } else if (player.getPosition() >= 5 && player.getPosition() <= 9) {
                playerImage.setTranslateY(playerImage.getTranslateY() - 99);
            } else if (player.getPosition() >= 10 && player.getPosition() <= 14) {
                playerImage.setTranslateX(playerImage.getTranslateX() + 99);
            } else {
                playerImage.setTranslateY(playerImage.getTranslateY() + 99);
            }

            player.setPosition((player.getPosition() + 1) % 20);
        }
        System.out.println("Now position: " + player.getPosition());
        System.out.println("=====================================");
    }

    public void openCard(ActionEvent actionEvent) {

    }

    public void endTurn(ActionEvent actionEvent) {
//        isPlayer1Turn = !isPlayer1Turn;
    }

    public void buyArea(ActionEvent actionEvent) {

    }
}




