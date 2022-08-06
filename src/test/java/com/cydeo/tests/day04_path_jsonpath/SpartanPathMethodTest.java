package com.cydeo.tests.day04_path_jsonpath;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import com.cydeo.utils.SpartanTestBase;

import java.util.List;


public class SpartanPathMethodTest extends SpartanTestBase {

    /**
     * Given accept is json
     * And path param id is 13
     * When I send get request to /api/spartans/{id}
     * ----------
     * Then status code is 200
     * And content type is json
     * And id value is 13
     * And name is "Jaimie"
     * And gender is "Female"
     * And phone is "7842554879"
     */

    @DisplayName("Test1")
    @Test
    public void example(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 13)
                .when().get("/spartans/{id}");

    /**
    "id": 13,
     "name": "Jaimie"
     "gender": "Female"
     "phone": 7842554879
     */

        System.out.println("id = " + response.path("id"));
        System.out.println("name = " + response.path("name"));
        System.out.println("gender = " + response.path("gender"));
        System.out.println("phone = " + response.path("phone"));


        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());
        assertEquals("Jaimie", response.path("name"));
        assertEquals("Female",response.path("gender"));
       assertEquals(7842554879L, (long)response.path("phone")); //(long) - casted making the same type LONG
      //  assertEquals(7842554879L, Long.valueOf("" + response.path("phone")));
        int spartID = response.path("id");
        assertEquals(13, spartID);
    }


    /**
     Given accept is json
     When I send get request to /api/spartans
     Then status code is 200
     And content type is json
     And I can navigate json array object
     */

    @DisplayName("Test2")
    @Test
    public void readSpartaJsonArrayUsingPathTest(){

        Response response = given().accept(ContentType.JSON)
                .when().get("/spartans");
        assertEquals(200, response.statusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());
        System.out.println("first spartan id = " + response.path("id[0]"));
        System.out.println("first spartan id = " + response.path("name[0]"));
        System.out.println("first spartan id = " + response.path("[0].name"));


        //LAST
        System.out.println("last spartan id = " + response.path("id[-1]"));
        System.out.println("last spartan name = " + response.path("name[-1]"));


        //get all ids into a List
       List<Integer> allIDS =  response.path("id");
        System.out.println("ID SIZE " + allIDS.size());
        System.out.println("ALL ID " + allIDS);
        assertTrue(allIDS.contains(22) && allIDS.size() == 100);


        //get all names and say hi
        List<String> allName  = response.path("name");
        System.out.println("Size of names " + allName.size());
        System.out.println("All name " + allName);
        assertTrue(allName.contains("Sol") && allIDS.size() == 100);

   //     allName.forEach(name -> System.out.println("Hi " + name)); // FOR LOOP

        for (String name : allName){
            System.out.println("Hi " + name);
        }




    }





}
