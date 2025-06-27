import dto.BookResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.BookApiService;

import java.util.List;

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
}
