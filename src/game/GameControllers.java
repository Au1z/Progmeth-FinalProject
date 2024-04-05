package game;

import item.area.Area;
import item.area.BaseArea;
import item.area.CardArea;
import item.area.UpgradeArea;
import item.card.BaseCard;
import item.card.TravelCard;
import player.Player;
import utils.AllCards;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameControllers {

    private static Scanner scanner = new Scanner(System.in);

    public static void start() {
        Player player1 = new Player("player 1");
        Player player2 = new Player("player 2");
        System.out.println("Game Start");
        ArrayList<Area> areas = new ArrayList<>();
        for (int i = 0; i < Config.NumberOfArea; i++) {
            areas.add(new BaseArea());
        }
        while (player1.getHp() != 0 && player2.getHp() != 0) {
            play(player1, areas);
            if (player1.getHp() == 0) {
                break;
            }
            play(player2, areas);
        }
        System.out.println(player1.getHp() == 0 ? "player 2 win!!" : "player 1 win!!");
    }

    private static void play(Player player, ArrayList<Area> areas) {
        System.out.println("<< " + player.getName() + " Turn >>");
        System.out.println("Now position is " + player.getPosition());

        int totalMove = rollDice();

        movePlayer(player, totalMove);

        System.out.println("Now position is " + player.getPosition());

        if (isCardArea(player.getPosition())) {
            drawCard(player, areas);
        } else {
            System.out.println("Current health is " + player.getHp());
            System.out.println("Do you want to see area? (Y/N)");
            String result = scanner.nextLine();
            switch (result) {
                case "Y":
                    showAreaOwner(areas);
                    break;
                case "N":
                    break;
            }
            existArea(player, areas);
        }

        System.out.println("Now player health is " + player.getHp());
        System.out.println(player.getName() + ": player Health " + player.getHp() + " Position " + player.getPosition());
        System.out.println("End turn");
        scanner.nextLine();
        System.out.println("==================================================");
    }

    static int rollDice() {
        Random random = new Random();
        return random.nextInt(6) + 1; // Roll a six-sided dice
    }

    private static void movePlayer(Player player, int totalMove) {
        player.setPosition(player.getPosition() + totalMove);
        System.out.println(player.getName() + " move " + totalMove + " position");
    }

    private static boolean isCardArea(int position) {
        for (int drawCardPos : Config.DrawCardPosition) {
            if (drawCardPos == position) {
                return true;
            }
        }
        return false;
    }

    private static void drawCard(Player player, ArrayList<Area> areas) {
        System.out.println("Draw a card!");
        ArrayList<BaseCard> allCards = AllCards.getAllCards();
        Random random = new Random();
        BaseCard drawnCard = allCards.get(random.nextInt(allCards.size()));
        System.out.println("You draw a " + drawnCard.getName() + " card.");
        if (drawnCard instanceof TravelCard) {
            System.out.println(drawnCard.effect());
            drawnCard.activate(player);
            existArea(player, areas);
        } else {
            System.out.println(drawnCard.effect());
            drawnCard.activate(player);
        }
    }

    private static void showAreaOwner(ArrayList<Area> areas) {
        for (int i = 0; i < areas.size(); i++) {
            if (!(areas.get(i) instanceof CardArea)) {
                UpgradeArea upgradeArea = (UpgradeArea) areas.get(i);
                System.out.println("Area " + i + ": " + upgradeArea.getLevel() + " " + upgradeArea.getOwner().getName());
            } else {
                System.out.println("Area " + i + ": Event Area!");
            }
        }
    }

    private static void existArea(Player player, ArrayList<Area> areas) {
        if (areas.get(player.getPosition()) instanceof CardArea) {
            buyArea(player, areas);
        } else {
            UpgradeArea upgradeArea = (UpgradeArea) areas.get(player.getPosition());
            if (upgradeArea.getOwner().equals(player)) {
                upgradeArea(player, upgradeArea);
            } else {
                System.out.println("You lose " + upgradeArea.getLevel() + " hp");
                player.setHp(player.getHp() - upgradeArea.getLevel());
            }
        }
    }

    private static void buyArea(Player player, ArrayList<Area> areas) {
        System.out.println("Do you want to buy Area " + player.getPosition() + "? (Y/N)");
        String ans = scanner.nextLine();
        switch (ans) {
            case "Y":
                if (player.getHp() - 1 < 0) {
                    System.out.println("Cannot buy Area " + player.getPosition());
                } else {
                    System.out.println("You lose 1 hp for buy Area " + player.getPosition());
                    player.setHp(player.getHp() - 1);
                    areas.set(player.getPosition(), new UpgradeArea());
                    areas.get(player.getPosition()).setOwner(player);
                }
                break;
            case "N":
                break;
        }
    }

    private static void upgradeArea(Player player, UpgradeArea upgradeArea) {
        System.out.println("Do you want to upgrade this area? (Y/N)");
        String ans = scanner.nextLine();
        switch (ans) {
            case "Y":
                if (player.getHp() - upgradeArea.getLevel() <= 0) {
                    System.out.println("Cannot upgrade this area");
                } else {
                    System.out.println("You lose 1 hp for upgrade this area");
                    upgradeArea.setLevel(upgradeArea.getLevel() + 1);
                    player.setHp(player.getHp() - 1);
                }
                break;
            case "N":
                break;
        }
    }
}
