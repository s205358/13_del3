package Monopoly;

public class Square
{
    private String name;
    private int value;

    public Square(String name, int value)
    {
        this.name = name;
        this.value = value;
    }

    // Getters/Setters
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getValue()
    {
        return value;
    }

    public void setValue(int value)
    {
        this.value = value;
    }

    // Stringify
    @Override
    public String toString() {
        return name + ", " + value;
    }
}
