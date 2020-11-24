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
        int oldLocation = player.getPiece().getLocation();
        int newLocation = oldLocation + steps;
        int size = Board.getInstance().getSize();
        if (newLocation + 1 >  size) {
            newLocation -= size;
        }
        player.movePiece(newLocation);
    }
}