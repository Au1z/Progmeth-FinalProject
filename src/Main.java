import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        System.out.println("Game Start");
        ArrayList<Area> areas = new ArrayList<>();
        for (int i = 0; i < Config.NumberOfArea + 1; i++) {
            areas.add(new Area());
        }
        while (player1.getHp() != 0 && player2.getHp() != 0) {
            play(player1, areas);
            if (player1.getHp() == 0) {
                break;
            }
            play(player2, areas);
        }
        System.out.println(player1.getHp() == 0 ? "player2 win!!" : "player1 win!!");
    }

    public static void play(Player player, ArrayList<Area> areas) {
        Dice dice = new Dice();
        dice.randomFaceValue();
        player.setPosition(player.getPosition() + dice.getFaceValue());
        System.out.println(player.getName() + ": "  + player.getHp() + " position: " + player.getPosition());
        if (areas.get(player.getPosition()).getOwner().getName().isEmpty()) {
            areas.get(player.getPosition()).setLevel(1);
            areas.get(player.getPosition()).setOwner(player);
        } else if (areas.get(player.getPosition()).getOwner().equals(player)) {
            areas.get(player.getPosition()).setLevel(areas.get(player.getPosition()).getLevel() + 1);
        } else {
            player.setHp(player.getHp() - areas.get(player.getPosition()).getLevel());
        }
        System.out.println(player.getName() + ": " + player.getHp() + " position: " + player.getPosition());
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}