package com.cydeo.tests.day13_access_token_specs;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.annotation.meta.When;
import com.cydeo.pojo_models_beans_dataObject.Spartan;
import com.cydeo.utils.SpartanRestUtils;
import com.cydeo.utils.SpartanTestBase;
import com.cydeo.utils.SpartanTestBase;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BookItAccessTokenTest {
   /** Given accept type is Json
    And Query params: email = blyst6@si.edu & password = barbabaslyst
    When I send GET request to
    Url: https://cybertek-reservation-api-qa3.herokuapp.com/sign
    Then status code is 200
    And accessCode should be present */

   @DisplayName("Token")
    @Test
    public void bookItLoginTest(){
    Response response = given().accept(ContentType.JSON)
               .and().queryParam("email", "blyst6@si.edu")
               .and().queryParam("password", "barbabaslyst")
               .when().get("https://cybertek-reservation-api-qa3.herokuapp.com/sign");


    response.prettyPrint();
       System.out.println(response.statusCode());
       Assertions.assertEquals(200, response.statusCode());

       String accessToken = response.path("accessToken");
       System.out.println(accessToken);

       //access is not empty
       Assertions.assertTrue(accessToken != null && !accessToken.isEmpty());



   }























}
