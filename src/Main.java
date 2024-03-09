import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        System.out.println("Game Start");
        Dice dice = new Dice();
        ArrayList<Area> areas = new ArrayList<>();
        for (int i = 0; i < Config.NumberOfArea + 1; i++) {
            areas.add(new Area());
        }
        while (player1.getHp() != 0 && player2.getHp() != 0) {
            dice.randomFaceValue();
            player1.setPosition(player1.getPosition() + dice.getFaceValue());
            System.out.println("player1: " + player1.getHp() + " position: " + player1.getPosition());
            if (areas.get(player1.getPosition()).getOwner().getName().isEmpty()) {
                areas.get(player1.getPosition()).setLevel(1);
                areas.get(player1.getPosition()).setOwner(player1);
            } else if (areas.get(player1.getPosition()).getOwner().equals(player1)) {
                areas.get(player1.getPosition()).setLevel(areas.get(player1.getPosition()).getLevel() + 1);
            } else {
                player1.setHp(player1.getHp() - areas.get(player1.getPosition()).getLevel());
            }
            System.out.println("player1: " + player1.getHp() + " position: " + player1.getPosition());
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
            dice.randomFaceValue();
            player2.setPosition(player2.getPosition() + dice.getFaceValue());
            System.out.println("player2: " + player2.getHp() + " position: " + player2.getPosition());
            if (areas.get(player2.getPosition()).getOwner().getName().isEmpty()) {
                areas.get(player2.getPosition()).setLevel(1);
                areas.get(player2.getPosition()).setOwner(player2);
            } else if (areas.get(player2.getPosition()).getOwner().equals(player2)) {
                areas.get(player2.getPosition()).setLevel(areas.get(player2.getPosition()).getLevel() + 1);
            } else {
                player2.setHp(player2.getHp() - areas.get(player2.getPosition()).getLevel());
            }
            System.out.println("player2: " + player2.getHp() + " position: " + player2.getPosition());
            scanner.nextLine();
        }
        System.out.println("Game End");
    }
}