package com.cydeo.utils;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class HrApiTestBase {

    @BeforeAll //@Before in JUnit 4, in the 5 BeforeAll
    public static void setUp(){
        RestAssured.baseURI = ConfigurationReader.getProperty("hr_api_url"); // key nme

    }
}
