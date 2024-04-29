package item.card;

import item.Player;
import javafx.scene.image.Image;

public class ExtremeDamageCard extends BaseCard implements Activatable{

    public ExtremeDamageCard() {
        super("EXTREMEDAMAGE", new Image("image/damage1.png"));
    }

    @Override
    public void activate(Player player) {
        player.setHp(player.getHp() - 3);
    }

    @Override
    public String effect() {
        return "ExtremeDamage card: It will decrease player's health by 3.";
    }
}
