package Monopoly;

import Monopoly.Squares.*;
import gui_fields.GUI_Chance;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_fields.GUI_Street;
import gui_main.GUI;

import java.util.Scanner;

import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;

public class Game {
    private GUI gui;
    private GUI_Field[] fields;
    private GUI_Player[] gui_players;
    private boolean winner = false;
    private Player[] players;
    private Die die = new Die(6);
    private Board board = Board.getInstance();

    public Game() {
    }

    public void init() {
        // Create fields
        fields = new GUI_Field[board.getSize()];
        for (int i = 0; i < board.getSize(); i++) {
            // Add info
            if (board.getSquare(i) instanceof Property) {
                Property property = (Property) board.getSquare(i);
                fields[i] = new GUI_Street(property.getName(), "$" + property.getValue(), "", "", property.getColor(), BLACK);
            } else if (board.getSquare(i) instanceof Chance) {
                fields[i] = new GUI_Chance();
            } else {
                Square square = board.getSquare(i);
                fields[i] = new GUI_Street(square.getName(), "", "", "", WHITE, BLACK);
            }
        }

        // Init fields
        gui = new GUI(fields);

        players = new Player[] {
                new Player("John Doe", 16, 0),
                new Player("Jane Doe", 16, 0)
        };

        gui_players = new GUI_Player[players.length];
        for (int i = 0; i < players.length; i++) {
            gui_players[i] = new GUI_Player(players[i].getName(), players[i].getBank().getBalance());
            gui.addPlayer(gui_players[i]);
            gui.getFields()[0].setCar(gui_players[i], true);
        }
    }

    // Refactor later
    public void play() {
        Scanner sc = new Scanner(System.in);

        // Game loop algorithm
        do {
            for (int i = 0; i < players.length; i++) {
                // Has player been to prison?
                if (players[i].isExCon()) {
                    int value = -1;
                    if (players[i].isPaidBail()) {
                        value = 0;
                        players[i].setPaidBail(false);
                        gui.showMessage(players[i].getName() + " used a get out of jail card.");
                    }
                    players[i].setExCon(false);
                    forcedSale(players[i], value);
                    players[i].updateBalance(value);
                    gui.showMessage(players[i].getName() + " paid " + value + " in bail.");
                    gui_players[i].setBalance(players[i].getBank().getBalance());
                }

                // Role die
                boolean click = gui.getUserLeftButtonPressed(
                        players[i].getName() + " roll or give up", "Roll", "Give up"
                );
                if (click) {
                    die.roll();
                } else {
                    winner = !winner;
                    gui.showMessage(players[i].getName() + " gave up.");
                    break;
                }
                int faceValue = die.getFaceValue();
                gui.setDie(faceValue);

                // Calculate location
                int oldLocation = players[i].getPiece().getLocation();
                int newLocation = oldLocation + faceValue;
                int size = board.getSize();
                if (newLocation + 1 >  size) {
                    newLocation -= size;
                }

                // Move piece
                players[i].movePiece(newLocation);
                gui.getFields()[oldLocation].setCar(gui_players[i], false);
                gui.getFields()[newLocation].setCar(gui_players[i], true);

                // Has player passed go?
                if (newLocation < oldLocation) {
                    players[i].updateBalance(2);
                    gui.showMessage(players[i].getName() + " passed Go!");
                    gui_players[i].setBalance(players[i].getBank().getBalance());
                }

                // Check location
                Square location = board.getSquare(newLocation);
                if (location instanceof Property) {
                    // Landed on property?
                    Property property = (Property) location;
                    GUI_Street gui_property = (GUI_Street) gui.getFields()[newLocation];

                    // Who owns on the property?
                    if (property.getOwner() == null) {
                        // Buy property
                        // Forced Sale: Sell property to buy property
                        forcedSale(players[i], property.getValue());
                        property.buy(players[i]);
                        gui_property.setOwnerName(players[i].getName());
                    } else if (property.getOwner() != players[i]) {
                        // Pay rent
                        // Forced Sale: Sell property to pay rent
                        forcedSale(players[i], property.getValue());
                        players[i].pay(property.getOwner(), property.getValue());
                        gui_property.setOwnerName("");
                    } else {
                        // Do nothing
                    }

                    // Update display
                    gui.getFields()[newLocation] = gui_property;
                } else if (location instanceof Chance) {
                    // Landed on chance?

                } else if (location instanceof Court) {
                    // Landed in court?
                    // Move to prison
                    int prison = 6;
                    players[i].getPiece().setLocation(prison);
                    players[i].setExCon(true);
                    // Update display
                    gui.showMessage(players[i].getName() + " was sentenced to prison.");
                    gui.getFields()[newLocation].setCar(gui_players[i], false);
                    gui.getFields()[prison].setCar(gui_players[i], true);
                }

                // Update balance
                gui_players[i].setBalance(players[i].getBank().getBalance());

                // Winner?
                if (players[i].getBank().getBalance() == 0 && !players[i].hasProperties()) {
                    winner = !winner;
                    gui.showMessage("Game over! " + players[i].getName() + " is bankrupt");
                    break;
                }
            }
        } while(!winner);
        sc.close();

        // Determine Winner
        // Bug: If you give up, you can win.
        Player winner = null;
        Player tie = null;
        for(Player player: players) {
            if (winner == null || winner.getBank().getBalance() < player.getBank().getBalance()) {
                winner = player;
                tie = null;
            } else if (player.getBank().getBalance() == winner.getBank().getBalance()) {
                int winnerProps = 0;
                int playerProps = 0;
                for (int i = 0; i < winner.numOfProperties(); i++) {
                    winnerProps += winner.getProperty(i).getValue();
                }
                for (int i = 0; i < player.numOfProperties(); i++) {
                    playerProps += player.getProperty(i).getValue();
                }
                if (winnerProps < playerProps) {
                    winner = player;
                } else if (winnerProps == playerProps) {
                    tie = player;
                }
            }
        }

        if (winner != null && tie == null) {
            gui.showMessage("Winner is: " + winner.getName());
        } else {
            gui.showMessage("It's a tie between: " + winner.getName() + " and " + tie.getName());
        }
    }

    public void forcedSale(Player player, int debt) {
        while (player.forcedSale(debt)) {
            boolean valid = false;
            int n;
            do {
                gui.showMessage("Choose one or more properties to sell to pay your debt!\n" +
                        player.listProperties());
                n = gui.getUserInteger("Enter the number of the property you wish to sell:") - 1;

                if (n < 0 || n >= player.numOfProperties()) {
                    gui.showMessage("Invalid property. Choose one from the list.");
                } else {
                    valid = !valid;
                }
            } while(!valid);
            player.getProperty(n).sell();
        }
    }
}