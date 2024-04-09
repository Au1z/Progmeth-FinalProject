package utils;

import item.card.*;

import java.util.ArrayList;

public class AllCards {
    public static ArrayList<BaseCard> getAllCards() {
        ArrayList<BaseCard> allCards = new ArrayList<>();
        allCards.add(new HealCard());
        allCards.add(new DamageCard());
//        allCards.add(new SkipCard());
//        allCards.add(new TravelCard());
        return allCards;
    }
}
