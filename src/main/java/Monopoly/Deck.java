package Monopoly;

import Monopoly.Cards.*;

import java.util.Arrays;

public class Deck {
    private Card[] cards;

    public Deck() {
    }

    public Card getRandom() {
        return cards[(int) (Math.random() * cards.length)];
    }

    public Card[] getCards() {
        return cards;
    }

    public void setCards(Card[] cards) {
        this.cards = cards;
    }

    @Override
    public String toString() {
        return "Deck{" +
                "cards=" + Arrays.toString(cards) +
                '}';
    }
}
