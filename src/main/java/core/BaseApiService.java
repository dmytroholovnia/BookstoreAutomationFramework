package core;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperSerializationContext;
import io.restassured.specification.RequestSpecification;

public abstract class BaseApiService {

    private static final String BASE_URL = "https://fakerestapi.azurewebsites.net/";

    public RequestSpecification getSpecification() {

        return new RequestSpecBuilder()
                .setConfig(getConfig())
                .setBaseUri(BASE_URL)
//                .header(new Header("merchant", PUBLIC_API_KEY))
//                .header(new Header("signature", generatedSignature))
//                .contentType(ContentType.JSON)
                .setContentType(ContentType.JSON)
//                .body(JsonUtils.toJson(object))
                .build()
                ;
    }

    private RestAssuredConfig getConfig() {
        return  RestAssured.config = RestAssured.config().objectMapperConfig(
                ObjectMapperConfig.objectMapperConfig()
                        .jackson2ObjectMapperFactory((cls, charset) ->  {
                            ObjectMapper objectMapper = new ObjectMapper();
//                            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                            return objectMapper;
                        })
        );

    }
}
