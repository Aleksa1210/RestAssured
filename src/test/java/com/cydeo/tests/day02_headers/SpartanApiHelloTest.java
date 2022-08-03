package com.cydeo.tests.day02_headers;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanApiHelloTest {
    /***
    When I send GET request to http://3.93.242.50:8000/api/hello
    Then status code should be 200
    And response body should be equal to "Hello from Sparta"
     And contain type is "text/plain;charset=UTF-8"
     */
    String url = "http://3.93.242.50:8000/api/hello";

    @DisplayName("Get Api/hello")
    @Test
    public void helloApiTest() {

        //JUnit assertion

      Response response = when().get(url);  // need to put import static io.restassured.RestAssured.*;
        //Response response1 = RestAssured.when().get(url); the same
       // Response response2 = RestAssured.get(url);   the same

        System.out.println(response.statusCode());
        assertEquals(200, response.statusCode()); //import static org.junit.jupiter.api.Assertions.*;

        response.prettyPrint();
        assertEquals("Hello from Sparta", response.asPrettyString());
       // assertEquals("Hello from Sparta", response.body().asString()); the same

        System.out.println(response.contentType());
        assertEquals("text/plain;charset=UTF-8", response.contentType());

    }

    /***
     When I send GET request to http://3.93.242.50:8000/api/hello
     Then status code should be 200
     And contain type is "text/plain;charset=UTF-8"
     */

    @DisplayName("Another format")
    @Test
    public void helloBddTest(){

        //BDD STYLE
        when().get(url)
                .then().assertThat().statusCode(200)
                .and().contentType("text/plain;charset=UTF-8");
    }






}
