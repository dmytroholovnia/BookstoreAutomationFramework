import datagenerator.BookDataGenerator;
import datasetup.BookDataSetup;
import dto.BookDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.BookApiService;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class BooksTests {

    private BookDataSetup bookDataSetup;
    private BookApiService bookApiService;

    @BeforeEach
    public void setup() {
        bookDataSetup = new BookDataSetup();
        bookApiService = new BookApiService();
    }

    @DisplayName("GET - all books test")
    @Test
    public void getBooksTest() {
        List<BookDto> actualBooks = bookApiService.getBooks();
        assertThat(actualBooks).isNotNull();
    }

    @DisplayName("GET - book by id test")
    @Test
    public void getBookTest() {
        var bookId = bookDataSetup.getRandomBookId();
        BookDto actualBook = bookApiService.getBook(bookId);
        assertThat(actualBook).isNotNull();
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
        Integer randomBookId = bookDataSetup.getRandomBookId();
        BookDto updatedExpectedBook = BookDataGenerator.generateRandomBook();
        BookDto actualUpdatedBook = bookApiService.putBook(randomBookId, updatedExpectedBook);

        List<BookDto> allBooks = bookApiService.getBooks();
        assertThat(allBooks).contains(actualUpdatedBook);
    }

    @DisplayName("DELETE - delete book by id test")
    @Test
    public void deleteBookTest() {
        Integer randomBookId = bookDataSetup.getRandomBookId();
        bookApiService.deleteBook(randomBookId);
        List<BookDto> allBooks = bookApiService.getBooks();
        assertThat(allBooks.stream()
                .map(BookDto::getId)
                .toList()).doesNotContain(randomBookId);
    }

}
