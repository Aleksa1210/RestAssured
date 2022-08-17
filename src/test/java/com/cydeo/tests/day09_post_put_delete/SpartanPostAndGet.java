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

public class SpartanPostAndGet extends SpartanTestBase{


    Spartan newSpartan = SpartanRestUtils.getNewSpartanUT();


    @DisplayName("Test 1 ")
    @Test
    public void postSpartanThenGETTest(){

        System.out.println(newSpartan);

    Response response = given().accept(ContentType.JSON)
            .and().contentType(ContentType.JSON)
            .and().body(newSpartan)
            .when().post("/spartans");



            System.out.println("status code = " + response.statusCode());
    assertThat(response.statusCode(), is(201));

        //get id value using jsonPath
    int newSpartanID = response.jsonPath().getInt("data.id");


        //send GET request with newSpartanID and compare
        Response response1 = given().accept(ContentType.JSON)
                .and().pathParam("id", newSpartanID)
                .and().get("/spartans/{id}");

        System.out.println("get request body: " );
        response.prettyPrint();


        //convert get request jSon body to another Spartan object
        Spartan spartanFromGetRequest = response1.as(Spartan.class);
        System.out.println(spartanFromGetRequest);

        // compare Posted newSpartan
        assertThat(spartanFromGetRequest.getName(), equalTo(newSpartan.getName()));
        assertThat(spartanFromGetRequest.getGender(), equalTo(newSpartan.getGender()));
        assertThat(spartanFromGetRequest.getPhone(), equalTo(newSpartan.getPhone()));


        SpartanRestUtils.deleteSpartanById(newSpartanID);


    }







}
