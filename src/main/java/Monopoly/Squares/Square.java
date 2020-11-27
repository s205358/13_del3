package Monopoly.Squares;

public abstract class Square {
    private String name;

    public Square(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Square{" +
                "name='" + name + '\'' +
                '}';
    }
}
