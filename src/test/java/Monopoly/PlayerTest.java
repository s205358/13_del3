package Monopoly;

import Monopoly.Squares.Property;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.net.BindException;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player playerA = new Player("A", 5, 10);
    Player playerB = new Player("B", 3, 20);

    @Test
    void forcedSale() {

        assertEquals(false, playerA.forcedSale(5));

        assertEquals(false, playerA.forcedSale(20));

        playerA.addProperty(new Property("TestProperty", 2, Color.blue));

        assertEquals(true, playerA.forcedSale(20));
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

        playerA.pay(playerB,2);
        assertEquals(3, playerA.getBank().getBalance());
        assertEquals(5, playerB.getBank().getBalance());


        // Bug. playerB får 10 i sin bankkonto og ikke 8.
        playerA.pay(playerB,5);
        assertEquals(0, playerA.getBank().getBalance());
        assertEquals(8, playerB.getBank().getBalance());

        //Test for at se om man kan betale sig selv.
        playerB.pay(playerB, 2);
        assertEquals(10, playerB.getBank().getBalance());
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