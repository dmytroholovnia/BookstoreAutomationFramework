package service;

import core.BaseApiService;
import dto.BookResponseDto;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.NoArgsConstructor;
import org.apache.http.HttpStatus;
import org.checkerframework.checker.units.qual.N;

import java.util.List;

import static io.restassured.RestAssured.given;

@NoArgsConstructor
public class BookApiService extends BaseApiService {

    private static final String BOOKS_URL = "api/v1/Books";
    private static final String BOOK_URL = "/api/v1/Books/{id}";

    public List<BookResponseDto> getBooks() {
        Response response = given(requestSpecification)
                .basePath(BOOKS_URL)
                .log().uri()
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        return List.of(response.as(BookResponseDto[].class));
    }

    public BookResponseDto getBook(Integer bookId) {
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

        return response.as(BookResponseDto.class);
    }

    public BookResponseDto addBook(BookResponseDto bookDto) {
        Response response = given(requestSpecification)
                .basePath(BOOKS_URL)
                .body(bookDto)
                .when()
                .post()
                .then()
                .extract()
                .response();

        return response.as(BookResponseDto.class);
    }

}
