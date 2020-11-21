package Monopoly;

import Monopoly.Squares.*;
import gui_fields.GUI_Chance;
import gui_fields.GUI_Field;
import gui_fields.GUI_Street;
import gui_main.GUI;

import java.awt.*;
import java.util.Scanner;

import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;

public class Game {
    private GUI gui;
    GUI_Field[] fields;
    private boolean winner = false;
    private boolean exit = false;
    private Player[] players;
    private Die die = new Die(6);
    private Board board = Board.getInstance();

    Color
            brown = new Color(130,69,19),
            lightblue = new Color(0,191,255),
            pink = new Color(255,105,180),
            orange = new Color(255,165,0),
            red = new Color(220,20,60),
            yellow = new Color(255,215,0),
            green = new Color(50,205,50),
            darkblue = new Color(0,0,205);

    public Game() {
    }

    public void init() {
        // Create fields
        fields = new GUI_Field[board.getSize()];
        for (int i = 0; i < board.getSize(); i++) {
            // Add info
            if (board.getSquare(i) instanceof Property) {
                Property property = (Property) board.getSquare(i);
                fields[i] = new GUI_Street(property.getName(), "$" + property.getValue(), "", "", WHITE, BLACK);
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
    }

    // Refactor later
    public void play() {
        Scanner sc = new Scanner(System.in);

        // Game loop algorithm
        do {
            for (Player player: players) {
                /*
                boolean click = gui.getUserLeftButtonPressed(
                        player.getName() + " roll or give up", "Roll", "Give up"
                );

                 */
                // Role die
                if (true) {
                    die.roll();
                } else {
                    exit = !exit;
                    gui.showMessage(player.getName() + " gave up.");
                    break;
                }

                int faceValue = die.getFaceValue();
                gui.setDie(faceValue);

                // Calculate location
                int oldLocation = player.getPiece().getLocation();
                int newLocation = oldLocation + faceValue;
                int size = board.getSize();
                if (newLocation + 1 >  size) {
                    newLocation -= size;
                }

                // Move piece
                player.movePiece(newLocation);

                // Check location
                Square location = board.getSquare(newLocation);
                if (location instanceof Property) { // Landed on property?
                    Property property = (Property) location;
                    if (property.getOwner() == null) {
                        // Buy property
                        // Forced Sale: Sell property to buy property
                        forcedSale(player, property.getValue());
                        property.buy(player);
                    } else if (property.getOwner() != player) {
                        // Pay rent
                        // Forced Sale: Sell property to pay rent
                        forcedSale(player, property.getValue());
                        player.pay(property.getOwner(), property.getValue());
                    }
                } else if (location instanceof Chance) { // Landed on chance?

                } else if (location instanceof Court) { // Landed in court?
                    player.getPiece().setLocation(6);
                    player.setExCon(true);
                    gui.showMessage(player.getName() + " was sentenced to prison.");
                }

                // Has player passed go?
                if (newLocation < oldLocation) {
                    int value;
                    if (player.isExCon()) {
                        if (player.isPaidBail()) {
                            value = 0;
                            player.setPaidBail(false);
                        } else {
                            value = -2;
                        }
                        player.setExCon(false);
                    } else {
                        value = +2;
                    }
                    player.updateBalance(value);
                }

                // Winner?
                if (player.getBank().getBalance() == 0 && !player.hasProperties()) {
                    winner = !winner;
                    gui.showMessage("Game over! " + player.getName() + " is bankrupt");
                    break;
                }
            }
        } while(!winner && !exit);
        sc.close();
    }

    public void forcedSale(Player player, int debt) {
        while (player.forcedSale(debt)) {
            boolean valid = false;
            int n;
            do {
                gui.showMessage("Choose one or more properties to sell to pay your debt!\n" +
                        player.listProperties());
                n = gui.getUserInteger("Enter the number of the property you wish to sell:") - 1;

                if (n < 0 || n > player.numOfProperties()) {
                    gui.showMessage("Invalid property. Choose one from the list.");
                } else {
                    valid = !valid;
                }
            } while(!valid);
            player.getProperty(n).sell();
        }
    }
}