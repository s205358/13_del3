package Monopoly.Cards;

import Monopoly.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RobberyTest {

    Player player = new Player("John Doe", 10,0);
    Robbery robbery = new Robbery("bla",5);

    @Test
    void update() {
        robbery.update(player);
        assertEquals(5, player.getBank().getBalance());
    }
}