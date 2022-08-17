package com.cydeo.tests.day09_post_put_delete;

import com.cydeo.pojo_models_beans_dataObject.Spartan;
import com.cydeo.utils.SpartanRestUtils;
import com.cydeo.utils.SpartanTestBase;
import com.cydeo.utils.SpartanTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SpartanPostTest extends SpartanTestBase {

    /**
     given accept is json and content type is json
     and body is:
     {
     "gender": "Male",
     "name": "TestPost1"
     "phone": 1234567425
     }
     When I send POST request to /spartans
     Then status code is 201
     And content type is json
     And "success" is "A Spartan is Born!"
     Data name, gender , phone matches my request details
     */


    @DisplayName("Test Post")
    @Test
    public void add_new_spartan_as_JSON_test(){
        /**
         given accept is json and content type is json
         and body is:
         {
         "gender": "Male",
         "name": "TestPost1",
         "phone": 1234567425
         }
         When I send POST request to /spartans
         Then status code is 201
         And content type is json
         And "success" is "A Spartan is Born!"
         Data name, gender , phone matches my request details
         */


            String jsonBody = "{\n" +
                    "     \"gender\": \"Male\",\n" +
                    "     \"name\": \"TestPost1\",\n" +
                    "     \"phone\": 1234567425\n" +
                    "     }";

            Response response = given().accept(ContentType.JSON)
                    .and().contentType(ContentType.JSON)
                    .and().body(jsonBody)
                    .when().post("/spartans");

            response.prettyPrint();
            System.out.println("status code = " + response.statusCode());
            assertThat(response.statusCode(), is(201));

            //verify header
            assertThat(response.contentType(), is("application/json"));

            //assign response to jsonpath
            JsonPath jsonPath = response.jsonPath();
            assertThat(jsonPath.getString("success"), equalTo("A Spartan is Born!"));
            assertThat(jsonPath.getString("data.name"), equalTo("TestPost1"));

            assertThat(jsonPath.getString("data.gender"), equalTo("Male"));
            assertThat(jsonPath.getLong("data.phone"), equalTo(1234567425L));

            //Delete the spartan after post
            //SpartanRestUtils.deleteSpartanById(192)


        //extract id of posted spartan and delete that by id  post ---> delete
        int spartanID = jsonPath.getInt("data.id");
        System.out.println("spartanID" + spartanID);
        SpartanRestUtils.deleteSpartanById(spartanID);
        }


        @DisplayName("Map Spartan - serialization (convert from java to json")
        @Test
        public void add_new_spartan_MAP_test(){
            Map<String, Object> map = new HashMap<>();

            map.put("gender", "Male");
            map.put("name","TestPost1");
            map.put("phone", 1234567425L);

            Response response = given().accept(ContentType.JSON)
                    .and().contentType(ContentType.JSON)
                    .and().body(map)  //Map  - serialization (convert from java to json)
                    .when().post("/spartans");


            response.prettyPrint();
            System.out.println("status code = " + response.statusCode());
            assertThat(response.statusCode(), is(201));




            //verify header
            assertThat(response.contentType(), is("application/json"));

            //assign response to jsonpath
            JsonPath jsonPath = response.jsonPath();
            assertThat(jsonPath.getString("success"), equalTo(("A Spartan is Born!")));
            assertThat(jsonPath.getString("data.name"), equalTo(map.get("name")));
            assertThat(jsonPath.getString("data.gender"), equalTo( map.get("gender")));
            assertThat(jsonPath.getLong("data.phone"), equalTo((long)map.get("phone")));

        }

        @DisplayName("Spartan using POJO")
        @Test
        public void and_new_spartan_POJO_test(){

            Spartan newSpartan = new Spartan();
            newSpartan.setGender("Male");
            newSpartan.setName("TestPost1");
            newSpartan.setPhone(1234567425l);


            Response response = given().accept(ContentType.JSON)
                    .and().contentType(ContentType.JSON)
                    .and().body(newSpartan)  //Map  - serialization (convert from java to json)
                    .when().post("/spartans");


            response.prettyPrint();
            System.out.println("status code = " + response.statusCode());
            assertThat(response.statusCode(), is(201));




            //verify header
            assertThat(response.contentType(), is("application/json"));

            //assign response to jsonpath
            JsonPath jsonPath = response.jsonPath();
            assertThat(jsonPath.getString("success"), equalTo(("A Spartan is Born!")));
            assertThat(jsonPath.getString("data.name"), equalTo(newSpartan.getName()));
            assertThat(jsonPath.getString("data.gender"), equalTo( newSpartan.getGender()));
            assertThat(jsonPath.getLong("data.phone"), equalTo((long)newSpartan.getPhone()));
        }



}
