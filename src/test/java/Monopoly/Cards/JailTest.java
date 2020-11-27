package Monopoly.Cards;

import Monopoly.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JailTest {

    Player player = new Player("John Doe",0,0);

    Jail jail = new Jail("Bla");

    @Test
    void update() {
        jail.update(player);
        assertEquals(true, player.hasBail());
    }
}