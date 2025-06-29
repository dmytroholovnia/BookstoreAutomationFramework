package datagenerator;

import dto.BookDto;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class BookDataGenerator extends RandomGenerator {

    public static BookDto generateBook(Integer id) {
        return BookDto.builder()
                .id(id)
                .title(faker.book().title())
                .description(faker.book().genre())
                .pageCount(RandomGenerator.getNumber())
                .excerpt(faker.book().genre())
                .publishDate(OffsetDateTime.now(ZoneOffset.UTC))
                .build();
    }

    public static BookDto generateRandomBook() {
        return generateBook(RandomGenerator.getNumber());
    }

    public static BookDto emptyBook() {
        return new BookDto();
    }

    public static BookDto noIdBook() {
        return BookDto.builder()
                .title(faker.book().title())
                .description(faker.book().genre())
                .pageCount(RandomGenerator.getNumber())
                .excerpt(faker.book().genre())
                .publishDate(OffsetDateTime.now(ZoneOffset.UTC))
                .build();
    }

    public static BookDto noTitleBook() {
        return BookDto.builder()
                .id(RandomGenerator.getNumber())
                .description(faker.book().genre())
                .pageCount(RandomGenerator.getNumber())
                .excerpt(faker.book().genre())
                .publishDate(OffsetDateTime.now(ZoneOffset.UTC))
                .build();
    }

    public static BookDto noPageCountBookDto() {
        return BookDto.builder()
                .id(RandomGenerator.getNumber())
                .title(faker.book().title())
                .description(faker.book().genre())
                .excerpt(faker.book().genre())
                .publishDate(OffsetDateTime.now(ZoneOffset.UTC))
                .build();
    }

    public static BookDto noExcerptBookDto() {
        return BookDto.builder()
                .id(RandomGenerator.getNumber())
                .title(faker.book().title())
                .description(faker.book().genre())
                .pageCount(RandomGenerator.getNumber())
                .publishDate(OffsetDateTime.now(ZoneOffset.UTC))
                .build();
    }

    public static BookDto noPublishDateBookDto() {
        return BookDto.builder()
                .id(RandomGenerator.getNumber())
                .title(faker.book().title())
                .description(faker.book().genre())
                .pageCount(RandomGenerator.getNumber())
                .excerpt(faker.book().genre())
                .build();
    }

}