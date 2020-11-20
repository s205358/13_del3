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

    public int getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(int faceValue) {
        this.faceValue = faceValue;
    }

    @Override
    public String toString() {
        return faceValue + " eye(s)";
    }
}
