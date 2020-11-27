package Monopoly.Cards;

import Monopoly.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BirthdayTest {

    Player player1 = new Player("John Doe", 10, 0);
    Player player2 = new Player("Jane Doe", 10, 0);
    Player player3 = new Player("John Smith", 10, 0);
    Player[] attendees = new Player[] {player1, player2, player3};
    Birthday birthday = new Birthday("Blabla",attendees,2);

    @Test
    void update() {
        birthday.update(player1);
        assertEquals(14,player1.getBank().getBalance());
        assertEquals(8,player2.getBank().getBalance());
        assertEquals(8,player3.getBank().getBalance());
    }
}