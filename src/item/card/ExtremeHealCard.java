package item.card;

import game.Config;
import player.Player;

public class ExtremeHealCard extends BaseCard {

    public ExtremeHealCard() {
        super("ExtremeHEAL");
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