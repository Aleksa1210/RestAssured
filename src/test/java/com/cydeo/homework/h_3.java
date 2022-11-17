package com.cydeo.homework;

import io.restassured.http.Headers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.cydeo.pojo_models_beans_dataObject.Spartan;
import com.cydeo.pojo_models_beans_dataObject.Spartan1;
import com.fasterxml.jackson.databind.util.JSONPObject;
import io.restassured.path.json.JsonPath;
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
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import static org.hamcrest.Matchers.is;


public class h_3 {

    String url = "https://reqres.in/api/users";

    @DisplayName("Test1")
    @Test
    public void t_1(){
        Response response = given().accept(ContentType.JSON)
                .when().get(url);
        response.prettyPrint();

        Headers headers = response.getHeaders();
        System.out.println(headers);
        Assertions.assertEquals("Janet", response.path("data.first_name[1]"));
        JsonPath jsonPath = response.jsonPath();
        String name=   jsonPath.getString("data.first_name[2]");
        Assertions.assertEquals("Emma", name);

        List<String> listName = jsonPath.getList("data.first_name");
        System.out.println(listName);

    }


    @DisplayName("Test 2 Hamcrest")
    @Test
    public void test_2(){

        /**
         *given accept type is json
         * and path id is 1
         * When i send get request to /{id}
         * then status code is 200
         * and content type is application/json
         * and id" is 1,
         *     "first_name" is "George",
         *     "email" is "george.bluth@reqres.in",
         *     "last_name" is "Bluth"
         */

        given().accept(ContentType.JSON)
                .and().log().all().pathParam("id", 1)
                .when().get(url +"/{id}")
                .then().statusCode(200)
                .and().contentType(ContentType.JSON)
                .and().assertThat().body("data.id", is (1),
                        "data.first_name", is("George"),
                        "data.email", is("george.bluth@reqres.in"),
                        "data.last_name", is("Bluth"));
    }



    @DisplayName("POJO")
    @Test
    public void test_3(){

        Response response =  given().accept(ContentType.JSON)
                .and().log().all().pathParam("id", 1)
                .when().get(url + "/{id}");

        JsonPath jsonPath = response.jsonPath();


//  List<Spartan1> js = jsonPath.getList("", Spartan1.class);
//      System.out.println(js);

//Spartan1 spartan1 = jsonPath.getList(Spartan1.class);
//        spartan1.getFirst_name();

    }

    @DisplayName("POST")
    @Test
    public void test_4(){
        String urls ="https://8ca2869e-bd08-4bb0-8d30-7aa6ed5ed66e.mock.pstmn.io//Girls/1";
        Map<String, Object> map = new HashMap<>();

        map.put("gender", "Male");
        map.put("name","TestPost1");
        map.put("phone", 1234567425L);

        Response response = given().accept(ContentType.JSON)
                .and().body(map)
                .when().post(urls);
        response.prettyPrint();


    }




}
