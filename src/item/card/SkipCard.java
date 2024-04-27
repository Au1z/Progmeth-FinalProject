package item.card;

import item.Player;

public class SkipCard extends BaseCard implements Activatable { // skip turn of player

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
