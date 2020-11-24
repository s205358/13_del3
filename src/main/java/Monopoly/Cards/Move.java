package Monopoly.Cards;

import Monopoly.Board;
import Monopoly.Player;

public class Move extends Card {
    private int steps;

    public Move(String instructions, int steps) {
        super(instructions);
        this.steps = steps;
    }

    @Override
    public void update(Player player) {
        player.getPiece().move(steps);
    }
}
