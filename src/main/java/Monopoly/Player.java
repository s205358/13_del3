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
    public void movePiece(int sum) {
        piece.setLocation(sum);
    }

    public void updateBalance(int cash) {
        bank.updateBalance(cash);
    }

    // Getters/Setters
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

    // Stringify
    @Override
    public String toString() {
        return name + " " + bank.toString();
    }
}



