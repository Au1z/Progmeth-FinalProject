package utils;

import item.card.*;

import java.util.ArrayList;

public class AllCards {
    public static ArrayList<BaseCard> getAllCards() {
        ArrayList<BaseCard> allCards = new ArrayList<>();
        allCards.add(new HealCard());
        allCards.add(new SuperHealCard());
        allCards.add(new ExtremeHealCard());
        allCards.add(new DamageCard());
        allCards.add(new SuperDamageCard());
        allCards.add(new ExtremeDamageCard());
//        allCards.add(new SkipCard());

        return allCards;
    }
}
