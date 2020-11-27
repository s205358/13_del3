package Monopoly.Cards;

import Monopoly.Bank;
import Monopoly.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LotteryTest {

  Player player = new Player("John Doe", 0,0);

  Lottery lottery = new Lottery("bla",5);

    @Test
    void update() {
      lottery.update(player);
      assertEquals(5, player.getBank().getBalance());
    }
}