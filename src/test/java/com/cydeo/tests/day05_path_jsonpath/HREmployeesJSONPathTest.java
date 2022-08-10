package com.cydeo.tests.day05_path_jsonpath;

import com.cydeo.utils.ConfigurationReader;
import com.cydeo.utils.HRApiTestBase;
import com.cydeo.utils.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HREmployeesJSONPathTest extends HRApiTestBase {
    /**
     Given accept type is Json
     And query param limit is 200
     when I send GET request to /employees
     Then I can use jsonPath to query and read values from json body
     */

    @Test
    public void test(){
Response response = given().accept(ContentType.JSON)
        .and().queryParam("limit", 200)
        .when().get("/employees");

assertEquals(200, response.statusCode());


// convert/path json respond body to jsonpath object
        JsonPath jsonPath = response.jsonPath();

        System.out.println(jsonPath.getString("items[0].first_name"));
        System.out.println(jsonPath.getString("items[0].job_id"));


        //working with jsonpath list
      //  List<String> ex = jsonPath.getList("items.job_id");
        System.out.println(jsonPath.getList("items.job_id"));

        List<String> allEmail = jsonPath.getList("items.email");
        System.out.println("email" + allEmail);
        assertTrue(allEmail.contains("TGATES"));


        //LIST with filter
       // List<String> ex1 = jsonPath.getList("items.findAll{it.salary>9000}.salary");
        System.out.println(jsonPath.getList("items.findAll{it.salary>9000}.salary"));

        List<String> departID = jsonPath.getList("items.findAll{it.department_id==90}.first_name");
        System.out.println(departID);

        //List because return second value
        List<String> jobID = jsonPath.getList("items.findAll{it.job_id ='T_PROG'}.first_name");
        System.out.println(jobID);

        String salaryMax = jsonPath.getString("items.max{it.salary}.first_name");
        System.out.println(salaryMax);


        // String because return only 1 value
        String salaryMin = jsonPath.getString("items.min{it.salary}.first_name");
        System.out.println(salaryMin);



    }

}
