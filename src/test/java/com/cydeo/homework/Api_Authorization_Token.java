package com.cydeo.homework;

import com.cydeo.pojo_models_beans_dataObject.SpartanSearch;
import com.cydeo.utils.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;



public class Api_Authorization_Token {

    String token = " Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4NiIsImF1ZCI6InN0dWRlbnQtdGVhbS1sZWFkZXIifQ.lEfjcu6RpBfcZ4qWthzZU8uH8fX4FCJFfxBnPNgh4Mo";

//    @Test
//    public void test(){
//        Response response = given().header("Authorization", token)
//                .when().get(ConfigurationReader.getProperty("url_end_token"));
//        Assertions.assertEquals(response.statusCode(), 200);
//        response.prettyPrint();
//
//    }

    @BeforeClass
    public static void setUp (){

        RestAssured.baseURI = ConfigurationReader.getProperty("url_base_token");
    }

    @Test
    public void access (){
        Response response = given().header("Authorization", token)
                .when().get("/api/campuses");
        Assertions.assertEquals(response.statusCode(), 200);
        response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();
        List<String> jsListName = jsonPath.getList("clusters.rooms.name");
        System.out.println(jsListName);


        List<String> jsListID = jsonPath.getList("clusters.rooms.id");
        List<String> jsListRooms = jsonPath.getList("clusters.id");
        System.out.println("" + jsListRooms + jsListID);

        System.out.println("________________________");



        List<Map<String,?>> a = jsonPath.getList("clusters");
        System.out.print( a);

        System.out.println("________________________");

        List<Map<String,?>> jsList = response.body().as(List.class);
                System.out.println(jsList.toString());

        System.out.println("+++++++++++++++");

        for (Map<String,?> map : jsList){



                if (jsList.equals("id")) {
                    map.keySet();

                }else {
                    continue;
                }
            System.out.println(map);
        }




    }











}
