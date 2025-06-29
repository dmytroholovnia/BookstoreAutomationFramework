package service;

import core.BaseApiService;
import dto.BookDto;
import dto.ErrorDto;
import enums.Param;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.NoArgsConstructor;
import org.apache.http.HttpStatus;

import java.util.List;

import static io.restassured.RestAssured.given;

@NoArgsConstructor
public class BookApiService extends BaseApiService {

    private static final String BOOKS_URL = "/api/v1/Books";
    private static final String BOOK_URL = "/api/v1/Books/{id}";

    @Step("GET request to " + BOOKS_URL)
    public List<BookDto> getBooks() {
        Response response = given(requestSpecification)
                .basePath(BOOKS_URL)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
        return List.of(response.as(BookDto[].class));
    }

    public BookDto getBook(Integer bookId) {
        Response response = sendGetRequestToBook(bookId)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
        return response.as(BookDto.class);
    }

    public ErrorDto getBookExceptional(Object bookId) {
        Response response = sendGetRequestToBook(bookId);
        return response.as(ErrorDto.class);
    }

    @Step("GET request to " + BOOK_URL + " id: {0}")
    private Response sendGetRequestToBook(Object bookId) {
        return given(requestSpecification)
                .basePath(BOOK_URL)
                .pathParam(Param.ID.getValue(), bookId)
                .when()
                .get()
                .then()
                .extract()
                .response();
    }

    public BookDto postBook(BookDto bookDto) {
        Response response = sendPostBookRequest(bookDto)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
        return response.as(BookDto.class);
    }

    public ErrorDto postBookExceptional(BookDto bookDto) {
        Response response = sendPostBookRequest(bookDto);
        return response.as(ErrorDto.class);
    }

    @Step("POST request to " + BOOKS_URL)
    private Response sendPostBookRequest(BookDto bookDto) {
        return given(requestSpecification)
                .basePath(BOOKS_URL)
                .body(bookDto)
                .when()
                .post()
                .then()
                .extract()
                .response();
    }

    public BookDto putBook(Integer bookId, BookDto bookDto) {
        Response response = sendPutBookRequest(bookId, bookDto);
        return response.as(BookDto.class);
    }

    public ErrorDto putBookExceptional(Object bookId, Object bookDto) {
        Response response = sendPutBookRequest(bookId, bookDto);
        return response.as(ErrorDto.class);
    }

    @Step("PUT request to " + BOOK_URL)
    private Response sendPutBookRequest(Object bookId, Object bookDto) {
        return given(requestSpecification)
                .basePath(BOOK_URL)
                .pathParam(Param.ID.getValue(), bookId)
                .body(bookDto)
                .when()
                .put()
                .then()
                .extract()
                .response();
    }

    public void deleteBook(Integer bookId) {
        sendDeleteBookRequest(bookId);
    }

    public ErrorDto deleteBookExceptional(Object bookId) {
        Response response = sendDeleteBookRequest(bookId);
        return response.as(ErrorDto.class);
    }

    @Step("DELETE request to " + BOOK_URL + " by id: {0}")
    private Response sendDeleteBookRequest(Object bookId) {
        return given(requestSpecification)
                .basePath(BOOK_URL)
                .pathParam(Param.ID.getValue(), bookId)
                .when()
                .delete()
                .then()
                .extract()
                .response();
    }
}