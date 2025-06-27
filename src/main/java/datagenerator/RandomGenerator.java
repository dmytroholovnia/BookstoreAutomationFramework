package datagenerator;

import java.util.List;
import java.util.Random;

public class RandomGenerator {

    public static int getNumber() {
        return new Random().nextInt(1, 1000);
    }

    public <T> T getRandomElement(List<T> list) {
        return list.get(new Random().nextInt(list.size()));
    }

}
