package com.cydeo.tests.day04_path_jsonpath;

import com.cydeo.utils.ConfigurationReader;
import com.cydeo.utils.HRApiTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import java.util.List;

public class HrApiPathMethodTest extends HRApiTestBase {

    @DisplayName("TEST1")
    @Test
    public void hrPathMethod(){

        Response response = given().accept(ContentType.JSON)
                .when().get("countries");
        assertEquals(200, response.statusCode());


        System.out.println("first country id "+ response.path("items.country_id[0]"));
        System.out.println("first country name " + response.path("items[0].country_name"));

        assertEquals("AR", response.path("items.country_id[0]") );
        assertEquals("Argentina", response.path( "items.country_name[0]"));

        List<String> countries = response.path("items.country_name");
        System.out.println("All countries name" + countries);


    }


}
