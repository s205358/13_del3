package Monopoly;

import Monopoly.Cards.*;
import Monopoly.Squares.*;
import gui_fields.*;
import gui_main.GUI;

import java.awt.*;
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
    private Deck deck = new Deck();

    public Game() {
    }

    public void init() {
        // Init fields
        Color
                brown = new Color(130,69,19),
                lightblue = new Color(0,191,255),
                pink = new Color(255,105,180),
                orange = new Color(255,165,0),
                red = new Color(220,20,60),
                yellow = new Color(255,215,0),
                green = new Color(50,205,50),
                darkblue = new Color(78, 78, 255);
        Square[] squares = {
                new Go(),
                new Property("Brugerbaren", 1, brown),
                new Property("Pizzariaet", 1, brown),
                new Chance(),
                new Property("Slikbutikken", 1, lightblue),
                new Property("Iskiosken", 1, lightblue),
                new Prison(),
                new Property("Museet", 2, pink),
                new Property("Biblioteket", 2, pink),
                new Chance(),
                new Property("Skateparken", 2, orange),
                new Property("Swimmingpoolen", 2, orange),
                new Parking(),
                new Property("Spillehallen", 3, red),
                new Property("Biografen", 3, red),
                new Chance(),
                new Property("Legetøjsbutikken", 3, yellow),
                new Property("Dyrehandlen", 3, yellow),
                new Court(),
                new Property("Bowlinghallen", 4, green),
                new Property("Zoo", 4, green),
                new Chance(),
                new Property("Vandlandet", 5, darkblue),
                new Property("Strandpromenaden", 5, darkblue),
        };
        board.setSquares(squares);

        int size = board.getSize();
        fields = new GUI_Field[size];
        for (int i = 0; i < size; i++) {
            Square square = board.getSquare(i);
            if (square instanceof Property) {
                Property property = (Property) square;
                fields[i] = new GUI_Street(property.getName(), "$" + property.getValue(), "", "", property.getColor(), BLACK);
            } else if (square instanceof Chance) {
                fields[i] = new GUI_Chance();
            } else {
                fields[i] = new GUI_Street(square.getName(), "", "", "", WHITE, BLACK);
            }
        }

        gui = new GUI(fields);

        // Init players
        int num = chooseNumOfPlayers();
        players = new Player[num];

        int balance;
        switch(num) {
            case 2:
                balance = 20;
                break;
            case 3:
                balance = 18;
                break;
            case 4:
                balance = 16;
                break;
            default:
                balance = 0;
        }

        for (int i = 0; i < players.length; i++) {
            String name = addPlayer(i, balance);
            players[i] = new Player(name, balance, 0);
        }

        // Init cards
        Card[] cards = {
                new Birthday("Det er din fødselsdag! Alle giver dig $1. Tillykke med fødselsdagen!", players, 1), // check if this updates the acutal players balance...
                new Jail("Du løslades uden omkostninger. Behold dette kort indtil du får brug for det."),
                new Lottery("Du har lavet alle dine lektier. Modtag $2 fra banken.", 2),
                new Robbery("Du har spist for meget slik. Betal $2 til banken.", 2),
                new Move("Ryk op til 5 felter frem.", 5),
                new Goto("Ryk frem til Strandpromenaden.",23),
        };
        deck.setCards(cards);
    }

    // Refactor later
    public void play() {
        Scanner sc = new Scanner(System.in);

        // Game loop algorithm
        do {
            for (int i = 0; i < players.length; i++) {
                // Has player been to prison?
                if (players[i].isConvicted()) {
                    int value = -1;
                    if (players[i].hasBail()) {
                        value = 0;
                        players[i].setBail(false);
                        gui.showMessage(players[i].getName() + " brugte et \"Du løslades uden omkostninger\"-kort.");
                    }
                    players[i].setConvicted(false);
                    forcedSale(players[i], value);
                    players[i].updateBalance(value);
                    gui.showMessage(players[i].getName() + " betalte " + value + " i kaution.");
                    gui_players[i].setBalance(players[i].getBank().getBalance());
                }

                // Role die
                boolean click = gui.getUserLeftButtonPressed("Det er " +
                        players[i].getName() + "'s tur.", "Kast", "Slut"
                );

                if (click) {
                    die.roll();
                } else {
                    winner = !winner;
                    break;
                }

                int faceValue = die.getFaceValue();
                gui.setDie(faceValue);

                int oldLocation = players[i].getPiece().getLocation();
                players[i].getPiece().move(faceValue);
                int newLocation = players[i].getPiece().getLocation();

                gui.getFields()[oldLocation].setCar(gui_players[i], false);
                gui.getFields()[newLocation].setCar(gui_players[i], true);

                // Has player passed go?
                if (newLocation < oldLocation) {
                    players[i].updateBalance(2);
                    gui.showMessage(players[i].getName() + "har passeret Start og modtager $2!");
                    gui_players[i].setBalance(players[i].getBank().getBalance());
                }

                Square location;

                location = board.getSquare(newLocation);
                if (location instanceof Chance) {
                    // Landed on chance?
                    Card card = deck.getRandom();
                    gui.showMessage(players[i].getName() + " er landet på et chancefelt");
                    gui.displayChanceCard(card.getInstrcutions());
                    card.update(players[i]);
                    gui.getFields()[newLocation].setCar(gui_players[i], false);
                    gui.getFields()[players[i].getPiece().getLocation()].setCar(gui_players[i], true);
                    newLocation = players[i].getPiece().getLocation();
                }

                // Check location
                location = board.getSquare(newLocation);
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
                        gui_property.setOwnerName(gui_players[i].getName());
                    } else if (property.getOwner() != players[i]) {
                        // Pay rent
                        // Forced Sale: Sell property to pay rent
                        forcedSale(players[i], property.getValue());
                        players[i].pay(property.getOwner(), property.getValue());
                        // Doesn't update owners balance before his/hers turn...
                    } else {
                        // Do nothing
                    }

                    // Update display
                    gui.getFields()[newLocation] = gui_property;
                } else if (location instanceof Court) {
                    // Landed in court?
                    // Move to prison
                    int prison = 6;
                    players[i].getPiece().setLocation(prison);
                    players[i].setConvicted(true);
                    // Update display
                    gui.showMessage(players[i].getName() + " har modtaget en fængselsdom.");
                    gui.getFields()[newLocation].setCar(gui_players[i], false);
                    gui.getFields()[prison].setCar(gui_players[i], true);
                }

                // Update balance
                gui_players[i].setBalance(players[i].getBank().getBalance());

                // Winner?
                if (players[i].getBank().getBalance() == 0 && !players[i].hasProperties()) {
                    winner = !winner;
                    gui.showMessage("Spillet er slut! " + players[i].getName() + " er gået bankerot.");
                    break;
                }
            }
        } while(!winner);
        sc.close();

        // Determine Winner
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
            gui.showMessage("Vinderen er: " + winner.getName());
        } else {
            gui.showMessage("Det er uagjort mellem: " + winner.getName() + " og " + tie.getName());
        }
    }

    public void forcedSale(Player player, int debt) {
        while (player.forcedSale(debt)) {
            boolean valid = false;
            int n;
            do {
                gui.showMessage("Sælg en eller flere af dine ejendomme for at afbetale din gæld!\n" +
                        player.listProperties());
                n = gui.getUserInteger("Angiv nummer på den ejendom du gerne vil sælge: ") - 1;

                if (n < 0 || n >= player.numOfProperties()) {
                    gui.showMessage("Forkert ejendom. Vælg en fra listen.");
                } else {
                    valid = !valid;
                }
            } while(!valid);
            player.getProperty(n).sell();
            GUI_Street gui_property = (GUI_Street) gui.getFields()[n];
            gui_property.setOwnerName(null);
        }
    }

    public int chooseNumOfPlayers() {
        boolean valid = false;
        int n = 0;
        do {
            n = gui.getUserInteger("Angiv antal spillere [2 - 4]:", 2, 4);

            if (n < 2 || n > 4) {
                gui.showMessage("Forkert tal. Vælg et tal mellem to og fire.");
            } else {
                valid = !valid;
            }
        } while(!valid);
        gui_players = new GUI_Player[n];
        return n;
    }

    public String addPlayer(int id, int balance) {
        String name = gui.getUserString("Angiv navn på en spiller: ");
        gui_players[id] = new GUI_Player(name, balance);
        gui.addPlayer(gui_players[id]);
        gui.getFields()[0].setCar(gui_players[id], true);
        return name;
    }
}