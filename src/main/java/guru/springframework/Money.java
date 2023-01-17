package guru.springframework;

public abstract class Money {
    protected int amount;

    public static Money dollar(int amount) {
        return new Dollar(amount);
    }

    public static Money franc(int amount) {
        return new Franc(amount);
    }

    public abstract Money times(int multiplier);

    public boolean equals(Object object) {
        Money money = (Money) object;
        return amount == money.amount
                && this.getClass() == object.getClass();
    }
}
