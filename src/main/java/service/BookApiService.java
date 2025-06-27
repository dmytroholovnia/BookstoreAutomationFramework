package service;

import core.BaseApiService;
import dto.BookDto;
import io.restassured.response.Response;
import lombok.NoArgsConstructor;
import org.apache.http.HttpStatus;

import java.util.List;

import static io.restassured.RestAssured.given;

@NoArgsConstructor
public class BookApiService extends BaseApiService {

    private static final String BOOKS_URL = "api/v1/Books";
    private static final String BOOK_URL = "/api/v1/Books/{id}";

    public List<BookDto> getBooks() {
        Response response = given(requestSpecification)
                .basePath(BOOKS_URL)
                .log().uri()
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        return List.of(response.as(BookDto[].class));
    }

    public BookDto getBook(Integer bookId) {
        Response response = given(requestSpecification)
                .basePath(BOOK_URL)
                .pathParam("id", bookId)
                .log().uri()
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        return response.as(BookDto.class);
    }

    public BookDto postBook(BookDto bookDto) {
        Response response = given(requestSpecification)
                .basePath(BOOKS_URL)
                .body(bookDto)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        return response.as(BookDto.class);
    }

    public BookDto putBook(Integer bookId, BookDto bookDto) {
        Response response = given(requestSpecification)
                .basePath(BOOK_URL)
                .pathParam("id", bookId)
                .body(bookDto)
                .when()
                .put()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        return response.as(BookDto.class);
    }

}
