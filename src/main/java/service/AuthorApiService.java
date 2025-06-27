package service;

import core.BaseApiService;
import dto.AuthorDto;
import enums.Param;
import io.restassured.response.Response;
import lombok.NoArgsConstructor;
import org.apache.http.HttpStatus;

import java.util.List;

import static io.restassured.RestAssured.*;

@NoArgsConstructor
public class AuthorApiService extends BaseApiService {

    private static final String AUTHORS_URL = "/api/v1/Authors";
    private static final String AUTHOR_URL = "/api/v1/Authors/{id}";

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

}
