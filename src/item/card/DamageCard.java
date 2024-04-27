package item.card;

import player.Player;

public class DamageCard extends BaseCard implements Activatable{

    public DamageCard() {
        super("DAMAGE");
    }


    @Override
    public String effect() {
        return "Damage card: It will decrease player's health by 1.";
    }

    @Override
    public void activate(Player player) {
        player.setHp(player.getHp() - 1);
    }
}
