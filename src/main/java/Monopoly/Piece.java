package Monopoly;

public class Piece {
    private int location;

    public Piece(int location) {
        this.location = location;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "located at " + location;
    }
}