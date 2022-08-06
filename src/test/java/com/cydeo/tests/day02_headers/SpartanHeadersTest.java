package com.cydeo.tests.day02_headers;

import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;

public class SpartanHeadersTest {
    /**
     • When I send a GET request to
     • spartan_base_url/api/spartans
     • Then Response STATUS CODE must be 200
     • And I Should see all Spartans data in JSON format
     */
    String url = "http://3.93.242.50:8000/api/spartans";

    @DisplayName("Get api/spartans and expect Json format")
    @Test
    public void m(){

            when().get(url)
            .then().assertThat().statusCode(200)
            .and().contentType("application/json");
         //  .and().contentType(io.restassured.http.ContentType.JSON);

    }

    /**
     * Given Accept type is application/xml
     • When I send a GET request to
     • spartan_base_url/api/spartans
     • Then Response STATUS CODE must be 200
     • And I Should see all Spartans data in xml format
     */
    @DisplayName("XML")
    @Test
    public void acceptTypeHeaderTest(){

        given().accept(ContentType.XML)
                .when().get(url)
                .then().assertThat().statusCode(200)
                .and().contentType(ContentType.XML);
    }


    /**
     * Given Accept type is application/json
     • When I send a GET request to
     • spartan_base_url/api/spartans
     • Then Response STATUS CODE must be 200
     • And read Headers
     */
    @DisplayName("Header")
    @Test
    public void readResponseHeaders(){

       Response response = given().accept(ContentType.JSON)
                .when().get(url);
        System.out.println("response getHeader date " + response.getHeader("date")); // can take specific value
        System.out.println("Content-Type" + response.getHeader( "Content-Type"));
        System.out.println("Connection" + response.getHeader("Connection"));
        response.then().assertThat().statusCode(200);

        Headers headers = response.getHeaders();
        System.out.println("headers" + headers);






    }

}
