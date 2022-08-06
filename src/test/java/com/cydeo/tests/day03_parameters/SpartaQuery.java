package com.cydeo.tests.day03_parameters;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;


public class SpartaQuery {
    /**
        Given Accept type is Json
        And query parameter values are:
        gender|Female
        nameContains|e
        When user sends GET request to /api/spartans/search
        Then response status code should be 200
        And response content-type: application/json
        And "Female" should be in response payload
        And "Janette" should be in response payload
     */
String url = "http://3.87.195.148:8000/api/spartans/search";

    @Test
    public void test(){

        Response response = given().log().all().accept(ContentType.JSON)
                .and().queryParam("gender","Female")
                .and().queryParam("nameContains","e")
                .when().get(url);

        //verify status code
       Assertions.assertEquals(200, response.statusCode());

        //verify response header
        Assertions.assertEquals("application/json", response.contentType());

        //     And "Female" should be in response payload
        //     And "Janette" should be in response payload

        Assertions.assertTrue(response.asString().contains("Female"));
        Assertions.assertTrue(response.asString().contains("Janette"));

    }
}
