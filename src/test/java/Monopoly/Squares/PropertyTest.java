package Monopoly.Squares;

import Monopoly.Player;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class PropertyTest {

    Player playerA = new Player("A", 10, 10);
    Player playerB = new Player("B", 6, 10);
    Property propertyA = new Property("PropertyA", 5, Color.blue);
    Property propertyB = new Property("PropertyB", 3, Color.orange);

    @Test
    void buy() {
        propertyB.buy(playerB);
        assertEquals(null, propertyA.getOwner());
        assertEquals(5, propertyA.getValue());
        assertEquals(playerB, propertyB.getOwner());
        assertEquals(3, propertyB.getValue());
        assertEquals(10, playerA.getBank().getBalance());
        assertEquals(false, playerA.hasProperties());
        assertEquals(3, playerB.getBank().getBalance());
        assertEquals(true, playerB.hasProperties());

        propertyA.buy(playerA);
        assertEquals(true, playerA.hasProperties());
        assertEquals(playerA, propertyA.getOwner());
        assertEquals(propertyA, playerA.getProperty(0));
        assertEquals(5, playerA.getBank().getBalance());

        propertyB.buy(playerA);
        assertEquals(true, playerA.hasProperties());
        assertEquals(playerA, propertyA.getOwner());
        assertEquals(propertyA, playerA.getProperty(0));
        assertEquals(2, playerA.getBank().getBalance());
        assertEquals(6, playerB.getBank().getBalance());

        // Reset
        playerB.getBank().setBalance(9);
        propertyB.buy(playerB);

        propertyA.buy(playerB);
        assertEquals(true, playerB.hasProperties());
        assertEquals(1, playerB.getBank().getBalance());
        assertEquals(playerB, propertyA.getOwner());
        assertEquals(propertyA, playerB.getProperty(1));

        propertyA.buy(playerB);
        assertEquals(true, playerB.hasProperties());
        assertEquals(1, playerB.getBank().getBalance());
        assertEquals(playerB, propertyB.getOwner());
        assertEquals(propertyB, playerB.getProperty(0));
    }

    @Test
    void sell() {
        propertyA.buy(playerA);
        playerA.getBank().setBalance(10);
        assertEquals(10,playerA.getBank().getBalance());
        assertEquals(true,playerA.hasProperties());
        assertEquals(propertyA, playerA.getProperty(0));
        assertEquals(5, propertyA.getValue());

        propertyA.sell();
        assertEquals(false, playerA.hasProperties());
        assertEquals(15, playerA.getBank().getBalance());
        assertEquals(null, propertyA.getOwner());
        propertyA.sell();
        assertEquals(null, propertyA.getOwner());
        assertEquals(false, playerA.hasProperties());
        assertEquals(15, playerA.getBank().getBalance());
    }

    @Test
    void getOwner() {
    }

    @Test
    void setOwner() {
    }

    @Test
    void getValue() {
    }

    @Test
    void setValue() {
    }

    @Test
    void getColor() {
    }

    @Test
    void setColor() {
    }

    @Test
    void testBuy() {
    }

    @Test
    void testSell() {
    }

    @Test
    void testGetOwner() {
    }

    @Test
    void testSetOwner() {
    }

    @Test
    void testGetValue() {
    }

    @Test
    void testSetValue() {
    }

    @Test
    void testGetColor() {
    }

    @Test
    void testSetColor() {
    }
}