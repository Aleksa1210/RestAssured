package com.cydeo.tests.day05_path_jsonpath;

import com.cydeo.utils.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import com.cydeo.utils.SpartanTestBase;

public class ZipCodeApiTest {
  /**
    Given accept type is json
    and country path param value is "us"
    and postal code path param value is 22102
    When I send get request to http://api.zippopotam.us/{country}/{postal-code}
    Then status code is 200
    Then "post code" is "22102"
    And  "country" is "United States"
    And "place name" is "Mc Lean"
    And  "state" is "Virginia"
    */

    @BeforeAll
    public static void setUp(){
      System.out.println("Setting up base URL");
    baseURI = ConfigurationReader.getProperty("zip_code_url");
}



    @DisplayName("Test01_Zip_Code")
    @Test
    public void zip_code_api(){
    Response response = given().accept(ContentType.JSON)
            .and().pathParam("country", "us")
            .and().pathParam("postal-code", "22102")
            .when().get("/{country}/{postal-code}"); //   /{us}/{22102}

            response.prettyPrint();

      JsonPath jsonPath = response.jsonPath();
      assertEquals(200, response.statusCode());

      System.out.println("country name = " + jsonPath.getString("country"));
      assertEquals("United States", jsonPath.getString("country"));



    System.out.println(jsonPath.getString("'post code'"));
    String zipcode = jsonPath.getString("'post code'");
    assertEquals("22102", zipcode);

    assertEquals("Mc Lean", jsonPath.getString("places[0].'place name'"));

    assertEquals("Virginia", jsonPath.getString("places[0].state"));

 //verifyZipCode( jsonPath, "22102"); not working
}

public void verifyZipCode(JsonPath jsonPath, String expZipCode){
        assertEquals(expZipCode, jsonPath.getString("''post code'"));

}


}
