package item.card;

import player.Player;

public class SuperDamageCard extends BaseCard {

    public SuperDamageCard() {
        super("SUPERDAMAGE");
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
