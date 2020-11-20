package Monopoly.Cards;

import Monopoly.Player;

public class Jail extends Card {
    public Jail(String instructions) {
        super(instructions);
    }

    public void getOut(Player player) {
        player.setPaidBail(true);
    }
}