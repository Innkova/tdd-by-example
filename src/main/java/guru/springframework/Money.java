package guru.springframework;

import org.w3c.dom.ls.LSOutput;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Money implements Expression {
    protected double amount;
    protected String currency;

    public Money(double amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public static Money dollar(double amount) {
        return new Money(amount, "USD");
    }

    public static Money gel(double amount) {
        return new Money(amount, "GEL");
    }

    protected String currency() {
        return currency;
    }

    @Override
    public Expression times(double multiplier) {
        return new Money(amount * multiplier, currency);
    }

    @Override
    public Money reduce(Bank bank, String to) {
        return new Money(amount / bank.rate(currency, to), to);
    }

    @Override
    public Expression plus(Expression added) {
        return new Sum(this, added);
    }

    public boolean equals(Object object) {
        Money money = (Money) object;
        return amount == money.amount
                && currency.equals(money.currency);
    }

    @Override
    public String toString() {
        return "Money{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }
}
