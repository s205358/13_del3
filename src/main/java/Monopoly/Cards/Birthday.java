package Monopoly.Cards;

import Monopoly.Player;

import java.util.Arrays;

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

    @Override
    public String toString() {
        return "Birthday{" +
                "attendees=" + Arrays.toString(attendees) +
                ", amount=" + amount +
                '}';
    }
}
