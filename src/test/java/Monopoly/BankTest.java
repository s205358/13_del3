package Monopoly;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {

    Bank bank = new Bank(20);

    @Test
    void deposit() {
        assertEquals(20, bank.getBalance());
        bank.deposit(10);
        assertEquals(30, bank.getBalance());
        bank.deposit(0);
        assertEquals(30, bank.getBalance());
        bank.deposit(-10);
        assertEquals(40, bank.getBalance());
    }

    @Test
    void withdraw() {
        assertEquals(20, bank.getBalance());
        bank.withdraw(5);
        assertEquals(15, bank.getBalance());
        bank.withdraw(0);
        assertEquals(15, bank.getBalance());
        bank.withdraw(-5);
        assertEquals(10, bank.getBalance());
        bank.withdraw(20);
        assertEquals(0, bank.getBalance());
    }

    @Test
    void updateBalance() {
        assertEquals(20, bank.getBalance());
        bank.updateBalance(10);
        assertEquals(30, bank.getBalance());
        bank.updateBalance(-10);
        assertEquals(20, bank.getBalance());
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