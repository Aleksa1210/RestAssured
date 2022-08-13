package com.cydeo.tests.day07_deserialization;


import com.cydeo.pojo_models_beans_dataObject.Spartan;
import com.cydeo.pojo_models_beans_dataObject.SpartanSearch;
import com.cydeo.utils.SpartanTestBase;
import com.cydeo.utils.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.filters;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Given accepts type is json
 * And query param nameContains value is "e"
 * And query param gender value is "Female"
 * When I send get request to /spartans/search
 * Then status code is 200
 * And content type is Json
 * And json response data is as expected
 */

public class SpartanSearch_POJO_TS extends SpartanTestBase{

    @DisplayName("T1")
    @Test
    public void listOdPOJO(){
        Map<String,String> queryMap = new HashMap<>();
        queryMap.put("nameContains","e");
        queryMap.put("gender","Female");


        Response response = given().accept(ContentType.JSON)
                .and().queryParams(queryMap)
                .when().get("/spartans/search");
        response.prettyPrint();

        assertEquals(200, response.statusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());
        //de-serialization
        SpartanSearch spartanSearchResult = response.body().as(SpartanSearch.class); // take all data and create object and assigned there
        System.out.println(spartanSearchResult.getTotalElement());
        System.out.println(spartanSearchResult.getContent()); // give list of spartans
        System.out.println("First spartan" + spartanSearchResult.getContent().get(0)); //spartan like block
        Spartan secondSpartan =  spartanSearchResult.getContent().get(1);
        System.out.println(secondSpartan.getName());
        System.out.println(secondSpartan.getId());


        List<Spartan> getData = spartanSearchResult.getContent();
        System.out.println(getData.get(1));

        List<String> names = new ArrayList<>();
        for (Spartan spartan : getData){
            names.add(spartan.getName());
        }
        System.out.println(names);

        //Using functional programing
       List <String> allNam =  getData.stream().map(spartan -> spartan.getName()).collect(Collectors.toList());
        System.out.println(allNam);

        //   assertTrue(queryMap.containsKey(spartanSearchResult.getContent()));







    }






}
