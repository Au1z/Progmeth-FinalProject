package item.card;

import game.Config;
import player.Player;

public class HealCard extends BaseCard {

    public HealCard() {
        super("HEAL");
    }

    @Override
    public void activate(Player player) {
        player.setHp(Math.min(player.getHp() + 1, Config.PlayerStartHp));
    }

    @Override
    public String effect() {
        return "Heal card: It will increase player's health by 1.";
    }


}
