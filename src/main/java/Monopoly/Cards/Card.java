package Monopoly.Cards;

import Monopoly.Player;

public abstract class Card {
    private String instrcutions;

    public Card(String instructions) {
        this.instrcutions = instructions;
    }

    public abstract void update(Player player);

    public String getInstrcutions() {
        return instrcutions;
    }

    public void setInstrcutions(String instrcutions) {
        this.instrcutions = instrcutions;
    }
}
