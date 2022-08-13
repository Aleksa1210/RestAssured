package com.cydeo.tests.day06_;


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

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class SpartanToPojoClass extends SpartanTestBase{


    @DisplayName("GET /spartan/{id} -> pojo object")
    @Test
    public void spartanToPojoTest() {

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 10)
                .when().get("/spartans/{id}");

        response.prettyPrint();

        //DE-SERIALIZATION . Json -> Pojo object. Jackson is doing all the work in background
        Spartan spartan = response.as(Spartan.class);
        System.out.println("spartan = " + spartan);

        //now we can use getters to read values:
        System.out.println("id = " + spartan.getId());
        System.out.println("name = " + spartan.getName());
        System.out.println("gender = " + spartan.getGender());
        System.out.println("phone = " + spartan.getPhone());


    }


}
