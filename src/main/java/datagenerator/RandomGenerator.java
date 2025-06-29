package datagenerator;

import com.github.javafaker.Faker;

import java.util.List;
import java.util.Random;

public class RandomGenerator {

    protected static final Faker faker = new Faker();

    public static int getNumber() {
        return new Random().nextInt(1, 1000);
    }

    public static char getRandomLetter() {
        Random rand = new Random();
        return (char) ('a' + rand.nextInt(26));
    }

    public static int getRandomNegativeNumber() {
        return -Math.abs((int)(Math.random() * Integer.MAX_VALUE));
    }

    public static char getRandomSpecialSymbol() {
        String symbols = "!@#$%^&*()_+-=[]{}|;:',.<>/?`~";
        int idx = (int) (Math.random() * symbols.length());
        return symbols.charAt(idx);
    }

    public static int getBigNumber() {
        return Integer.MAX_VALUE - new Random().nextInt(1000);
    }

    public <T> T getRandomElement(List<T> list) {
        return list.get(new Random().nextInt(list.size()));
    }

}
