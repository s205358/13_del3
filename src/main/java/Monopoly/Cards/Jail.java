package Monopoly.Cards;

import Monopoly.Player;

public class Jail extends Card {
    public Jail(String instructions) {
        super(instructions);
    }

    @Override
    public void update(Player player) {
        player.setPaidBail(true);
    }
}