package game;

import item.Area;
import item.Player;
import item.card.*;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import utils.AllCards;
import utils.Sound;

import java.util.ArrayList;
import java.util.Random;

public class GameControllers {
    public static boolean isCardPosition(int position) {
        for (int i = 0; i < Config.DrawCardPosition.length; i++) {
            if (position == Config.DrawCardPosition[i]) {
                return true;
            }
        }
        return false;
    }

    public static boolean isBuyAblePosition(int position) {
        for (int i = 0; i < Config.NotBuyAblePosition.length; i++) {
            if (position == Config.NotBuyAblePosition[i]) {
                return false;
            }
        }
        return true;
    }

    public static void movePlayer(Player player, int sumOfDices, ImageView playerImage) {
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
    }

    public static void checkArea(Player player, Player otherPlayer, TextField hpPlayer, ArrayList<Area> areas, TextField descriptionText, AudioClip crySound) {
        if (areas.get(player.getPosition()).getOwner().getName().equals(otherPlayer.getName())) {
            player.setHp(player.getHp() - areas.get(player.getPosition()).getLevel());
            descriptionText.setText(player.getName()+" lose " + areas.get(player.getPosition()).getLevel() + " hp");
            hpPlayer.setText(String.valueOf(player.getHp()));
            Sound.changeBackgroundSound(null, crySound);
        }
    }

    public static void openCard(Player player, TextField hpPlayer, ImageView cardImage, TextField descriptionText, ImageView pickUpImage) {
        ArrayList<BaseCard> allCards = AllCards.getAllCards();
        Random random = new Random();
        BaseCard drawnCard = allCards.get(random.nextInt(allCards.size()));
        drawnCard.activate(player);
        cardImage.setImage(drawnCard.getImage());
        hpPlayer.setText(String.valueOf(player.getHp()));
        descriptionText.setText(drawnCard.effect());
        pickUpImage.setVisible(false);
    }

    public static void buyArea(Player player, TextField hpPlayer, Color color, AnchorPane[] areaPanes, ArrayList<Area> areas, AudioClip hurtSound) {
        if (GameControllers.isBuyAblePosition(player.getPosition()) && areas.get(player.getPosition()).canBuy()) {
            areaPanes[player.getPosition()].setBackground(new Background(new BackgroundFill(color, null, null)));
            areas.get(player.getPosition()).setOwned(true);
            areas.get(player.getPosition()).setOwner(player);
            areas.get(player.getPosition()).setLevel(areas.get(player.getPosition()).getLevel() + 1);
            player.setHp(player.getHp() - 1);
            hpPlayer.setText(String.valueOf(player.getHp()));
            Sound.changeBackgroundSound(null, hurtSound);
        }
    }

    public static void upgradeArea(Player player, TextField hpPlayer, AnchorPane[] areaPanes, ArrayList<Area> areas, AudioClip hurtSound) {
        if (GameControllers.isBuyAblePosition(player.getPosition()) && areas.get(player.getPosition()).getOwner().getName().equals(player.getName())) {
            Color currentColor = ((Color) areaPanes[player.getPosition()].getBackground().getFills().get(0).getFill());
            Color newColor = currentColor.deriveColor(0, 1, Config.DarkenFactor, 1); // Darken the color slightly
            areaPanes[player.getPosition()].setBackground(new Background(new BackgroundFill(newColor, null, null)));
            areas.get(player.getPosition()).setLevel(areas.get(player.getPosition()).getLevel() + 1);
            player.setHp(player.getHp() - 1);
            hpPlayer.setText(String.valueOf(player.getHp()));
            Sound.changeBackgroundSound(null, hurtSound);
        }
    }
}