package com.cydeo.utils;

import com.cydeo.utils.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public abstract class SpartanTestBase {
  // visible for suclises and for the same package
    protected static RequestSpecification requestSpac;


    @BeforeAll //@Before in JUnit 4, in the 5 BeforeAll
    public static void setUp(){
        RestAssured.baseURI = ConfigurationReader.getProperty("spartan_api_url");

        requestSpac = given().accept(ContentType.JSON)
                .and().auth().basic("admin", "admin");
    }




}
