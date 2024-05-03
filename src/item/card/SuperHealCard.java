package item.card;

import game.Config;
import item.Player;
import javafx.scene.image.Image;

public class SuperHealCard extends BaseCard implements Activatable {

    public SuperHealCard() {
        super("SUPERHEAL", Config.SuperHealCardImage);
    }

    @Override
    public void activate(Player player) {
        player.setHp(Math.min(player.getHp() + 2, Config.PlayerStartHp));
    }

    @Override
    public String effect() {
        return "SuperHeal card: It will increase player's health by 2.";
    }


}