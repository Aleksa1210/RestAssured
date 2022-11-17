package com.cydeo.homework;
import com.cydeo.pojo_models_beans_dataObject.Spartan;
import com.cydeo.pojo_models_beans_dataObject.Spartan1;
import com.fasterxml.jackson.databind.util.JSONPObject;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Assert.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.cydeo.utils.HRApiTestBase;
import com.cydeo.utils.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import  static io.restassured.RestAssured.*;
import javax.annotation.meta.When;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

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
