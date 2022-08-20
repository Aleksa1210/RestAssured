package com.cydeo.tests.day10_db_vs_api_put_delete;

import com.cydeo.utils.ConfigurationReader;
import com.cydeo.utils.DBUtils;
import com.cydeo.utils.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import io.restassured.path.json.JsonPath;

public class SpartanAPIANDBVValidation extends SpartanTestBase {


    /**
     given accept is json and content type is json
     and body is:
     {
     "gender": "Male",
     "name": "PostVSDatabase"
     "phone": 1234567425
     }
     When I send POST request to /spartans
     Then status code is 201
     And content type is json
     And "success" is "A Spartan is Born!"
     When I send database query
     SELECT name, gender, phone
     FROM spartans
     WHERE spartan_id = newIdFrom Post request;
     Then name, gender , phone values must match with POST request details
     */


    @DisplayName("Post/api/spartan ->then validate db")
    @Test
    public void postNewSpartanThenValidateInDBTest(){

        Map <String,Object> postRequestMap = new HashMap<>(); // send request, test data from Api --> DATA
        postRequestMap.put("gender","Male");
        postRequestMap.put("name","PostVSDatabase");
        postRequestMap.put("phone",1234567425L);

       Response response = given().accept(ContentType.JSON) //request
                .and().contentType(ContentType.JSON) //sending json
                .and().body(postRequestMap)     // put map
                .when().post("/spartans");
        response.prettyPrint();


        assertThat(response.statusCode(), equalTo(201));
        assertThat(response.contentType(), equalTo("application/json"));



        assertThat(response.jsonPath().getString("success"), equalTo("A Spartan is Born!"));

      //  JsonPath jsonPath = new JsonPath();
      //  assertThat(jsonPath.getString("success", equalTo("A Spartan is Born!")));
      //  assertThat(response.path("success"), equalTo("A Spartan is Born!"));
        //int newSpartaID = jsonPath.getInt("data.id");


        int newSpartaID = response.path("data.id");
        System.out.println("newSpartaID" + newSpartaID);

        //data base step
        String query = "select name, GENDER, PHONE from SPARTANS where SPARTAN_ID = " + newSpartaID;

        String dbURl = ConfigurationReader.getProperty("spartan_db_url");
        String dbUser = ConfigurationReader.getProperty("spartan_db_userName");
        String dbPassword = ConfigurationReader.getProperty("spartan_db_password");
        DBUtils.createConnection(dbURl,dbUser,dbPassword);


        //run the query and get result as Map object
        Map<String, Object> dbMap = DBUtils.getRowMap(query); // get all data in the Map (FROM DATA BASE SPARTAN)
        System.out.println("dbMap" + dbMap);


        //assert/validate data from database Matches data from post request
        assertThat(dbMap.get("NAME"),equalTo(postRequestMap.get("name"))); // actual/ expected
        assertThat(dbMap.get("GENDER"),equalTo(postRequestMap.get("gender")));
        assertThat(dbMap.get("PHONE"),equalTo(postRequestMap.get("phone")+"")); // +"" - make String




        //disconnect for database
        DBUtils.destroy();





    }




}
