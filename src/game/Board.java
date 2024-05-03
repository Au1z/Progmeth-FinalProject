package game;

import item.Dice;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import item.Player;
import item.Area;
import utils.Sound;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

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
    private final ArrayList<String> dicesImage = new ArrayList<>();

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
    }

    private void initializePlayer() {
        player1 = new Player(Config.Player1Name, Config.Player1Image, Config.MiniPlayer1Image);
        player2 = new Player(Config.Player2Name, Config.Player2Image, Config.MiniPlayer2Image);

        playerName1.setText(player1.getName());
        playerName2.setText(player2.getName());

        hpPlayer1.setText(String.valueOf(player1.getHp()));
        hpPlayer2.setText(String.valueOf(player2.getHp()));

        characterImage1.setImage(player1.getImage());
        characterImage2.setImage(player2.getImage());
    }

    private void initializeBoard() {
        boardBackground.setImage(Config.Background);

        area0.setImage(Config.VampireImage);
        area5.setImage(Config.EventImage);
        area10.setImage(Config.EventImage);
        area15.setImage(Config.EventImage);

        playerImage1.setImage(player1.getMiniImage());
        playerImage2.setImage(player2.getMiniImage());

        upgradeAreasImage.setImage(Config.UpgradeImage);
        upgradeAreasImage.setVisible(false);
        rollDiceImage.setImage(Config.RollDiceImage);
        buyAreaImage.setImage(Config.BuyImage);
        pickUpImage.setImage(Config.PickUpCardImage);
        pickUpImage.setVisible(false);
    }

    private void initializeSound() {
        backgroundSound = new AudioClip(Objects.requireNonNull(getClass().getResource("/audio/backgroundSound.mp3")).toString());
        backgroundSound.setVolume(0.4);
        backgroundSound.setCycleCount(AudioClip.INDEFINITE);
        crySound = new AudioClip(Objects.requireNonNull(getClass().getResource("/audio/crySound.mp3")).toString());
        crySound.setVolume(0.7);
        hurtSound = new AudioClip(Objects.requireNonNull(getClass().getResource("/audio/hurtSound.mp3")).toString());
        hurtSound.setVolume(1);
        cardSound = new AudioClip(Objects.requireNonNull(getClass().getResource("/audio/cardSound.wav")).toString());
        diceSound = new AudioClip(Objects.requireNonNull(getClass().getResource("/audio/diceSound.mp3")).toString());
        vampireSound = new AudioClip(Objects.requireNonNull(getClass().getResource("/audio/evilLaughSound.mp3")).toString());

        Sound.changeBackgroundSound(null, backgroundSound);
    }

    private void initializeDice() {
        dicesImage.add(Config.Dice1Path);
        dicesImage.add(Config.Dice2Path);
        dicesImage.add(Config.Dice3Path);
        dicesImage.add(Config.Dice4Path);
        dicesImage.add(Config.Dice5Path);
        dicesImage.add(Config.Dice6Path);
    }

    public void rollDices(MouseEvent actionEvent) {
        checkPlayerDied();

        clear();

        Sound.changeBackgroundSound(null, diceSound);

        if (actionEvent.getButton() == MouseButton.PRIMARY) {
            Dice dice1 = new Dice();
            Dice dice2 = new Dice();
            dice1.randomFaceValue();
            dice2.randomFaceValue();

            clear();

            rollDicesAnimation(dice1, dice2);

            totalValueOfDices.setText(String.valueOf(dice1.getFaceValue() + dice2.getFaceValue()));

            setBoard(dice1, dice2);
        }
    }

    private void checkPlayerDied() {
        if (player1.getHp() <= 0) {
            player2.setIsWin(true);
            gotoSummaryPage (false);
        }
        else if (player2.getHp() <= 0) {
            player1.setIsWin(true);
            gotoSummaryPage (true);
        }
    }

    private void clear() {
        cardImage.setVisible(false);
        upgradeAreasImage.setVisible(false);
        descriptionText.setText(null);
    }

    private void setBoard(Dice dice1, Dice dice2) {
        movePlayer(isPlayer1Turn ? player1 : player2, dice1.getFaceValue() + dice2.getFaceValue(), isPlayer1Turn ? playerImage1 : playerImage2);

        Player currentPlayer = isPlayer1Turn ? player1 : player2;
        String playerName = isPlayer1Turn ? player1.getName() : player2.getName();

        turnText.setText(playerName + " Turn");

        if (GameControllers.isCardPosition(currentPlayer.getPosition())) {
            pickUpImage.setVisible(true);
        } else if (areas.get(currentPlayer.getPosition()).getOwner().getName().equals(playerName)) {
            upgradeAreasImage.setVisible(true);
        }

        hpPlayer1.setText(String.valueOf(player1.getHp()));
        hpPlayer2.setText(String.valueOf(player2.getHp()));

        isPlayer1Turn = !isPlayer1Turn;
    }

    private void rollDicesAnimation(Dice dice1, Dice dice2) {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 6; i++) {
                diceImage1.setImage(new Image(dicesImage.get(i)));
                diceImage2.setImage(new Image(dicesImage.get(i)));
                diceImage1.setImage(new Image(dicesImage.get(dice1.getFaceValue()-1)));
                diceImage2.setImage(new Image(dicesImage.get(dice2.getFaceValue()-1)));
            }
        });
        thread.start();
    }

    public void gotoSummaryPage(boolean isPlayer1Win) {
        Sound.changeBackgroundSound(backgroundSound, null);
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
            System.out.println(e.getMessage());
        }
    }


    public void movePlayer(Player player, int sumOfDices, ImageView playerImage) {
        GameControllers.movePlayer(player, sumOfDices, playerImage);

        if (player.getPosition() == Config.VampirePosition) {
            Sound.changeBackgroundSound(null, hurtSound);
            Sound.changeBackgroundSound(null, vampireSound);

            player.setHp(Math.max(player.getHp()-1,0));
            descriptionText.setText(player.getName()+" lose " +  "1 hp for drop in area 0");
        }

        if (isPlayer1Turn) {
            GameControllers.checkArea(player1, player2, hpPlayer1, areas, descriptionText, crySound);
        } else {
            GameControllers.checkArea(player2, player1, hpPlayer2, areas, descriptionText, crySound);
        }

        pickUpImage.setVisible(false);
    }

    public void openCard() {
        cardImage.setVisible(false);

        Sound.changeBackgroundSound(null, cardSound);

        if (!isPlayer1Turn && GameControllers.isCardPosition(player1.getPosition())) {
            GameControllers.openCard(player1, hpPlayer1, cardImage, descriptionText, pickUpImage);
        }

        else if (isPlayer1Turn && GameControllers.isCardPosition(player2.getPosition())) {
            GameControllers.openCard(player2, hpPlayer2, cardImage, descriptionText, pickUpImage);
        }

        cardImage.setVisible(true);
    }

    public void buyArea() {
        if (!isPlayer1Turn) {
            GameControllers.buyArea(player1, hpPlayer1, Color.ORANGE, areaPanes, areas, hurtSound);
        } else {
            GameControllers.buyArea(player2, hpPlayer2, Color.GRAY, areaPanes, areas, hurtSound);
        }
    }

    public void upgradeArea() {
        if (!isPlayer1Turn) {
            GameControllers.upgradeArea(player1, hpPlayer1, areaPanes, areas, hurtSound);
        } else {
            GameControllers.upgradeArea(player2, hpPlayer2, areaPanes, areas, hurtSound);
        }
        upgradeAreasImage.setVisible(false);
    }
}