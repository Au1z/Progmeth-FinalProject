package game;

import item.Dice;
import item.card.*;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import player.Player;
import item.area.Area;
import utils.AllCards;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import static game.GameControllers.*;

public class Board implements Initializable {
    public TextField playerName1;
    public TextField playerName2;
    public ImageView boardBackground;
    public ImageView diceImage1;
    public ImageView diceImage2;
    public TextField totalValueOfDices;
    public ImageView characterImage1;
    public ImageView characterImage2;
    public ImageView area0;
    public ImageView area5;
    public ImageView area10;
    public ImageView area15;
    public ImageView cardImage;
    public ImageView playerImage1;
    public ImageView playerImage2;
    public TextField hpPlayer1;
    public TextField hpPlayer2;
    public ImageView upgradeAreasImage;
    public ImageView rollDiceImage;
    public ImageView buyAreaImage;
    public ImageView pickUpImage;
    public TextField turnText;
    public TextField descriptionText;
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
    private AudioClip backgroundSound;
    private AudioClip cardSound;
    private AudioClip diceSound;
    private AudioClip crySound;
    private AudioClip hurtSound;
    private AudioClip vampireSound;
    private ArrayList<String> dicesImage = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializePlayer();

        initializeBoard();

        initializeSound();

        initializeDice();

        areaPanes = new AnchorPane[]{null, area1, area2, area3, area4, null, area6, area7, area8, area9, null, area11, area12, area13, area14, null, area16, area17, area18, area19};
        areas = new ArrayList<>();
        for (int i = 0; i < Config.NumberOfArea; i++) {
            areas.add(new Area());
        }

