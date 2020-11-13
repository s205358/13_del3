package Monopoly;

public class Board {
    private Square[] squares;

    public Board(Square[] squares) {
        this.squares = squares;
    }

    public Square getSquare(int index) {
        return squares[index];
    }

    public int getLength() {
        return squares.length;
    }

    // Getters/Setters
    public Square[] getSquares() {
        return squares;
    }

    public void setSquares(Square[] squares) {
        this.squares = squares;
    }

    // Stringify
    @Override
    public String toString() {
        return squares.length + " squares";
    }
}