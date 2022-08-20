package com.cydeo.tests.day11_put_patch_request;
import com.cydeo.utils.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import com.cydeo.utils.SpartanRestUtils;
import com.cydeo.utils.SpartanTestBase;








public class SpartanPUTRequest extends SpartanTestBase {
/**
    Given accept type is json
    And content type is json
    And path param id is 15
    And json body is
    {
        "gender": "Male",
            "name": "PutRequest"
        "phone": 1234567425
    }
    When i send PUT request to /spartans/{id}
    Then status code 204
            */


    @DisplayName("PUT / spartan/{id}")
    @Test
    public void spartan_test_base(){
        Map <String, Object>  requestMap = new HashMap<>(); // update exesting one
        requestMap.put("gender","Male"); // all update
        requestMap.put("name", "PutRequest");
        requestMap.put("phone",1234567425L);

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().pathParam("id", 1) // update exesting one
                .and().body(requestMap)
                .when().put("/spartans/{id}");


        response.prettyPrint();
        System.out.println(response.statusCode());

        assertThat(response.statusCode(), is(204));














    }


}
