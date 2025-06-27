package datagenerator;

import com.github.javafaker.Faker;
import dto.BookDto;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Random;

public class BookDataGenerator {

    private static final Faker faker = new Faker();

    public static BookDto generateRandomBook() {
        return BookDto.builder()
                .id(new Random().nextInt(5, 1000))
                .title(faker.book().title())
                .description(faker.book().genre())
                .pageCount(RandomGenerator.getNumber())
                .excerpt(faker.book().genre())
                .publishDate(OffsetDateTime.now(ZoneOffset.UTC))
                .build();
    }

}
