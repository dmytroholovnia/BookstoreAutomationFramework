package datagenerator;

import com.github.javafaker.Faker;

import java.util.List;
import java.util.Random;

public class RandomGenerator {

    protected static final Faker faker = new Faker();

    public static int getNumber() {
        return new Random().nextInt(1, 1000);
    }

    public static int getBigNumber() {
        return Integer.MAX_VALUE - new Random().nextInt(1000);
    }

    public <T> T getRandomElement(List<T> list) {
        return list.get(new Random().nextInt(list.size()));
    }

}
