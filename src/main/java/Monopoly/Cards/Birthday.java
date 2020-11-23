package Monopoly.Cards;

import Monopoly.Player;

public class Birthday extends Card {
    private Player[] attendees;
    private int amount;

    public Birthday(String instructions, Player[] attendees, int amount) {
        super(instructions);
        this.attendees = attendees;
        this.amount = amount;
    }

    @Override
    public void update(Player player) {
        for (Player attendee: attendees) {
            attendee.pay(player, amount);
        }
    }
}