package card;

import GameMaterial.Player;

public class DamageCard extends BaseCard { // decrease player hp 2

    public DamageCard() {
        super("DAMAGE");
    }

    @Override
    public void activate(Player player) {
        player.setHp(player.getHp() - 2);
    }

    @Override
    public String effect() {
        return "Damage card: It will decrease player's health by 2.";
    }
}
