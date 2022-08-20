package com.cydeo.utils;

import com.cydeo.pojo_models_beans_dataObject.Spartan;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.*;

public class SpartanRestUtils {

    private static String baseUrl = ConfigurationReader.getProperty("spartan_api_url"); //


    public static void deleteSpartanById(int spartanID){
        //send delete request {{baseUrl}}/api/spartans/{id}

        given().pathParam("id", spartanID)
                .when().delete(baseUrl + "/spartans/{id}")
                .then().log().all();


    }

    public static Spartan getNewSpartanUT(){
        Faker randomFaker = new Faker();
        Spartan spartan = new Spartan();
         int number = randomFaker.number().numberBetween(1,3);
         if (number == 1){
             spartan.setGender("Female");
         } else {
             spartan.setGender("Male");
         }
        spartan.setName(randomFaker.name().firstName());
        spartan.setPhone(randomFaker.number().numberBetween(1000000000L, 9999999999L));

     return spartan;
    }

    /**
     * Method accepts spartanID and sends a GET request
     * @param spartanId
     * @return is Map object containing response json data
     */
    public static Map<String, Object> getSpartan(int spartanId) {
        Response response = given().log().uri().accept(ContentType.JSON)
                .and().pathParam("id", spartanId)
                .when().get(baseUrl + "/spartans/{id}");

        Map<String, Object> spartanMap = response.as(Map.class);
        System.out.println("TEST");
        System.out.println(spartanMap);
        return spartanMap;
    }



}
