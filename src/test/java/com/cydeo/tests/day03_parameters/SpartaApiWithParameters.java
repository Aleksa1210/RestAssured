package com.cydeo.tests.day03_parameters;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class SpartaApiWithParameters {
     /*   Given accept type is Json
          And Id parameter value is 5
          When user sends GET request to /api/spartans/{id}
          Then response status code should be 200
          And response content-type: application/json
          And "Blythe" should be in response payload(body)
       */

    String url ="http://3.93.242.50:8000/api/spartans";
    int id = 5;

    @DisplayName("TS 1")
    @Test
    public void test(){
        given().accept(ContentType.JSON)
                .when().get(url+"/" + id);

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", id) // key value5
                .when().get(url+"/{id}"); //5

        response.prettyPrint();
        System.out.println("status code = " + response.statusCode());
        Assertions.assertEquals(200, response.statusCode()); // way 1
        Assertions.assertEquals(HttpStatus.SC_OK, response.statusCode()); // way 2 SC_OK status code_OK

        System.out.println(response.contentType());
        response.getHeader("content-type");

        Assertions.assertEquals("application/json", response.contentType());
        Assertions.assertTrue(response.asString().contains("Blythe"));

    }


    /**
     *Given accept type is Json
     *         And Id parameter value is 500
     *         When user sends GET request to /api/spartans/{id}
     *         Then response status code should be 404
     *         And response content-type: application/json
     *         And "Not Found" message should be in response payload
     */

    @DisplayName("TS 2")
    @Test
    public void test2(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 500)
                .when().get(url + "/{id}");

        System.out.println(response.statusCode());
        Assertions.assertEquals(404, response.statusCode());
        System.out.println(response.contentType());
        Assertions.assertEquals("application/json", response.contentType());
       // Assertions.assertEquals(ContentType.JSON.toString(), response.contentType());
        Assertions.assertTrue(response.asString().contains("Not Found"));



    }

}





