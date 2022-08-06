package com.cydeo.utils;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class SpartanTestBase {
    @BeforeAll //@Before in JUnit 4, in the 5 BeforeAll
    public static void setUp(){
        RestAssured.baseURI = ConfigurationReader.getProperty("spartan_api_url");

    }




}
