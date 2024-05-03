package item.card;

import game.Config;
import item.Player;
import javafx.scene.image.Image;

public class SuperDamageCard extends BaseCard implements Activatable {

    public SuperDamageCard() {
        super("SUPERDAMAGE", Config.SuperDamageCardImage);
    }

    @Override
    public void activate(Player player) {
        player.setHp(player.getHp() - 2);
    }

    @Override
    public String effect() {
        return "SuperDamage card: It will decrease player's health by 2.";
    }
}
