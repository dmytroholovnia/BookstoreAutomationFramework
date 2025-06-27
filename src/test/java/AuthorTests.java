import datagenerator.AuthorDataGenerator;
import datasetup.AuthorDataSetup;
import dto.AuthorDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.AuthorApiService;

import java.util.Comparator;
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
        var authorId = authorDataSetup.getRandomAuthor();
        AuthorDto actualAuthor = authorApiService.getAuthor(authorId);
        assertThat(actualAuthor).isNotNull();
    }

    @DisplayName("POST - create new author")
    @Test
    public void postAuthorTest() {
        int maxId = authorApiService.getAuthors().stream()
                .mapToInt(AuthorDto::getId)
                .max()
                .orElseThrow(() -> new RuntimeException("No author find"));
        AuthorDto newAuthor = AuthorDataGenerator.generateAuthor(maxId + 1);
        AuthorDto createdAuthor = authorApiService.postAuthor(newAuthor);

        List<AuthorDto> allAuthors = authorApiService.getAuthors();
        assertThat(allAuthors).contains(createdAuthor);
    }

}
