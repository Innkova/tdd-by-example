package guru.springframework;

import java.util.HashMap;

public class Bank {

    private HashMap<Pair, Double> rateMap = new HashMap<>();

    Money reduce(Expression source, String toCurrency) {
        return source.reduce(this, toCurrency);
    }

    public double rate(String from, String to) {
        if (from.equals(to)) {
            return 1.0;
        }
        return rateMap.get(new Pair(from, to));
    }

    public void addRate(String from, String to, double rate) {
        rateMap.put(new Pair(from, to), rate);
    }
}
