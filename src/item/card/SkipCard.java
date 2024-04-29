package item.card;

import item.Player;
import javafx.scene.image.Image;

public class SkipCard extends BaseCard implements Activatable { // skip turn of player

    public SkipCard() {
        super("SKIP", new Image("image/skip.png"));
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
