package com.fiserv.fs.helix.rest.client;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@QuarkusTestResource(PartyDirectoryWireMock.class)
public class PartyDirectoryResourceTest {

    @Test
    public void testPartyDirectoryClientRestEndpoint() {
        given()
          .when().get("/PartyDirectoryClient/t-id/1")
          .then()
             .statusCode(200)
             .body("$.size()", is(1),
                     "[0].id", is("1"),
                     "[0].name", is("Aby"),
                     "[0].age", is (40));
    }

    @Test
    public void testPartyDirectoryClientRestAsyncEndpoint() {
        given()
                .when().get("/PartyDirectoryClient/t-async/1")
                .then()
                .statusCode(200)
                .body("$.size()", is(1),
                        "[0].id", is("1"),
                        "[0].name", is("Aby"),
                        "[0].age", is (40));
    }

    @Test
    public void testPartyDirectoryClientRestUniEndpoint() {
        given()
                .when().get("/PartyDirectoryClient/t-uni/1")
                .then()
                .statusCode(200)
                .body("$.size()", is(1),
                        "[0].id", is("1"),
                        "[0].name", is("Aby"),
                        "[0].age", is (40));
    }
}