package Monopoly.Cards;

import Monopoly.Player;

public class Goto extends Card {
    private int location;

    public Goto(String instructions, int location) {
        super(instructions);
        this.location = location;
    }

    @Override
    public void update(Player player) {
        player.getPiece().setLocation(location);
    }
}