        startGameLogic(player1);
    }

    private void initializePlayer() {
        player1 = new Player("Penney-Wise");
        player2 = new Player("Scream");

        playerName1.setText(player1.getName());
        playerName2.setText(player2.getName());

        hpPlayer1.setText(String.valueOf(player1.getHp()));
        hpPlayer2.setText(String.valueOf(player2.getHp()));

        characterImage1.setImage(new Image("image/player1.png"));
        characterImage2.setImage(new Image("image/player2.png"));
    }

    private void initializeBoard() {
        boardBackground.setImage(new Image("image/background.png"));

        area0.setImage(new Image("image/vampire.png"));
        area5.setImage(new Image("image/event.png"));
        area10.setImage(new Image("image/event.png"));
        area15.setImage(new Image("image/event.png"));

        playerImage1.setImage(new Image("image/miniPlayer1.png"));
        playerImage2.setImage(new Image("image/miniPlayer2.png"));

        upgradeAreasImage.setImage(new Image("image/upgrade.png"));
        upgradeAreasImage.setVisible(false);
        rollDiceImage.setImage(new Image("image/rollDice.png"));
        buyAreaImage.setImage(new Image("image/buy.png"));
        pickUpImage.setImage(new Image("image/pickUpCard.png"));
        pickUpImage.setVisible(false);
    }

    private void initializeSound() {
        backgroundSound = new AudioClip(getClass().getResource("/audio/backgroundSound.mp3").toString());
        backgroundSound.setVolume(0.4);
        backgroundSound.setCycleCount(AudioClip.INDEFINITE);
        crySound = new AudioClip(getClass().getResource("/audio/crySound.mp3").toString());
        crySound.setVolume(0.7);
        hurtSound = new AudioClip(getClass().getResource("/audio/hurtSound.mp3").toString());
        hurtSound.setVolume(1);
        cardSound = new AudioClip(getClass().getResource("/audio/cardSound.wav").toString());
        diceSound = new AudioClip(getClass().getResource("/audio/diceSound.mp3").toString());
        vampireSound = new AudioClip(getClass().getResource("/audio/evilLaugh.mp3").toString());

        changeBackgroundSound(null, backgroundSound);
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

    private void initializeDice() {
        dicesImage.add("image/dice1.png");
        dicesImage.add("image/dice2.png");
        dicesImage.add("image/dice3.png");
        dicesImage.add("image/dice4.png");
        dicesImage.add("image/dice5.png");
        dicesImage.add("image/dice6.png");
    }

    private void startGameLogic(Player currentPlayer) {
        Thread game = new Thread(() -> {
            start(currentPlayer, player2, areas);
        });
        game.setDaemon(true);
        game.start();
    }

    private void buyTheArea(Player currentPlayer) {
        Thread gameThread = new Thread(() -> {
            existArea(currentPlayer, areas);
        });
        gameThread.setDaemon(true);
        gameThread.start();
    }

    public void rollDices(MouseEvent actionEvent) {
        if (player1.getHp() <= 0) {
            player2.setIsWin(true);
            gotoSummaryPage (false);
        }
        else if (player2.getHp() <= 0) {
            player1.setIsWin(true);
            gotoSummaryPage (true);
        }
        cardImage.setVisible(false);
        descriptionText.setText(null);
        Thread effect = new Thread(() -> {
            if (diceSound != null) {
                diceSound.play();
            } else {
                System.err.println("AudioClip (backgroundSound) is null.");
            }
        });
        effect.start();

        if(actionEvent.getButton() == MouseButton.PRIMARY){
            Dice dice1 = new Dice();
            Dice dice2 = new Dice();
            dice1.randomFaceValue();
            dice2.randomFaceValue();

            System.out.println("Dice 1: " + dice1.getFaceValue());
            System.out.println("Dice 2: " + dice2.getFaceValue());

            descriptionText.setText(null);
            upgradeAreasImage.setVisible(false);

            Thread thread = new Thread() {
                public void run() {
                    System.out.println("thread start");
                    for (int i = 0; i < 6; i++) {
                          diceImage1.setImage(new Image(dicesImage.get(i)));
                          diceImage2.setImage(new Image(dicesImage.get(i)));
                        diceImage1.setImage(new Image(dicesImage.get(dice1.getFaceValue()-1)));
                        diceImage2.setImage(new Image(dicesImage.get(dice2.getFaceValue()-1)));
                    }
                }

            };
            thread.start();

            totalValueOfDices.setText(String.valueOf(dice1.getFaceValue() + dice2.getFaceValue()));
            movePlayer(isPlayer1Turn ? player1 : player2, dice1.getFaceValue() + dice2.getFaceValue(), isPlayer1Turn ? playerImage1 : playerImage2);
            startGameLogic(isPlayer1Turn ? player2 : player1);

            Player currentPlayer = isPlayer1Turn ? player1 : player2;
            String playerName = isPlayer1Turn ? player1.getName() : player2.getName();

            turnText.setText(playerName + " Turn");

            if (currentPlayer.getPosition() == 5 || currentPlayer.getPosition() == 10 || currentPlayer.getPosition() == 15) {
                pickUpImage.setVisible(true);
            } else if (areas.get(currentPlayer.getPosition()).getOwner().getName().equals(playerName)) {
                upgradeAreasImage.setVisible(true);
            }

            hpPlayer1.setText(String.valueOf(player1.getHp()));
            hpPlayer2.setText(String.valueOf(player2.getHp()));
            isPlayer1Turn = !isPlayer1Turn;
        }
        for (int i = 0; i < areas.size(); i++) {
            System.out.println("Area " + i + ": " + areas.get(i).getLevel() + " " + areas.get(i).getOwner().getName());
        }
    }

    public void gotoSummaryPage(boolean isPlayer1Win) {
        backgroundSound.stop();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("summaryPage.fxml"));
            Parent root = fxmlLoader.load();
            summaryPage controller = fxmlLoader.getController();

            Stage stage = (Stage) rollDiceImage.getScene().getWindow();
            stage.setTitle("Game Over");
            stage.setScene(new Scene(root,1200,600));

            controller.setPlayer1Win(isPlayer1Win);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
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


        }
        if(player.getPosition()==0){
            Thread effect = new Thread(() -> {
                if (vampireSound != null && hurtSound != null) {
                    hurtSound.play();
                    vampireSound.play();
                } else {
                    System.err.println("AudioClip (backgroundSound) is null.");
                }
            });
            effect.start();
            player.setHp(Math.max(player.getHp()-1,0));
            descriptionText.setText(player.getName()+" lose " +  "1 hp for drop in area 0");
        }

        System.out.println("Area " + player.getPosition() + ": " + areas.get(player.getPosition()).getLevel() + " " + areas.get(player.getPosition()).getOwner().getName());
        System.out.println(player.getName());

        if (isPlayer1Turn) {
            if (areas.get(player1.getPosition()).getOwner().getName().equals(player2.getName())) {
                player1.setHp(player.getHp() - areas.get(player.getPosition()).getLevel());
                System.out.println(player.getHp());
                descriptionText.setText(player1.getName()+" lose " + areas.get(player1.getPosition()).getLevel() + " hp");
                hpPlayer1.setText(String.valueOf(player1.getHp()));
                playCrySound();
            }
        }
        else{
            if (areas.get(player2.getPosition()).getOwner().getName().equals(player1.getName())) {
                player2.setHp(player2.getHp() - areas.get(player2.getPosition()).getLevel());
                descriptionText.setText(player2.getName()+" lose " + areas.get(player2.getPosition()).getLevel() + " hp");
                hpPlayer2.setText(String.valueOf(player2.getHp()));
                playCrySound();
            }
        }
        pickUpImage.setVisible(false);
        System.out.println("Now position: " + player.getPosition());
        System.out.println("=====================================");
    }

    public void playCrySound(){
        Thread soundThread = new Thread(() -> {
            if (crySound != null) {
                crySound.play();
            } else {
                System.err.println("AudioClip (crySound) is null.");
            }
        });
        soundThread.start();
    }

    public void playHurtSound(){
        Thread soundThread = new Thread(() -> {
            if (hurtSound != null) {
                hurtSound.play();
            } else {
                System.err.println("AudioClip (hurtSound) is null.");
            }
        });
        soundThread.start();
    }

    public void openCard(MouseEvent actionEvent) {
        cardImage.setVisible(false);
        Thread effect = new Thread(() -> {
            if (cardSound != null) {
                cardSound.play();
            } else {
                System.err.println("AudioClip (backgroundSound) is null.");
            }
        });
        effect.start();
        if (!isPlayer1Turn&&(player1.getPosition() == 5 || player1.getPosition() == 10 || player1.getPosition() == 15)) {
            ArrayList<BaseCard> allCards = AllCards.getAllCards();
            Random random = new Random();
            BaseCard drawnCard = allCards.get(random.nextInt(allCards.size()));
            System.out.println("O P E N 1");
            System.out.println(drawnCard.getName());
            drawnCard.activate(player1);
            if(drawnCard instanceof HealCard) cardImage.setImage(new Image("image/heal1.png"));
            else if(drawnCard instanceof SuperHealCard) cardImage.setImage(new Image("image/heal2.png"));
            else if(drawnCard instanceof ExtremeHealCard) cardImage.setImage(new Image("image/heal3.png"));
            else if(drawnCard instanceof DamageCard) cardImage.setImage(new Image("image/damage1.png"));
            else if(drawnCard instanceof SuperDamageCard) cardImage.setImage(new Image("image/damage2.png"));
            else if(drawnCard instanceof ExtremeDamageCard) cardImage.setImage(new Image("image/damage3.png"));
            else if(drawnCard instanceof SkipCard) cardImage.setImage(new Image("image/skip.png"));

            System.out.println(player1.getHp());
            hpPlayer1.setText(String.valueOf(player1.getHp()));
            descriptionText.setText(drawnCard.effect());
            pickUpImage.setVisible(false);
        }

        else if (isPlayer1Turn&&(player2.getPosition() == 5 || player2.getPosition() == 10 || player2.getPosition() == 15)) {
            ArrayList<BaseCard> allCards = AllCards.getAllCards();
            Random random = new Random();
            BaseCard drawnCard = allCards.get(random.nextInt(allCards.size()));
            System.out.println("O P E N 2");
            System.out.println(drawnCard.getName());
            drawnCard.activate(player2);
            if(drawnCard instanceof HealCard) cardImage.setImage(new Image("image/heal1.png"));
            else if(drawnCard instanceof SuperHealCard) cardImage.setImage(new Image("image/heal2.png"));
            else if(drawnCard instanceof ExtremeHealCard) cardImage.setImage(new Image("image/heal3.png"));
            else if(drawnCard instanceof DamageCard) cardImage.setImage(new Image("image/damage1.png"));
            else if(drawnCard instanceof SuperDamageCard) cardImage.setImage(new Image("image/damage2.png"));
            else if(drawnCard instanceof ExtremeDamageCard) cardImage.setImage(new Image("image/damage3.png"));
            else if(drawnCard instanceof SkipCard) cardImage.setImage(new Image("image/skip.png"));

            System.out.println(player2.getHp());
            hpPlayer2.setText(String.valueOf(player2.getHp()));
            descriptionText.setText(drawnCard.effect());
            pickUpImage.setVisible(false);
        }
        cardImage.setVisible(true);

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
                playHurtSound();
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
                playHurtSound();
            }
        }
    }

    public void upgradeArea(MouseEvent mouseEvent) {
        double darkenFactor = 0.8; // Adjust this value to control the darkness level

        if (!isPlayer1Turn) {
            if (!(player1.getPosition() == 0 || player1.getPosition() == 5 || player1.getPosition() == 10 || player1.getPosition() == 15)
                    && areas.get(player1.getPosition()).getOwner().getName().equals(player1.getName())) {
                buyTheArea(player1);
                Color currentColor = ((Color) areaPanes[player1.getPosition()].getBackground().getFills().get(0).getFill());
                Color newColor = currentColor.deriveColor(0, 1, darkenFactor, 1); // Darken the color slightly
                areaPanes[player1.getPosition()].setBackground(new Background(new BackgroundFill(newColor, null, null)));
                areas.get(player1.getPosition()).setLevel(areas.get(player1.getPosition()).getLevel() + 1);
                player1.setHp(player1.getHp() - 1);
                hpPlayer1.setText(String.valueOf(player1.getHp()));
                playHurtSound();
            }
        } else {
            if (!(player2.getPosition() == 0 || player2.getPosition() == 5 || player2.getPosition() == 10 || player2.getPosition() == 15)
                    && areas.get(player2.getPosition()).getOwner().getName().equals(player2.getName())) {
                buyTheArea(player2);
                Color currentColor = ((Color) areaPanes[player2.getPosition()].getBackground().getFills().get(0).getFill());
                Color newColor = currentColor.deriveColor(0, 1, darkenFactor, 1); // Darken the color slightly
                areaPanes[player2.getPosition()].setBackground(new Background(new BackgroundFill(newColor, null, null)));
                areas.get(player2.getPosition()).setLevel(areas.get(player2.getPosition()).getLevel() + 1);
                player2.setHp(player2.getHp() - 1);
                hpPlayer2.setText(String.valueOf(player2.getHp()));
                playHurtSound();
            }
        }
        upgradeAreasImage.setVisible(false);
    }

}