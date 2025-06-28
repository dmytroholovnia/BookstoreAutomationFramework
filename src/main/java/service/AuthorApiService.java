package service;

import core.BaseApiService;
import dto.AuthorDto;
import enums.Param;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.NoArgsConstructor;
import org.apache.http.HttpStatus;

import java.util.List;

import static io.restassured.RestAssured.*;

@NoArgsConstructor
public class AuthorApiService extends BaseApiService {

    private static final String AUTHORS_URL = "/api/v1/Authors";
    private static final String AUTHOR_URL = "/api/v1/Authors/{id}";

    @Step("GET request to " + AUTHORS_URL)
    public List<AuthorDto> getAuthors() {
        Response response = given(requestSpecification)
                .basePath(AUTHORS_URL)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
        return List.of(response.as(AuthorDto[].class));
    }

    @Step("GET request to " + AUTHOR_URL + " id: {0}")
    public AuthorDto getAuthor(Integer authorId) {
        Response response = given(requestSpecification)
                .basePath(AUTHOR_URL)
                .pathParam(Param.ID.getValue(), authorId)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
        return response.as(AuthorDto.class);
    }

    @Step("POST request to " + AUTHORS_URL)
    public AuthorDto postAuthor(AuthorDto authorDto) {
        Response response = given(requestSpecification)
                .basePath(AUTHORS_URL)
                .body(authorDto)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
        return response.as(AuthorDto.class);
    }

    @Step("PUT request to " + AUTHOR_URL)
    public AuthorDto putAuthor(Integer authorId, AuthorDto authorDto) {
        Response response = given(requestSpecification)
                .basePath(AUTHOR_URL)
                .pathParam(Param.ID.getValue(), authorId)
                .body(authorDto)
                .when()
                .put()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
        return response.as(AuthorDto.class);
    }

    @Step("DELETE request to " + AUTHOR_URL + " by id: {0}")
    public void deleteAuthor(Integer authorId) {
        given(requestSpecification)
                .basePath(AUTHOR_URL)
                .pathParam(Param.ID.getValue(), authorId)
                .when()
                .delete()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
    }

}
