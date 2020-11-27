package Monopoly.Cards;

import Monopoly.Player;
import Monopoly.Squares.Prison;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GotoTest {

    Player player = new Player("John Doe", 0, 0);
    Goto card = new Goto("bla", 6);

    @Test
    void update() {
        card.update(player);
        assertEquals(6, player.getPiece().getLocation());
    }
}