package Monopoly;

public class Piece {
    private int location;

    public Piece(int location) {
        this.location = location;
    }

    public void move(int steps) {
        int destination = location + steps;
        int size = Board.getInstance().getSize();
        if (destination + 1 >  size) {
            destination -= size;
        }
        setLocation(destination);
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