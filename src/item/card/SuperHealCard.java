package item.card;

import game.Config;
import player.Player;

public class SuperHealCard extends BaseCard implements Activatable{

    public SuperHealCard() {
        super("SUPERHEAL");
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