package Monopoly.Cards;

import Monopoly.Player;

public class Lottery extends Card {
    private int amount;

    public Lottery(String instructions, int amount) {
        super(instructions);
        this.amount = amount;
    }

    @Override
    public void update(Player player) {
        player.getBank().deposit(amount);
    }

    @Override
    public String toString() {
        return "Lottery{" +
                "amount=" + amount +
                '}';
    }
}
