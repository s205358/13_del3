package Monopoly;

public class Die {
    private final int MAX;
    private int faceValue;

    public Die(int max) {
        this.MAX = max;
        roll();
    }

    public void roll() {
        setFaceValue((int) (Math.random() * MAX) + 1);
    }

    // Getters/Setters
    public int getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(int faceValue) {
        this.faceValue = faceValue;
    }

    // Stringify
    @Override
    public String toString() {
        return faceValue + " eyes";
    }
}
