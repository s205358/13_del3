package Monopoly;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {

    Piece piece = new Piece(10);

    @Test
    void move() {
        assertEquals(10,piece.getLocation());
        piece.move(10);
        assertEquals(20,piece.getLocation());
        piece.move(5);
        assertEquals(1,piece.getLocation());;
        piece.move(0);
        assertEquals(1,piece.getLocation());;
        piece.move(-15);
        assertEquals(10,piece.getLocation());
    }

    @Test
    void getLocation() {
    }

    @Test
    void setLocation() {
        piece.setLocation(15);
        assertEquals(15, piece.getLocation());
    }

    @Test
    void testToString() {
    }
}