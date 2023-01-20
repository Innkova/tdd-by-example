package guru.springframework;

import java.text.DecimalFormat;
import java.util.Scanner;

public class UsdToGelConverter {

    public static void main(String[] args) {

        DecimalFormat df = new DecimalFormat("##.##");

        Scanner console = new Scanner(System.in);
        System.out.println("Введи курс из RUB в GEL");
        Double rubToGel = console.nextDouble();
        System.out.println("Введи курс из RUB в USD");
        Double rubToUsd = console.nextDouble();
        double rate = rubToUsd / rubToGel;
        System.out.println("rate:" + df.format(rate));
        Bank bank = new Bank();
        bank.addRate("GEL", "USD", rate);
        System.out.println("Введи сумму в $");
        Integer dollarAmount = console.nextInt();
        Double gelAmount = dollarAmount * rate;
        System.out.println("Вот столько тебе надо GEL: " + df.format(gelAmount));

    }
}
