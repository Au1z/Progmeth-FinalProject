package item.card;

import player.Player;

public class DamageCard extends BaseCard {

    public DamageCard() {
        super("DAMAGE");
    }

    @Override
    public void activate(Player player) {
        player.setHp(player.getHp() - 1);
    }

    @Override
    public String effect() {
        return "Damage card: It will decrease player's health by 1.";
    }
}
