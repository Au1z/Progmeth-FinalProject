package game;

import item.Dice;
import item.card.BaseCard;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import player.Player;
import item.area.Area;
import utils.AllCards;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import static game.GameControllers.*;

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
    public ImageView rollDicePics;
    public ImageView buyAreaPics;
    public ImageView pickUpPics;
    public ImageView endPics;
    public TextField textTurn;
    public TextField textPosition;
    public TextField textDescription;
    private Player player1;
    private Player player2;
    private ArrayList<Area> areas;
    private boolean isPlayer1Turn = true;
    public AnchorPane area1;
    public AnchorPane area2;
    public AnchorPane area3;
    public AnchorPane area4;
    public AnchorPane area6;
    public AnchorPane area7;
    public AnchorPane area8;
    public AnchorPane area9;
    public AnchorPane area11;
    public AnchorPane area12;
    public AnchorPane area13;
    public AnchorPane area14;
    public AnchorPane area16;
    public AnchorPane area17;
    public AnchorPane area18;
    public AnchorPane area19;
    public AnchorPane[] areaPanes;

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
        area0.setImage(new Image("vampire.png"));
        area5.setImage(new Image("event.png"));
        area10.setImage(new Image("event.png"));
        area15.setImage(new Image("event.png"));
        imagePlayer1.setImage(new Image("character1mini.png"));
        imagePlayer2.setImage(new Image("character2mini.png"));

        rollDicePics.setImage(new Image("rollDice.png"));
        buyAreaPics.setImage(new Image("buy.png"));
        pickUpPics.setImage(new Image("pickupCard.png"));
        pickUpPics.setVisible(false);
        endPics.setImage(new Image("end.png"));

        areaPanes = new AnchorPane[]{null, area1, area2, area3, area4, null, area6, area7, area8, area9, null, area11, area12, area13, area14, null, area16, area17, area18, area19};
        areas = new ArrayList<>();
        for (int i = 0; i < Config.NumberOfArea; i++) {
            areas.add(new Area());
        }

        startGameLogic(player1);
    }

    private void startGameLogic(Player currentPlayer) {
        Thread gameThread = new Thread(() -> {
            start(currentPlayer, player2, areas);
        });
        gameThread.setDaemon(true);
        gameThread.start();
    }

    private void buyTheArea(Player currentPlayer) {
        Thread gameThread = new Thread(() -> {
            existArea(currentPlayer, areas);
        });
        gameThread.setDaemon(true);
        gameThread.start();
    }

    public void rollDicesAndPutImage(MouseEvent actionEvent) {
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
                    for (int i = 0; i < 10; i++) {
                        File file1 = new File("res/dice/dice" + (dice1.getFaceValue()) + ".png");
                        dice01.setImage(new Image(file1.toURI().toString()));
                        File file2 = new File("res/dice/dice" + (dice2.getFaceValue()) + ".png");
                        dice02.setImage(new Image(file2.toURI().toString()));
                        Thread.sleep(100);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };
        thread.start();
        dice.setText(String.valueOf(dice1.getFaceValue() + dice2.getFaceValue()));
        movePlayer(isPlayer1Turn ? player1 : player2, dice1.getFaceValue() + dice2.getFaceValue(), isPlayer1Turn ? imagePlayer1 : imagePlayer2);
        startGameLogic(isPlayer1Turn ? player2 : player1);
        if (isPlayer1Turn) {
            textTurn.setText("<< " + player1.getName() + " Turn >>");
            textPosition.setText("Position : " + player1.getPosition());
            if (player1.getPosition() == 5 || player1.getPosition() == 10 || player1.getPosition() == 15) {
                pickUpPics.setVisible(true);
            }
        } else {
            textTurn.setText("<< " + player2.getName() + " Turn >>");
            textPosition.setText("Position : " + player2.getPosition());
            if (player2.getPosition() == 5 || player2.getPosition() == 10 || player2.getPosition() == 15) {
                pickUpPics.setVisible(true);
            }
        }
        if ((player1.getPosition() != 5 && player1.getPosition() != 10 && player1.getPosition() != 15) && (player2.getPosition() != 5 && player2.getPosition() != 10 && player2.getPosition() != 15)) {
            pickUpPics.setVisible(false);
        }
        hpPlayer1.setText(String.valueOf(player1.getHp()));
        hpPlayer2.setText(String.valueOf(player2.getHp()));
        isPlayer1Turn = !isPlayer1Turn;

        for(int i=0;i<19;i++){
            System.out.println("Area " + i + ": " + areas.get(i).getLevel() + " " + areas.get(i).getOwner().getName());
        }
    }

    public void movePlayer(Player player, int sumOfDices, ImageView playerImage) {
        for (int i = 0; i < sumOfDices; i++) {
            int currentPosition = player.getPosition();
            if (currentPosition >= 0 && currentPosition <= 4) {
                playerImage.setTranslateX(playerImage.getTranslateX() - 99);
            } else if (currentPosition >= 5 && currentPosition <= 9) {
                playerImage.setTranslateY(playerImage.getTranslateY() - 99);
            } else if (currentPosition >= 10 && currentPosition <= 14) {
                playerImage.setTranslateX(playerImage.getTranslateX() + 99);
            } else {
                playerImage.setTranslateY(playerImage.getTranslateY() + 99);
            }

            int newPosition = (currentPosition + 1) % 20;
            player.setPosition(newPosition);

            System.out.println("this " + areas.get(player1.getPosition()).isBuyable(player1));

        }
        System.out.println("Can buy this area : " + areas.get(player.getPosition()).isBuyable(player));
        System.out.println(player.getName());
        if (isPlayer1Turn) {
            if (areas.get(player1.getPosition()).getOwner().equals(player2)) {

            }
            else {
                System.out.println("H E R E 1");
                player1.setHp(player1.getHp() - areas.get(player1.getPosition()).getLevel());
                System.out.println(player1.getHp());
                textDescription.setText("You lose " + areas.get(player1.getPosition()).getLevel() + " hp");
                hpPlayer1.setText(String.valueOf(player1.getHp()));
            }
        }
        else if (areas.get(player2.getPosition()).isBuyable(player2)) {
            System.out.println("H E R E 2");
            if (areas.get(player1.getPosition()).isBuyable(player1)) {

            }
            else {
                System.out.println("H E R E 1");
                player2.setHp(player2.getHp() - areas.get(player2.getPosition()).getLevel());
                textDescription.setText("You lose " + areas.get(player2.getPosition()).getLevel() + " hp");
                hpPlayer2.setText(String.valueOf(player2.getHp()));
            }

        }
        System.out.println("Now position: " + player.getPosition());
        System.out.println("=====================================");
    }


    public void openCard(MouseEvent actionEvent) {
        if (player1.getPosition() == 5 || player1.getPosition() == 10 || player1.getPosition() == 15) {
            ArrayList<BaseCard> allCards = AllCards.getAllCards();
            Random random = new Random();
            BaseCard drawnCard = allCards.get(random.nextInt(allCards.size()));
            System.out.println("O P E N 1");
            System.out.println(drawnCard.getName());
            drawnCard.activate(player1);

            System.out.println(player1.getHp());
            hpPlayer1.setText(String.valueOf(player1.getHp()));
            pickUpPics.setVisible(false);
        }

        else if (player2.getPosition() == 5 || player2.getPosition() == 10 || player2.getPosition() == 15) {
            ArrayList<BaseCard> allCards = AllCards.getAllCards();
            Random random = new Random();
            BaseCard drawnCard = allCards.get(random.nextInt(allCards.size()));
            System.out.println("O P E N 2");
            System.out.println(drawnCard.getName());
            drawnCard.activate(player2);

            System.out.println(player2.getHp());
            hpPlayer2.setText(String.valueOf(player2.getHp()));
            pickUpPics.setVisible(false);
        }

    }

    public void endTurn(MouseEvent actionEvent) {
        isPlayer1Turn = !isPlayer1Turn;
    }

    public void buyArea(MouseEvent actionEvent) {
        if (!isPlayer1Turn) {
            if (!(player1.getPosition() == 0 || player1.getPosition() == 5 || player1.getPosition() == 10 || player1.getPosition() == 15)
                    && areas.get(player1.getPosition()).isBuyable(player1)) {
                buyTheArea(player1);
                areaPanes[player1.getPosition()].setBackground(new Background(new BackgroundFill(Color.ORANGE, null, null)));
                areas.get(player1.getPosition()).setOwned(true);
                areas.get(player1.getPosition()).setLevel(areas.get(player1.getPosition()).getLevel() + 1);
                player1.setHp(player1.getHp() - 1);
                hpPlayer1.setText(String.valueOf(player1.getHp()));
            }
        } else {
            if (!(player2.getPosition() == 0 || player2.getPosition() == 5 || player2.getPosition() == 10 || player2.getPosition() == 15)
                    && areas.get(player2.getPosition()).isBuyable(player2)) {
                buyTheArea(player2);
                areaPanes[player2.getPosition()].setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
                areas.get(player2.getPosition()).setOwned(true);
                areas.get(player2.getPosition()).setLevel(areas.get(player2.getPosition()).getLevel() + 1);
                player2.setHp(player2.getHp() - 1);
                hpPlayer2.setText(String.valueOf(player2.getHp()));
            }
        }
    }
}