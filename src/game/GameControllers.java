package game;

import item.card.BaseCard;
import item.area.Area;
import item.card.TravelCard;
import player.Player;
import item.Dice;
import utils.AllCards;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameControllers {

    public static void start(Player player1, Player player2, ArrayList<Area> areas) {
        System.out.println("Game Start");
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

        if (isCardArea(player.getPosition())) {
            drawCard(player, areas);
        } else {
            System.out.println("Current health is " + player.getHp());
            Scanner scanner = new Scanner(System.in);
            String result = scanner.nextLine();
            existArea(player, areas);
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Now player health is " + player.getHp());
        System.out.println(player.getName() + ": player Health " + player.getHp() + " Position " + player.getPosition());
        System.out.println("End turn");
        scanner.nextLine();
        System.out.println("==================================================");
    }

    static int rollDice() {
        Dice dice1 = new Dice();
        Dice dice2 = new Dice();

        dice1.randomFaceValue();
        dice2.randomFaceValue();

        System.out.println("Dice1 is " + dice1.getFaceValue() + " and Dice2 is " + dice2.getFaceValue());

        return dice1.getFaceValue() + dice2.getFaceValue();
    }

    private static void movePlayer(Player player, int totalMove) {
        player.setPosition(player.getPosition() + totalMove);

        System.out.println(player.getName() + " move " + totalMove + " position");
    }

    private static boolean isCardArea(int position) {
        for (int drawCardPosition : Config.DrawCardPosition) {
            if (drawCardPosition == position) {
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

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        System.out.println("You draw a " + drawnCard.getName() + " card.");

        if (drawnCard instanceof TravelCard) {
            showAreaOwner(areas);
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
            if (!isCardArea(i)) {
                System.out.println("Area " + i + ": " + areas.get(i).getLevel() + " " + areas.get(i).getOwner().getName());
            } else {
                System.out.println("Area " + i + ": Event Area!");
            }
        }
    }

    public static void existArea(Player player, ArrayList<Area> areas) {
        if (areas.get(player.getPosition()).getOwner().getName().isEmpty()) {
            buyArea(player, areas);
        } else if (areas.get(player.getPosition()).getOwner().equals(player)) {
            upgradeArea(player, areas);
        } else {
            System.out.println("You lose " + areas.get(player.getPosition()).getLevel() + " hp");
            player.setHp(player.getHp() - areas.get(player.getPosition()).getLevel());
        }
    }

    public static void buyArea(Player player, ArrayList<Area> areas) {
        if (player.getHp() - 1 <= 0) {
            System.out.println("Cannot buy Area " + player.getPosition());
        } else {
            System.out.println("You lose 1 hp for buy Area " + player.getPosition());
//            player.setHp(player.getHp() - 1);
            areas.get(player.getPosition()).setLevel(1);
            areas.get(player.getPosition()).setOwner(player);
        }
    }

    private static void upgradeArea(Player player, ArrayList<Area> areas) {
        if (player.getHp() - areas.get(player.getPosition()).getLevel() <= 0) {
            System.out.println("Cannot upgrade this area");
        } else {
            System.out.println("You lose 1 hp for upgrade this area");
//            areas.get(player.getPosition()).setLevel(areas.get(player.getPosition()).getLevel() + 1);
//                   player.setHp(player.getHp() - 1);
        }
    }
}
