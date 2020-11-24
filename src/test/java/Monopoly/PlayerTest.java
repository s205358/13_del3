package Monopoly;

import Monopoly.Squares.Property;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.net.BindException;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player player = new Player("Test", 10, 10);
    Player player2 = new Player("Test2", 20, 20);
    @Test
    void forcedSale() {

        assertEquals(false, player.forcedSale(5));

        assertEquals(false, player.forcedSale(20));

        player.addProperty(new Property("TestProperty", 2, Color.blue));

        assertEquals(true, player.forcedSale(20));
    }

    @Test
    void listProperties() {
    }

    @Test
    void numOfProperties() {

    }

    @Test
    void hasProperties() {

    }

    @Test
    void addProperty() {

    }

    @Test
    void removeProperty() {
    }

    @Test
    void getProperty() {
    }

    @Test
    void setProperty() {
    }

    @Test
    void getProperties() {
    }

    @Test
    void setProperties() {
    }

    @Test
    void pay() {
        player.pay(player2, 10);
        assertEquals(0, player.getBank().getBalance());

        player2.pay(player, 10);
        assertEquals(10, player.getBank().getBalance());

        player.pay(player, 10);
        assertEquals(10, player.getBank().getBalance());

    }

    @Test
    void movePiece() {
    }

    @Test
    void updateBalance() {
    }

    @Test
    void isConvicted() {
    }

    @Test
    void setConvicted() {
    }

    @Test
    void hasBail() {
    }

    @Test
    void setBail() {
    }

    @Test
    void getName() {
    }

    @Test
    void setName() {
    }

    @Test
    void getBank() {
    }

    @Test
    void setBank() {
    }

    @Test
    void getPiece() {
    }

    @Test
    void setPiece() {
    }

    @Test
    void testToString() {
    }
}