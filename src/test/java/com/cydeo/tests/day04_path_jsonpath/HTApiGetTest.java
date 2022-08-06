package com.cydeo.tests.day04_path_jsonpath;

import com.cydeo.utils.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class HTApiGetTest {

    @BeforeEach //@Before in JUnit 4
    public void setUp(){
        // http://3.93.242.50:1000/ords/hr
        RestAssured.baseURI = ConfigurationReader.getProperty("hr_api_url"); // get url
    //    baseURI = ConfigurationReader.getProperty("hr_api_url"); // the same


    }


    /**
     * Given accept type is json
     * When user send get request to /ords/hr/regions
     * Status code should be 200
     * Content type should be "application/json"
     * And body should contain "Europe"
     */

    @DisplayName("GET /regions")
    @Test
    public void getRegionsTest(){
    Response response = given().accept(ContentType.JSON)
            .when().get("/regions");

    response.prettyPrint();

    assertEquals(200, response.statusCode());
    assertEquals("application/json", response.contentType());
    assertTrue(response.body().asString().contains("Europe"), "Europe is not in json");

    }


    /**
     * Given accept type is json
     And Path param "region_id" value is 1
     When user send get request to /ords/hr/regions/{region_id}
     Status code should be 200
     Content type should be "application/json"
     And body should contain "Europe"
     */


    @Test
    public void getSingleRegion(){
        Response response =  given().log().all().accept(ContentType.JSON)  // log.all print all details
                                                                            // accept type - json
            .and().pathParam("region_id",1)
            .when().get("/regions/{region_id}");
        response.prettyPrint();

        assertEquals(200, response.statusCode());
       //assertEquals(HttpStatus.SC_OK, response.statusCode() ); the same as line 68 == 200

        assertEquals("application/json", response.contentType());
      //assertEquals(ContentType.JSON.toString(),response.contentType() ); the same as line 71 == "application/json"

        assertTrue(response.body().asString().contains("Europe"));



    }
    /**
     * Given accept type is json
     * And query param q={"region_name": "Americas"}
     * When user send get request to /ords/hr/regions
     * Status code should be 200
     * Content type should be "application/json"
     * And region name should be "Americas"
     * And region id should be "2"
     */

    @Test
    public void getRegionWithQuery(){
        Response response = given().log().all().accept(ContentType.JSON) //accept type - json
                .and().queryParam("q","{\"region_name\": \"Americas\"}")
                .when().get("/regions");
        response.prettyPrint();

        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());
        assertTrue(response.body().asString().contains("Americas"));
        assertTrue(response.asString().contains("2"),"region id is incorrect");



    }










}
