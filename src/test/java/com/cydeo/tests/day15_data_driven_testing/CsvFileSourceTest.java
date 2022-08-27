package com.cydeo.tests.day15_data_driven_testing;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import com.cydeo.utils.ConfigurationReader;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.*;


public class CsvFileSourceTest {


    @BeforeAll
    public static void setUp() {
        baseURI = ConfigurationReader.getProperty("zip_code_url");
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/jsonschemas/ZipCodes.csv", numLinesToSkip = 1)
    public void zipcode_test(String state, String city, int zipcodeCount) {

        Map <String, String> paramsMap = new HashMap<>();
        paramsMap.put("state", state);
        paramsMap.put("city", city);


        given().accept(ContentType.JSON)
                .and().pathParams(paramsMap)
                .when().get("/us/{state}/{city}")
                .then().assertThat().statusCode(200)
                .and().contentType(ContentType.JSON)
                .and().body("places", hasSize(zipcodeCount))
                .log().all();

    }

    }
