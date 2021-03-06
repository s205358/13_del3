package Monopoly.Squares;

import Monopoly.Player;

import java.awt.*;

public class Property extends Square {
    private int value;
    private Player owner = null;
    private Color color;

    public Property(String name, int value, Color color) {
        super(name);
        this.value = value;
        this.color = color;
    }

    public void buy(Player buyer) {
        if (owner == null) {
            buyer.updateBalance(-1 * value);
            buyer.addProperty(this);
            setOwner(buyer);
        } else if (buyer != owner) {
            sell();
            buyer.updateBalance(-1 * value);
            buyer.addProperty(this);
            setOwner(buyer);
        }
    }

    public void sell() {
        if (owner != null) {
            owner.updateBalance(value);
            owner.removeProperty(this);
            setOwner(null);
        }
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Property{" +
                "value=" + value +
                ", owner=" + owner +
                ", color=" + color +
                '}';
    }
}