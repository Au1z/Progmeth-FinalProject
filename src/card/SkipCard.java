package card;

import GameMaterial.Player;

public class SkipCard extends BaseCard { // skip turn of player

    public SkipCard() {
        super("SKIP");
    }

    @Override
    public void activate(Player player) {
        // No effect
    }

    @Override
    public String effect() {
        return "Skip card: It has no effect.";
    }
}
