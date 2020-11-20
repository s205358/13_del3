package Monopoly;

public class Player {
    private String name;
    private Bank bank;
    private Piece piece;
    private boolean paidBail = false;
    private boolean exCon = false;

    public Player(String name, int balance, int location) {
        this.name = name;
        this.bank = new Bank(balance);
        this.piece = new Piece(location);
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

    public boolean isExCon() {
        return exCon;
    }

    public void setExCon(boolean exCon) {
        this.exCon = exCon;
    }

    public boolean isPaidBail() {
        return paidBail;
    }

    public void setPaidBail(boolean paidBail) {
        this.paidBail = paidBail;
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