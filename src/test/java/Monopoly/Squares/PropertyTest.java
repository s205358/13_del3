package Monopoly.Squares;

import Monopoly.Player;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class PropertyTest {

    Player playerA = new Player("A", 10, 10);
    Player playerB = new Player("B", 3, 10);


    Property propertyA = new Property("PropertyA", 5, Color.blue);
    Property propertyB = new Property("PropertyB", 3, Color.orange);



    @Test
    void buy() {
        playerB.addProperty(propertyB);

        propertyA.buy(playerA);
        assertEquals(true, playerA.hasProperties());
        assertEquals(playerA, propertyA.getOwner());
        assertEquals(propertyA, playerA.getProperty(0));
        assertEquals(5, playerA.getBank().getBalance());

        // Bug. Der sker ejerskift, men playerB får ikke penge for
        // den ejendom, som playerA har købt fra ham.
        propertyB.buy(playerA);
        assertEquals(true, playerA.hasProperties());
        assertEquals(playerA, propertyA.getOwner());
        assertEquals(propertyA, playerA.getProperty(0));
        assertEquals(2, playerA.getBank().getBalance());
        assertEquals(8, playerB.getBank().getBalance());

        playerB.getBank().setBalance(8);

        propertyA.buy(playerB);
        assertEquals(true, playerB.hasProperties());
        assertEquals(3, playerB.getBank().getBalance());
        assertEquals(playerB, propertyA.getOwner());
        assertEquals(propertyB, playerB.getProperty(0));

    }

    @Test
    void sell() {
        propertyA.setOwner(playerA);


        //Bug.
        propertyA.sell();
       assertEquals(false, playerA.hasProperties());
       assertEquals(15, playerA.getBank().getBalance());

        // fejl. NullException. Sker hvis en ejendom uden ejer prøves at sælges.
        propertyB.sell();
        assertEquals(false, playerA.hasProperties());
        assertEquals(false, playerB.hasProperties());
    }

    @Test
    void getOwner() {
    }

    @Test
    void setOwner() {
        propertyB.setOwner(playerA);
        assertEquals(playerA, propertyB.getOwner());
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