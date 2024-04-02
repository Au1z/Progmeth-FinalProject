package Game;

import GameMaterial.Area;
import GameMaterial.Player;
import java.util.ArrayList;

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
            GameControllers.play(player1, areas);
            if (player1.getHp() == 0) {
                break;
            }
            GameControllers.play(player2, areas);
        }
        System.out.println(player1.getHp() == 0 ? "player2 win!!" : "player1 win!!");
    }
}
