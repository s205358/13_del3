package Monopoly;

public class Piece {
    private int location;

    public Piece(int location) {
        this.location = location;
    }

    // Getters/Setters
    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    // Stringify
    @Override
    public String toString() {
        return "located at " + location;
    }
}