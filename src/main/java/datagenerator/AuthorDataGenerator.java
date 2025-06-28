package datagenerator;

import dto.AuthorDto;

public class AuthorDataGenerator extends RandomGenerator {

    public static AuthorDto generateAuthor(Integer id) {
        return AuthorDto.builder()
                .id(id)
                .idBook(RandomGenerator.getNumber())
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .build();
    }

    public static AuthorDto emptyAuthor() {
        return new AuthorDto();
    }

    public static AuthorDto noIdAuthor() {
        return AuthorDto.builder()
                .idBook(RandomGenerator.getNumber())
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .build();
    }

    public static AuthorDto noNameAuthor() {
        return AuthorDto.builder()
                .id(RandomGenerator.getNumber())
                .idBook(RandomGenerator.getNumber())
                .build();
    }

    public static AuthorDto noBookIdAuthor() {
        return AuthorDto.builder()
                .id(RandomGenerator.getNumber())
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .build();
    }
}
