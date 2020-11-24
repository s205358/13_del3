package Monopoly.Cards;

import Monopoly.Player;

public class Robbery extends Card {
    private int amount;

    public Robbery(String instructions, int amount) {
        super(instructions);
        this.amount = amount;
    }

    @Override
    public void update(Player player) {
        player.getBank().withdraw(amount); }
}
