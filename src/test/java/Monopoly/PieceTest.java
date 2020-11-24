package Monopoly;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {

        Piece piece = new Piece(10);



    @Test
    void move() {
        piece.move(10);
        assertEquals(20,piece.getLocation());
    }

    @Test
    void getLocation() {
    }

    @Test
    void setLocation() {
    }

    @Test
    void testToString() {
    }
}