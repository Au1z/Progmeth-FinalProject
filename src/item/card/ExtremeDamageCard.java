package item.card;

import item.Player;

public class ExtremeDamageCard extends BaseCard implements Activatable{

    public ExtremeDamageCard() {
        super("EXTREMEDAMAGE");
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
