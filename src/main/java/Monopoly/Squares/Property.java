package Monopoly.Squares;

import Monopoly.Player;

public class Property extends Square {
    private int value;
    private Player owner = null;

    public Property(String name, int value) {
        super(name);
        this.value = value;
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