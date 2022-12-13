package com.cydeo.homework;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import  static io.restassured.RestAssured.*;

import java.util.List;

public class h_2 {

//"https://reqres.in/api/users";
    String url = "https://reqres.in/api/users";



    @Test
    public void test(){
        Response response = given().accept(ContentType.JSON)
                .when().get(url);
        response.prettyPrint();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(HttpStatus.SC_OK, response.statusCode());
        Assertions.assertEquals("application/json; charset=utf-8", response.contentType());

        Assertions.assertEquals("George", response.path("data.first_name[0]"));
        Assertions.assertEquals("George", response.path("data[0].first_name"));


        Assertions.assertTrue(response.asString().contains("Emma"));
        Assertions.assertTrue(response.body().asString().contains("Bluth"));



        Headers headers = response.getHeaders();
        System.out.println(headers);
        System.out.println("TOTA " + response.getHeader("Content-Type"));


        //Only one name
        JsonPath jsonPath = response.jsonPath();
        System.out.println(jsonPath.getString("data.first_name[5]"));

        //All names
        List<String> jsonPathList = jsonPath.getList("data.first_name");
        System.out.println(jsonPathList);


    }




}
