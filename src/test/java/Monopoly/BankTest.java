package Monopoly;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {

    Bank bank = new Bank(90);

    @Test
    void deposit() {
        bank.deposit(10);
        assertEquals(100, bank.getBalance());
        bank.deposit(-10);
        assertEquals(100, bank.getBalance());
    }

    @Test
    void withdraw() {
    }

    @Test
    void updateBalance() {
    }

    @Test
    void getBalance() {
    }

    @Test
    void setBalance() {
    }

    @Test
    void testToString() {
    }
}