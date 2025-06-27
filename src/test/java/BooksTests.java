import datagenerator.BookDataGenerator;
import dto.BookDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.BookApiService;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class BooksTests {

    private BookApiService bookApiService;

    @BeforeEach
    public void setup() {
        bookApiService = new BookApiService();
    }

    @DisplayName("GET - all books test")
    @Test
    public void getBooksTest() {
        List<BookDto> actualBooks = bookApiService.getBooks();
    }

    @DisplayName("GET - book by id test")
    @Test
    public void getBookTest() {
        BookDto actualBook = bookApiService.getBook(1);
    }

    @DisplayName("POST - add book test")
    @Test
    public void addBookTest() {
        BookDto expectedBook = BookDataGenerator.generateRandomBook();
        BookDto actualBook = bookApiService.postBook(expectedBook);
        assertThat(actualBook)
                .usingRecursiveComparison()
                .isEqualTo(expectedBook);

        List<BookDto> allBooks = bookApiService.getBooks();
        assertThat(allBooks).contains(expectedBook);
    }

    @DisplayName("PUT - update book test with all fields")
    @Test
    public void updateBookTest() {
        Integer randomBookId = bookApiService.getBooks().stream()
                .map(BookDto::getId)
                .findAny()
                .orElseThrow(() -> new RuntimeException("No book find!"));
        BookDto updatedExpectedBook = BookDataGenerator.generateRandomBook();
        BookDto actualUpdatedBook = bookApiService.putBook(randomBookId, updatedExpectedBook);

        List<BookDto> allBooks = bookApiService.getBooks();
        assertThat(allBooks).contains(actualUpdatedBook);
    }



}
