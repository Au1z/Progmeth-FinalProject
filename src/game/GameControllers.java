package game;

import item.card.BaseCard;
import item.area.Area;
import player.Player;
import item.Dice;
import utils.AllCards;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameControllers {
    public static void play(Player player, ArrayList<Area> areas) {
        Dice dice1 = new Dice();
        dice1.randomFaceValue();
        Dice dice2 = new Dice();
        dice2.randomFaceValue();

        System.out.println("<< " + player.getName() + " Turn >>");
        System.out.println("Now position is " + player.getPosition());
        System.out.println("Dice1 is " + dice1.getFaceValue() + " and Dice2 is " + dice2.getFaceValue());

        int totalMove = dice1.getFaceValue() + dice2.getFaceValue();
        player.setPosition(player.getPosition() + totalMove);

        System.out.println("Player move " + totalMove + " position");
        System.out.println("Now position is " + player.getPosition());

        if (player.getPosition() == 0 || player.getPosition() == 5 || player.getPosition() == 10 || player.getPosition() == 15) {
            System.out.println("Draw a card!");

            ArrayList<BaseCard> allCards = AllCards.getAllCards();
            Random random = new Random();
            BaseCard drawnCard = allCards.get(random.nextInt(allCards.size()));

            System.out.println("You draw a " + drawnCard.getName() + " card.");
            System.out.println(drawnCard.effect());
            drawnCard.activate(player);
        } else {
            System.out.println("Current health is " + player.getHp());
            showAreaOwner(areas);
            existArea(player, areas);
        }

        System.out.println("Now player health is " + player.getHp());
        System.out.println(player.getName() + ": player Health " + player.getHp() + " Position " + player.getPosition());
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        System.out.println("========================");
    }

    private static void existArea(Player player, ArrayList<Area> areas) {

        if (areas.get(player.getPosition()).getOwner().getName().isEmpty()) {
            System.out.println("Do you want to buy this Area? (Y/N)");
            Scanner scanner = new Scanner(System.in);
            String ans = scanner.nextLine();
            switch (ans) {
                case "Y":
                    if (player.getHp() - 1 <= 0) {
                        System.out.println("Cannot buy this area");
                    } else {
                        System.out.println("You lose 1 hp for buy this area");
                        player.setHp(player.getHp() - 1);
                        areas.get(player.getPosition()).setLevel(1);
                        areas.get(player.getPosition()).setOwner(player);
                    }
                    break;
                case "N":
                    break;
            }
        } else if (areas.get(player.getPosition()).getOwner().equals(player)) {
            System.out.println("Do you want to upgrade this area? (Y/N)");
            Scanner scanner = new Scanner(System.in);
            String ans = scanner.nextLine();
            switch (ans) {
                case "Y":
                    if (player.getHp() - areas.get(player.getPosition()).getLevel() <= 0) {
                        System.out.println("Cannot upgrade this area");
                    } else {
                        System.out.println("You lose 1 hp for upgrade this area");
                        areas.get(player.getPosition()).setLevel(areas.get(player.getPosition()).getLevel() + 1);
                        player.setHp(player.getHp() - 1);
                    }
                    break;
                case "N":
                    break;
            }
        } else {
            System.out.println("You lose " + areas.get(player.getPosition()).getLevel() + " hp");
            player.setHp(player.getHp() - areas.get(player.getPosition()).getLevel());
        }
    }

    public static void showAreaOwner(ArrayList<Area> areas){
        System.out.println("Do you want to see area? (Y/N)");
        Scanner scanner = new Scanner(System.in);
        String result = scanner.nextLine();

        switch (result) {
            case "Y":
                for (int i = 0; i < areas.size(); i++) {
                    if (i != 0 && i != 5 && i != 10 && i != 15) {
                        System.out.println("Area " + i + ": " + areas.get(i).getLevel() + " " + areas.get(i).getOwner().getName());
                    } else{
                        System.out.println("Area " + i + ": Event Area!");
                    }
                }
            case "N":
                break;
        }
    }

}