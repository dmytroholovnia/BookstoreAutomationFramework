import datagenerator.BookDataGenerator;
import dto.BookResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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

    @Test
    public void getBooksTest() {
        List<BookResponseDto> actualBooks = bookApiService.getBooks();
    }

    @Test
    public void getBookTest() {
        BookResponseDto actualBook = bookApiService.getBook(1);
    }

    @Test
    public void addBookTest() {
        BookResponseDto expectedBook = BookDataGenerator.generateRandomBook();
        BookResponseDto actualBook = bookApiService.addBook(expectedBook);
        assertThat(actualBook)
                .usingRecursiveComparison()
                .isEqualTo(expectedBook);

        List<BookResponseDto> allBooks = bookApiService.getBooks();
        assertThat(allBooks).contains(expectedBook);
    }
}
