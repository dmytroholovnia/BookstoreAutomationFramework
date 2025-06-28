package service;

import core.BaseApiService;
import dto.AuthorDto;
import dto.ErrorDto;
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

    public AuthorDto getAuthor(Integer authorId) {
        Response response = sendGetRequestToAuthor(authorId)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        return response.as(AuthorDto.class);
    }

    public ErrorDto getAuthorExceptional(Object authorId) {
        Response response = sendGetRequestToAuthor(authorId);
        return response.as(ErrorDto.class);
    }

    @Step("GET request to " + AUTHOR_URL + " id: {0}")
    private Response sendGetRequestToAuthor(Object authorId) {
        return given(requestSpecification)
                .basePath(AUTHOR_URL)
                .pathParam(Param.ID.getValue(), authorId)
                .when()
                .get()
                .then()
                .extract()
                .response();
    }

    public AuthorDto postAuthor(AuthorDto authorDto) {
        Response response = sendPostAuthorRequest(authorDto)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
        return response.as(AuthorDto.class);
    }

    public ErrorDto postAuthorExceptional(AuthorDto authorDto) {
        Response response = sendPostAuthorRequest(authorDto);
        return response.as(ErrorDto.class);
    }

    @Step("POST request to " + AUTHORS_URL)
    private Response sendPostAuthorRequest(AuthorDto authorDto) {
        return given(requestSpecification)
                .basePath(AUTHORS_URL)
                .body(authorDto)
                .when()
                .post()
                .then()
                .extract()
                .response();
    }

    public AuthorDto putAuthor(Integer authorId, AuthorDto authorDto) {
        Response response = sendPutAuthorRequest(authorId, authorDto);
        return response.as(AuthorDto.class);
    }

    public ErrorDto putAuthorExceptional(Object authorId, Object authorDto) {
        Response response = sendPutAuthorRequest(authorId, authorDto);
        return response.as(ErrorDto.class);
    }

    @Step("PUT request to " + AUTHOR_URL)
    private Response sendPutAuthorRequest(Object authorId, Object authorDto) {
        return given(requestSpecification)
                .basePath(AUTHOR_URL)
                .pathParam(Param.ID.getValue(), authorId)
                .body(authorDto)
                .when()
                .put()
                .then()
                .extract()
                .response();
    }

    public void deleteAuthor(Integer authorId) {
        sendDeleteAuthorRequest(authorId);
    }

    public ErrorDto deleteAuthorExceptional(Object authorId) {
        Response response = sendDeleteAuthorRequest(authorId);
        return response.as(ErrorDto.class);
    }

    @Step("DELETE request to " + AUTHOR_URL + " by id: {0}")
    private Response sendDeleteAuthorRequest(Object authorId) {
        return given(requestSpecification)
                .basePath(AUTHOR_URL)
                .pathParam(Param.ID.getValue(), authorId)
                .when()
                .delete()
                .then()
                .extract()
                .response();
    }

}
