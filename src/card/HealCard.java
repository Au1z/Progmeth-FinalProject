package card;

import Game.Config;
import GameMaterial.Player;

public class HealCard extends BaseCard { // increase player hp 2

    public HealCard() {
        super("HEAL");
    }

    @Override
    public void activate(Player player) {
        player.setHp(Math.min(player.getHp() + 2, Config.PlayerStartHp)); // make max hp not more than 12
    }

    @Override
    public String effect() {
        return "Heal card: It will increase player's health by 2.";
    }


}
