import datagenerator.AuthorDataGenerator;
import datasetup.AuthorDataSetup;
import dto.AuthorDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.AuthorApiService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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

    @DisplayName("PUT - update author")
    @Test
    public void putAuthorTest() {
        int authorId = authorDataSetup.getRandomAuthorId();
        AuthorDto authorRequestDto = AuthorDataGenerator.generateAuthor(authorId);
        AuthorDto updatedAuthorDto = authorApiService.putAuthor(authorId, authorRequestDto);

        List<AuthorDto> allAuthors = authorApiService.getAuthors();
        assertThat(allAuthors).contains(updatedAuthorDto);
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

}
