package com.cydeo.utils;

import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.*;

public abstract class BookItAPITestBase {

    protected static RequestSpecification teacherReqSpec;

    protected static  ResponseSpecification responseSpec;


    @BeforeAll
    public static void setUp() {
        baseURI = ConfigurationReader.getProperty("bookit.base.url");


        String teacherToken = getAccessToken(ConfigurationReader.getProperty("teacher_email"),
                ConfigurationReader.getProperty("teacher_password"));

        teacherReqSpec = given().accept(ContentType.JSON).and().header("Authorization", teacherToken).log().all();
        responseSpec = expect().statusCode(200).and().contentType(ContentType.JSON).logDetail(LogDetail.ALL);




    }




    public static String getAccessToken(String email, String password) {
        String accessToken = given().accept(ContentType.JSON)
                .and().queryParam("email", email)
                .and().queryParam("password", password)
                .when().get("/sign")
                .then().assertThat().statusCode(200)
                .and().extract().path("accessToken");

        return "Bearer " + accessToken;
    }

}
        // and value will be token
        // "Bearer" - owner, hive autorization to the Bearer




















