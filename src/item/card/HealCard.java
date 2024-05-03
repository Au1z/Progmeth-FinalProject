package item.card;

import game.Config;
import item.Player;
import javafx.scene.image.Image;

public class HealCard extends BaseCard implements Activatable {

    public HealCard() {
        super("HEAL", Config.HealCardImage);
    }

    @Override
    public void activate(Player player) {
        player.setHp(Math.min(player.getHp() + 1, Config.PlayerStartHp));
    }

    @Override
    public String effect() {
        return "Heal card: It will increase player's health by 1.";
    }


}
