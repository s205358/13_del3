package Monopoly.Cards;

import Monopoly.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveTest {

    Player player = new Player("John Doe", 0, 0);

    Move move = new Move("bla", 5);

    @Test
    void update() {
        move.update(player);
        assertEquals(5, player.getPiece().getLocation());
    }
}