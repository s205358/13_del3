package Monopoly;

import Monopoly.Squares.*;

public class Game {
    private boolean winner = false;
    private Player[] players;
    private Die die = new Die(6);
    private Board board = Board.getInstance();

    public Game() {
    }

    public void init() {
        players = new Player[] {
                new Player("John Doe", 20, 0),
                new Player("Jane Doe", 20, 18)
        };
    }

    public void play() {
        do {
            for (Player player: players) {
                die.roll();
                int faceValue = die.getFaceValue();

                int oldLocation = player.getPiece().getLocation();
                int newLocation = oldLocation + faceValue;

                int size = board.getSize();
                if (newLocation >  size - 1) {
                    newLocation -= size - 1;
                }

                player.movePiece(newLocation);

                Square location = board.getSquare(newLocation);
                if (location instanceof Property) {
                    Property p = (Property) location;
                    if (p.getOwner() == null) {
                        p.buy(player);
                    } else if (p.getOwner() == player) {
                        // do nothing
                    } else if (p.getOwner() != player) {
                        // Pay rent! * Missing double rent! *
                        player.pay(p.getOwner(), p.getValue());
                    }
                    // Check balance and do something if cannot pay
                } else if (location instanceof Chance) {

                } else if (location instanceof Court) {
                    player.getPiece().setLocation(6);
                    player.setExCon(true);
                } else {
                    // Error
                }

                // Has passed Go?
                if (newLocation < oldLocation) {
                    int value;
                    if (player.isExCon()) {
                        if (player.isPaidBail()) {
                            value = 0;
                        } else {
                            value = -2;
                        }
                    } else {
                        value = +2;
                    }
                    player.updateBalance(value);
                }

                System.out.println(player.getBank().getBalance());

                if (player.getBank().getBalance() == 0) {
                    winner = !winner;
                    break;
                }
            }
        } while(!winner);
    }
}