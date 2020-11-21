package Monopoly.Squares;

import Monopoly.Player;

public class Property extends Square {
    private int value;
    private Player owner = null;

    public Property(String name, int value) {
        super(name);
        this.value = value;
    }

    public void buy(Player buyer) {
        buyer.updateBalance(-1 * value);
        buyer.addProperty(this);
        setOwner(buyer);
    }

    public void sell() {
        owner.updateBalance(value);
        owner.removeProperty(this);
        setOwner(null);
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
}