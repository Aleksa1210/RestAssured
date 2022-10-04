package com.cydeo.tests.day16_methodsource_mocks;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;


import org.junit.jupiter.params.ParameterizedTest;
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

public class MockStudentApiTest {

    @BeforeAll
    public static void setUp(){
        baseURI = "https://2b35bef5-b9b9-47e0-9c68-39f1dd014cdd.mock.pstmn.io";
    }

    @DisplayName("Mock Test")
    @Test
    public void testStudent(){
        given().accept(ContentType.JSON)
                .when().get("/students/1")
                .then().assertThat().statusCode(200)
                .and().contentType(ContentType.JSON)
                .log().all();

    }

}
