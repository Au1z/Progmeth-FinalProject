package item.area;

import player.Player;
import item.card.BaseCard;

public class CardArea extends Area {
    private BaseCard card;

    public CardArea(BaseCard card) {
        super(); // Call the superclass constructor
        this.card = card;
    }

    public BaseCard getCard() {
        return card;
    }

    public void setCard(BaseCard card) {
        this.card = card;
    }
}
