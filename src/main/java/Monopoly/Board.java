package Monopoly;

import Monopoly.Squares.*;

import java.awt.*;

public class Board {
    private static Board board = new Board();
    Color
            brown = new Color(130,69,19),
            lightblue = new Color(0,191,255),
            pink = new Color(255,105,180),
            orange = new Color(255,165,0),
            red = new Color(220,20,60),
            yellow = new Color(255,215,0),
            green = new Color(50,205,50),
            darkblue = new Color(78, 78, 255);

    private Square[] squares = {
            new Go(),
            new Property("Ejendom 1", 1, brown),
            new Property("Ejendom 2", 1, brown),
            new Chance(),
            new Property("Ejendom 3", 1, lightblue),
            new Property("Ejendom 4", 1, lightblue),
            new Prison(),
            new Property("Ejendom 5", 2, pink),
            new Property("Ejendom 6", 2, pink),
            new Chance(),
            new Property("Ejendom 7", 2, orange),
            new Property("Ejendom 8", 2, orange),
            new Parking(),
            new Property("Ejendom 9", 3, red),
            new Property("Ejendom 10", 3, red),
            new Chance(),
            new Property("Ejendom 11", 3, yellow),
            new Property("Ejendom 12", 3, yellow),
            new Court(),
            new Property("Ejendom 13", 4, green),
            new Property("Ejendom 14", 4, green),
            new Chance(),
            new Property("Ejendom 15", 5, darkblue),
            new Property("Ejendom 16", 5, darkblue),
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