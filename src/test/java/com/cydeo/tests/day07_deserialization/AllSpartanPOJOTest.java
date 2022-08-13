package com.cydeo.tests.day07_deserialization;

import com.cydeo.pojo_models_beans_dataObject.Spartan;
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

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
/**
 Given accept type is json
 when I send GET request to /spartans
 Then status code is 200
 And content type is json
 And I can convert json to list of spartan pojos
 */

public class AllSpartanPOJOTest extends SpartanTestBase {

    @DisplayName("Test POJO")
    @Test
    public void spartanToPojoTest() {

        Response response = given().accept(ContentType.JSON)
                .when().get("/spartans");

        response.prettyPrint();
        assertEquals(200, response.statusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());


        // convert response to jsonPath
    JsonPath jsonPath = response.jsonPath();

    // using JsonPath extract list of spartans/ do de-serialization
        List<Spartan> allSpartan = jsonPath.getList("", Spartan.class);
        System.out.println("count "+ allSpartan.size());


        //first spartan
        System.out.println(allSpartan.get(0));



       List<Spartan>  femaleSpartans= allSpartan.stream().filter(spartan -> spartan.getGender().equals("female")).collect(Collectors.toList());

        System.out.println(femaleSpartans);


        long count = allSpartan.stream().filter(spartan -> spartan.getGender().equals("Male")).count();
        List<Spartan> maleSpartan = allSpartan.stream().filter(spartan -> spartan.getGender().equals("Male")).collect(Collectors.toList());
        System.out.println(count); // stream meaning go throw. filter-filter,



    }




}
