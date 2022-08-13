package com.cydeo.tests.day06_;

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

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanToMapClass extends SpartanTestBase{

    /**
     Given accept type is application/json
     And Path param id = 10
     When i send GET request to /api/spartans
     Then status code is 200
     And content type is json
     And spartan data matching:
     id > 10
     name>Lorenza
     gender >Female
     phone >3312820936
     */



    @Test
    public void spartan_to_map(){


      Response response =  given().accept(ContentType.JSON)
               .and().pathParam("id", 10)
               .when().get("/spartans/{id}");
      response.prettyPrint();


      assertEquals(HttpStatus.SC_OK, response.statusCode());
      assertEquals(ContentType.JSON.toString(), response.contentType());


      //convert json to map object.   de-serialization
        Map<String,Object> spartMap = response.body().as(Map.class);
        System.out.println(spartMap); // id=10, name=Lorenza, gender=Female, phone=3312820936
        System.out.println("keys " + spartMap.keySet()); //keys [id, name, gender, phone]
        assertEquals(10, spartMap.get("id"));
        assertEquals("Lorenza",spartMap.get("name"));
        assertEquals("Female", spartMap.get("gender"));
        assertEquals(3312820936L, spartMap.get("phone"));



        Map<String,?> spartMap2 = response.body().as(Map.class); // accept anytime when you don't know
        System.out.println(spartMap2); // linkedHashMap in the background










    }



}
