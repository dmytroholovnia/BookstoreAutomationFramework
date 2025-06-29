import datagenerator.BookDataGenerator;
import datagenerator.RandomGenerator;
import datagenerator.TestDataProvider;
import datasetup.BookDataSetup;
import dto.BookDto;
import dto.ErrorDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import service.BookApiService;

import java.util.List;
import java.util.stream.Stream;

import static org.apache.http.HttpStatus.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.of;

public class BooksTests {

    private BookDataSetup bookDataSetup;
    private BookApiService bookApiService;

    @BeforeEach
    public void setup() {
        bookDataSetup = new BookDataSetup();
        bookApiService = new BookApiService();
    }

    @DisplayName("GET - get all books test")
    @Test
    public void getAllBooksTest() {
        List<BookDto> actualBooks = bookApiService.getBooks();
        assertThat(actualBooks).isNotNull();
    }

    @DisplayName("GET - get book by id test")
    @Test
    public void getBookTest() {
        Integer bookId = bookDataSetup.getRandomBookId();
        BookDto actualBook = bookApiService.getBook(bookId);
        assertThat(actualBook).isNotNull();
    }

    @DisplayName("GET - get book with non-existent id")
    @Test
    public void getNonExistentBookTest() {
        ErrorDto actualError = bookApiService.getBookExceptional(RandomGenerator.getBigNumber());
        assertThat(actualError.getStatus()).isEqualTo(SC_NOT_FOUND);
    }

    @DisplayName("GET - get book with invalid format of id")
    @ParameterizedTest(name = "Param: {0}")
    @MethodSource("getInvalidParams")
    public void getInvalidFormatBookIdTest(String invalidParam) {
        ErrorDto actualError = bookApiService.getBookExceptional(invalidParam);
        assertThat(actualError.getStatus()).isEqualTo(SC_BAD_REQUEST);
    }

    private static Stream<Arguments> getInvalidParams() {
        return TestDataProvider.getInvalidParams()
                .stream()
                .map(Arguments::of);
    }

    @DisplayName("POST - create new book")
    @Test
    public void postBookTest() {
        int maxBookId = bookApiService.getBooks().stream()
                .mapToInt(BookDto::getId)
                .max()
                .orElse(0);
        BookDto newBook = BookDataGenerator.generateBook(maxBookId + 1);
        BookDto createdBook = bookApiService.postBook(newBook);

        List<BookDto> allBooks = bookApiService.getBooks();
        assertThat(allBooks).contains(createdBook);
    }

    @DisplayName("POST - create book with invalid payload")
    @ParameterizedTest
    @MethodSource("getInvalidBookPayloads")
    public void postBookExceptionalTest(BookDto invalidBookDto) {
        ErrorDto actualError = bookApiService.postBookExceptional(invalidBookDto);
        assertThat(actualError.getStatus()).isEqualTo(SC_BAD_REQUEST);
    }

    private static Stream<Arguments> getInvalidBookPayloads() {
        return Stream.of(
                of(BookDataGenerator.emptyBook()),
                of(BookDataGenerator.noIdBook()),
                of(BookDataGenerator.noTitleBook()),
                of(BookDataGenerator.noPageCountBookDto()),
                of(BookDataGenerator.noExcerptBookDto()),
                of(BookDataGenerator.noPublishDateBookDto())
        );
    }

    @DisplayName("PUT - update book")
    @Test
    public void putBookTest() {
        int bookId = bookDataSetup.getRandomBookId();
        BookDto bookRequestDto = BookDataGenerator.generateBook(bookId);
        BookDto updatedBookDto = bookApiService.putBook(bookId, bookRequestDto);

        List<BookDto> allBooks = bookApiService.getBooks();
        assertThat(allBooks).contains(updatedBookDto);
    }

    @DisplayName("PUT - update book with invalid payload and/or id")
    @ParameterizedTest
    @MethodSource("getInvalidParamsAndPayloads")
    public void putBookExceptionalTest(String param, BookDto payload) {
        ErrorDto actualError = bookApiService.putBookExceptional(param, payload);
        assertThat(actualError.getStatus()).isEqualTo(SC_BAD_REQUEST);
    }

    private static Stream<Arguments> getInvalidParamsAndPayloads() {
        return getInvalidParams()
                .flatMap(idArg -> getInvalidBookPayloads()
                        .map(bookArg -> Arguments.of(idArg.get()[0], bookArg.get()[0]))
                );
    }

    @DisplayName("DELETE - delete book test")
    @Test
    public void deleteBookTest() {
        int bookId = bookDataSetup.getRandomBookId();
        bookApiService.deleteBook(bookId);

        List<BookDto> allBooks = bookApiService.getBooks();
        assertThat(allBooks.stream()
                .map(BookDto::getId)
                .toList())
                .doesNotContain(bookId);
    }

    @DisplayName("DELETE - delete book with invalid param")
    @ParameterizedTest
    @MethodSource("getInvalidParams")
    public void deleteBookExceptionTest(Object bookId) {
        ErrorDto actualError = bookApiService.deleteBookExceptional(bookId);
        assertThat(actualError.getStatus()).isEqualTo(SC_BAD_REQUEST);
    }
}