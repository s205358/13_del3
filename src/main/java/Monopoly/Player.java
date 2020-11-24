package Monopoly;

import Monopoly.Squares.Property;

import java.util.ArrayList;

public class Player {
    private String name;
    private Bank bank;
    private Piece piece;
    private ArrayList<Property> properties = new ArrayList<Property>();

    private boolean bail = false;
    private boolean convicted = false;

    public Player(String name, int balance, int location) {
        this.name = name;
        this.bank = new Bank(balance);
        this.piece = new Piece(location);
    }

    /*public void convicted() {
        int amount = 0;
        if (convicted) {
            amount = -1;
            if (bail) {
                amount = 0;
                bail = false;
            }
            convicted = false;
        }
        forcedSale(amount);
    }*/

    public boolean forcedSale(int debt) {
        debt = Math.abs(debt);
        if (this.bank.getBalance() < debt && hasProperties()) {
            return true;
        } else {
            return false;
        }
    }

    public String listProperties() {
        String list = "";
        int n = 1;
        for (Property property: properties) {
           list += (n++) + ": " + property.getName() + " for " + property.getValue() + "\n";
        }
        return list;
    }

    public int numOfProperties() {
        return properties.size();
    }

    public boolean hasProperties() {
        if (properties.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public void addProperty(Property property) {
        this.properties.add(property);
    }

    public void removeProperty(Property property) {
        int i = properties.indexOf(property);
        this.properties.remove(i);
    }

    public Property getProperty(int i) {
        return properties.get(i);
    }

    public void setProperty(int i, Property property) {
        this.properties.set(i, property);
    }

    public ArrayList<Property> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
    }

    public void pay(Player recipient, int amount){
        bank.withdraw(amount);
        recipient.getBank().deposit(amount);
    }

    public void movePiece(int location) {
        piece.setLocation(location);
    }

    public void updateBalance(int cash) {
        bank.updateBalance(cash);
    }

    public boolean isConvicted() {
        return convicted;
    }

    public void setConvicted(boolean convicted) {
        this.convicted = convicted;
    }

    public boolean hasBail() {
        return bail;
    }

    public void setBail(boolean bail) {
        this.bail = bail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    @Override
    public String toString() {
        return name + " " + bank.toString();
    }
}