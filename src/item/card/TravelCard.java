package item.card;

import player.Player;
import java.util.Scanner;

public class TravelCard extends BaseCard { // choose 0-19 to go that position

    public TravelCard() {
        super("TRAVEL");
    }

    @Override
    public void activate(Player player) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a number you want to go (0-19): ");
        int newPosition = scanner.nextInt();
        player.setPosition(newPosition);
        System.out.println("Player moved to position " + newPosition);
    }

    @Override
    public String effect() {
        return "Travel card: It allows the player to travel to a different position.";
    }
}
