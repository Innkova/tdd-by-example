package guru.springframework;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MoneyTest {
    @Test
    void testMultiplication() {
        Money fiveDollars = Money.dollar(5);
        assertEquals(Money.dollar(10), fiveDollars.times(2));
        assertEquals(Money.dollar(15), fiveDollars.times(3));

        Money fiveGel = Money.gel(5);
        assertEquals(Money.gel(10), fiveGel.times(2));
        assertEquals(Money.gel(15), fiveGel.times(3));
    }

    @Test
    void testEquality() {
        assertEquals(Money.dollar(5), Money.dollar(5));
        assertEquals(Money.gel(5), Money.gel(5));

        assertNotEquals(Money.dollar(5), Money.dollar(7));
        assertNotEquals(Money.gel(5), Money.gel(7));
        assertNotEquals(Money.dollar(5), Money.gel(5));
    }

    @Test
    void testCurrency() {
        assertEquals("USD", Money.dollar(1).currency());
        assertEquals("GEL", Money.gel(1).currency());
    }

    @Test
    void testSimpleAddition() {
        Money five = Money.dollar(5);
        Expression sum = five.plus(five);
        Bank bank = new Bank();
        Money reduced = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(10), reduced);
    }

    @Test
    void testPlusReturnsSum() {
        Money five = Money.dollar(5);
        Expression result = five.plus(five);
        Sum sum = (Sum) result;
        assertEquals(five, sum.augmend);
        assertEquals(five, sum.addmend);
    }

    @Test
    void testReduceSum() {
        Expression sum = new Sum(Money.dollar(3), Money.dollar(4));
        Bank bank = new Bank();
        Money result = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(7), result);
    }

    @Test
    void testReduceMoney() {
        Bank bank = new Bank();
        Money result = bank.reduce(Money.dollar(1), "USD");
        assertEquals(Money.dollar(1), result);

    }

    @Test
    void testReduceMoneyDifferentCurrency() {
        Bank bank = new Bank();
        bank.addRate("GEL", "USD", 2.6526);
        Money result = bank.reduce(Money.gel(26.526), "USD");
        assertEquals(Money.dollar(10), result);
    }

    @Test
    void testIdentityRate() {
        assertEquals(1.0, new Bank().rate("USD", "USD"));
        assertEquals(1.0, new Bank().rate("GEL", "GEL"));
    }

    @Test
    void testMixedAddition() {
        Expression fiveBucks = Money.dollar(5);
        Expression tenFrancs = Money.gel(10);
        Bank bank = new Bank();
        bank.addRate("GEL", "USD", 2);
        Money result = bank.reduce(fiveBucks.plus(tenFrancs), "USD");
        assertEquals(Money.dollar(10), result);
    }

    @Test
    void testSumPlusMoney() {
        Expression fiveBucks = Money.dollar(5);
        Expression tenFrancs = Money.gel(10);
        Bank bank = new Bank();
        bank.addRate("GEL", "USD", 2);
        Expression sum = new Sum(fiveBucks, tenFrancs).plus(fiveBucks);
        Money result = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(15), result);
    }

    @Test
    void testSumTimes() {
        Expression fiveBucks = Money.dollar(5);
        Expression tenFrancs = Money.gel(10);
        Bank bank = new Bank();
        bank.addRate("GEL", "USD", 2);
        Expression sum = new Sum(fiveBucks, tenFrancs).times(2);
        Money result = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(20), result);
    }
}
