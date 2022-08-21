package com.cydeo.tests.day12_jsonschema_authorization;

import com.cydeo.pojo_models_beans_dataObject.Spartan;
import com.cydeo.utils.SpartanRestUtils;
import com.cydeo.utils.SpartanTestBase;
import com.cydeo.utils.SpartanTestBase;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class PostJsonSchemaValidationTest extends SpartanTestBase {

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
     And body should match SpartanPostSchema.json schema
     */


    @DisplayName("Post Schema")
    @Test

    public void post_Json_Schema_Validation_Test(){
        Map <String,Object> newSpartan = new HashMap<>();
        newSpartan.put("gender","Male");
        newSpartan.put("name", "TestPost1");
        newSpartan.put("phone", 1234567425L);

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(newSpartan)
                .when().post("/spartans")
                .then().assertThat().statusCode(201)
                .and().body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/jsonschemas/SpartanPostSchema.json")))
                .and().log().all()
                .and().extract().jsonPath();




    }





}
