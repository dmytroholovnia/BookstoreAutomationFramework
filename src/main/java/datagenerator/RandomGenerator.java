package datagenerator;

import java.util.Random;

public class RandomGenerator {

    public static int getNumber() {
        return new Random().nextInt(1, 1000);
    }

}
