package datagenerator;

import java.util.List;
import java.util.stream.Stream;

public class TestDataProvider extends RandomGenerator {

    public static List<String> getInvalidParams() {
        return Stream.of(
                        getRandomLetter(),
                        " ",
                        getRandomNegativeNumber(),
                        getRandomSpecialSymbol(),
                        "<script>alert(1)</script>",
                        "1=1",
                        "null"
                )
                .map(String::valueOf)
                .toList();
    }

}
