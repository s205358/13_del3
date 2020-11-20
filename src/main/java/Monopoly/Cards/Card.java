package Monopoly.Cards;

public abstract class Card {
    private String instrcutions;

    public Card(String instructions) {
        this.instrcutions = instructions;
    }

    public String getInstrcutions() {
        return instrcutions;
    }

    public void setInstrcutions(String instrcutions) {
        this.instrcutions = instrcutions;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
