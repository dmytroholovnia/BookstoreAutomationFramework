package service;

import core.BaseApiService;
import dto.BookDto;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.NoArgsConstructor;
import org.apache.http.HttpStatus;
import enums.Param;

import java.util.List;

import static enums.Param.*;
import static io.restassured.RestAssured.given;

@NoArgsConstructor
public class BookApiService extends BaseApiService {

    private static final String BOOKS_URL = "api/v1/Books";
    private static final String BOOK_URL = "/api/v1/Books/{id}";

    @Step("GET request to " + BOOKS_URL)
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

    @Step("GET request to " + BOOK_URL + " id: {0}")
    public BookDto getBook(Integer bookId) {
        Response response = given(requestSpecification)
                .basePath(BOOK_URL)
                .pathParam(ID.getValue(), bookId)
                .log().uri()
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        return response.as(BookDto.class);
    }

    @Step("POST request to " + BOOKS_URL)
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

    @Step("PUT request to " + BOOK_URL)
    public BookDto putBook(Integer bookId, BookDto bookDto) {
        Response response = given(requestSpecification)
                .basePath(BOOK_URL)
                .pathParam(ID.getValue(), bookId)
                .body(bookDto)
                .when()
                .put()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        return response.as(BookDto.class);
    }

    @Step("DELETE request to " + BOOK_URL)
    public void deleteBook(Integer bookId) {
        given(requestSpecification)
                .basePath(BOOK_URL)
                .pathParam(ID.getValue(), bookId)
                .when()
                .delete()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
    }

}
