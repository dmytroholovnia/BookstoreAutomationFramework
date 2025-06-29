import datagenerator.AuthorDataGenerator;
import datagenerator.RandomGenerator;
import datagenerator.TestDataProvider;
import datasetup.AuthorDataSetup;
import dto.AuthorDto;
import dto.ErrorDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import service.AuthorApiService;

import java.util.List;
import java.util.stream.Stream;

import static org.apache.http.HttpStatus.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.*;

public class AuthorTests {

    private AuthorDataSetup authorDataSetup;
    private AuthorApiService authorApiService;

    @BeforeEach
    public void setup() {
        authorApiService = new AuthorApiService();
        authorDataSetup = new AuthorDataSetup();
    }

    @DisplayName("GET - get all authors test")
    @Test
    public void getAllAuthorsTest() {
        List<AuthorDto> allAuthors = authorApiService.getAuthors();
        assertThat(allAuthors).isNotNull();
    }

    @DisplayName("GET - get author by id")
    @Test
    public void getAuthorTest() {
        var authorId = authorDataSetup.getRandomAuthorId();
        AuthorDto actualAuthor = authorApiService.getAuthor(authorId);
        assertThat(actualAuthor).isNotNull();
    }

    @DisplayName("GET - get author with non-existent id")
    @Test
    public void getNonExistentAuthorTest() {
        ErrorDto actualError = authorApiService.getAuthorExceptional(RandomGenerator.getBigNumber());
        assertThat(actualError.getStatus()).isEqualTo(SC_NOT_FOUND);
    }

    @DisplayName("GET - get author with invalid format of id")
    @ParameterizedTest(name = "Param: {0}")
    @MethodSource("getInvalidParams")
    public void getInvalidFormatAuthorIdTest(String invalidParam) {
        ErrorDto actualError = authorApiService.getAuthorExceptional(invalidParam);
        assertThat(actualError.getStatus()).isEqualTo(SC_BAD_REQUEST);
    }

    private static Stream<Arguments> getInvalidParams() {
        return TestDataProvider.getInvalidParams()
                .stream()
                .map(Arguments::of);
    }

    @DisplayName("POST - create new author")
    @Test
    public void postAuthorTest() {
        int maxAuthorId = authorApiService.getAuthors().stream()
                .mapToInt(AuthorDto::getId)
                .max()
                .orElseThrow(() -> new RuntimeException("No author find"));
        AuthorDto newAuthor = AuthorDataGenerator.generateAuthor(maxAuthorId + 1);
        AuthorDto createdAuthor = authorApiService.postAuthor(newAuthor);

        List<AuthorDto> allAuthors = authorApiService.getAuthors();
        assertThat(allAuthors).contains(createdAuthor);
    }

    @DisplayName("POST - create author with invalid payload")
    @ParameterizedTest
    @MethodSource("getInvalidAuthorPayloads")
    public void postAuthorExceptionalTest(AuthorDto invalidAuthorDto) {
        ErrorDto actualError = authorApiService.postAuthorExceptional(invalidAuthorDto);
        assertThat(actualError.getStatus()).isEqualTo(SC_BAD_REQUEST);
    }

    private static Stream<Arguments> getInvalidAuthorPayloads() {
        return Stream.of(
                of(AuthorDataGenerator.emptyAuthor()),
                of(AuthorDataGenerator.noIdAuthor()),
                of(AuthorDataGenerator.noNameAuthor()),
                of(AuthorDataGenerator.noBookIdAuthor())
        );
    }

    @DisplayName("PUT - update author")
    @Test
    public void putAuthorTest() {
        int authorId = authorDataSetup.getRandomAuthorId();
        AuthorDto authorRequestDto = AuthorDataGenerator.generateAuthor(authorId);
        AuthorDto updatedAuthorDto = authorApiService.putAuthor(authorId, authorRequestDto);

        List<AuthorDto> allAuthors = authorApiService.getAuthors();
        assertThat(allAuthors).contains(updatedAuthorDto);
    }

    @DisplayName("PUT - update author with invalid payload")
    @ParameterizedTest
    @MethodSource("getInvalidParamsAndPayloads")
    public void putAuthorExceptionalTest(String param, AuthorDto payload) {
        ErrorDto actualError = authorApiService.putAuthorExceptional(param, payload);
        assertThat(actualError.getStatus()).isEqualTo(SC_BAD_REQUEST);
    }

    private static Stream<Arguments> getInvalidParamsAndPayloads() {
        return getInvalidParams()
                .flatMap(idArg -> getInvalidAuthorPayloads()
                        .map(authorArg -> Arguments.of(idArg.get()[0], authorArg.get()[0]))
        );
    }

    @DisplayName("DELETE - delete author test")
    @Test
    public void deleteAuthorTest() {
        int authorId = authorDataSetup.getRandomAuthorId();
        authorApiService.deleteAuthor(authorId);

        List<AuthorDto> allAuthors = authorApiService.getAuthors();
        assertThat(allAuthors.stream()
                .map(AuthorDto::getId)
                .toList())
                .doesNotContain(authorId);
    }

    @DisplayName("DELETE - delete author with invalid param")
    @ParameterizedTest
    @MethodSource("getInvalidParams")
    public void deleteAuthorExceptionTest(Object authorId) {
        ErrorDto actualError = authorApiService.deleteAuthorExceptional(authorId);
        assertThat(actualError.getStatus()).isEqualTo(SC_BAD_REQUEST);
    }

}
