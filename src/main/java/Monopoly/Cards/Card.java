package Monopoly.Cards;

import Monopoly.Player;

public abstract class Card {
    private String instructions;

    public Card(String instructions) {
        this.instructions = instructions;
    }

    public abstract void update(Player player);

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
