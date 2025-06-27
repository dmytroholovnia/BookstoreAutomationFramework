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
}
