package org.acme.conference.jwt;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class JwtResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/jwt")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }

}