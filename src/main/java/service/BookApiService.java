package service;

import core.BaseApiService;
import dto.BookResponseDto;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.NoArgsConstructor;

import java.awt.print.Book;
import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;

public class BookApiService extends BaseApiService {

    private static final String BOOKS_URL = "api/v1/Books";

    public BookApiService() {}

    public List<BookResponseDto> getBooks() {
        Response response =
//                getSpecification()
                given(getSpecification())
                .basePath(BOOKS_URL)
                        .log().all()
                .when()
                .get()
                .then()
                        .log().all()
//                .statusCode(200)    //todo replace with const
                .extract()
                .response();

//        List<BookResponseDto> dto = List.of(response.as(BookResponseDto[].class));

        return List.of(response.as(BookResponseDto[].class));
    }

}
