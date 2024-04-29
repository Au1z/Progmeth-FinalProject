package item.card;

import game.Config;
import item.Player;
import javafx.scene.image.Image;

public class ExtremeHealCard extends BaseCard implements Activatable {

    public ExtremeHealCard() {
        super("ExtremeHEAL", new Image("image/heal3.png"));
    }

    @Override
    public void activate(Player player) {
        player.setHp(Math.min(player.getHp() + 3, Config.PlayerStartHp));
    }

    @Override
    public String effect() {
        return "SuperHeal card: It will increase player's health by 3.";
    }


}