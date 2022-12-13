package com.cydeo.homework;

import io.restassured.path.json.JsonPath;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import  static io.restassured.RestAssured.*;

import java.util.List;


public class h_1 {

    String url = "https://reqres.in/api/users";

    @Test
    public void test(){

        Response response = when().get(url);
        response.prettyPrint();
        String s = response.asString();
        Assertions.assertEquals(200, response.statusCode());
        System.out.println("++++++++++++++++++++++++++++++++");
        String a = JsonPath.from(s).get("data.first_name[2]");
        Assertions.assertEquals(a, "Emma");


        System.out.println("_______________JSONPATH______________");
        JsonPath jsonPath = response.jsonPath();
        System.out.println("MY " + jsonPath.getString("data.first_name[2]")); // only 3 names
        List<String> js = jsonPath.getList("data.first_name"); // all names
        System.out.println(js);
    }


    @Test
    public void test2(){
        System.out.println("_______________Chaining, Hamcrest______________");

       given().accept(ContentType.JSON)
               .when().get(url)
               .then().assertThat().statusCode(200)
               .and().assertThat().contentType(ContentType.JSON)
               .and().body("data.first_name[2]", Matchers.equalTo("Emma"),"data.last_name[1]", Matchers.equalTo("Weaver"));
    }


    @Test
    public void test3(){
        System.out.println("_______________POJO______________");

      Response response =  given().accept(ContentType.JSON)
                .when().get(url);


//        List<String> js = jsonPath.getList("data.first_name");
//
//        // using JsonPath extract list of spartans/ do de-serialization
//        List<Spartan1> allSpartan = jsonPath.getList("", Spartan1.class);
//        List<Spartan1>  femaleSpartans= allSpartan.stream().filter(spartan -> spartan.getFirst_name().equals("Emma")).collect(Collectors.toList());
//
//        System.out.println(femaleSpartans);




    }

}
