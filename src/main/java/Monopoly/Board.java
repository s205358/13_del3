package Monopoly;

import Monopoly.Squares.*;

public class Board {
    private static Board board = new Board();
    private Square[] squares = {
            new Go(),
            new Property("Property 1", 1),
            new Property("Property 2", 1),
            new Chance(),
            new Property("Property 3", 1),
            new Property("Property 4", 1),
            new Prison(),
            new Property("Property 5", 2),
            new Property("Property 6", 2),
            new Chance(),
            new Property("Property 7", 2),
            new Property("Property 8", 2),
            new Parking(),
            new Property("Property 9", 3),
            new Property("Property 10", 3),
            new Chance(),
            new Property("Property 11", 3),
            new Property("Property 12", 3),
            new Court(),
            new Property("Property 13", 4),
            new Property("Property 14", 4),
            new Chance(),
            new Property("Property 15", 5),
            new Property("Property 16", 5),
    };

    private Board() {
    }

    public static Board getInstance() {
        return board;
    }

    public Square getSquare(int i) {
        return squares[i];
    }

    public int getSize() {
        return squares.length;
    }

    public Square[] getSquares() {
        return squares;
    }

    public void setSquares(Square[] squares) {
        this.squares = squares;
    }

    @Override
    public String toString() {
        return squares.length + " squares";
    }
}