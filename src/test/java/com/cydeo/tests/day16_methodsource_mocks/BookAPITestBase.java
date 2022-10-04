package com.cydeo.tests.day16_methodsource_mocks;

import com.cydeo.utils.BookItAPITestBase;
import com.cydeo.utils.ExcelUtil;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.List;
import java.util.Map;

public class BookAPITestBase extends BookItAPITestBase {


    @ParameterizedTest
    @MethodSource("getUserCredentials")
    public void bookItAuthDDTTest(Map<String,String> userInfo){ // can be used asQuery parame in API call // store data to useInfo
     // This  @ParameterizedTest
        System.out.println("userInfo " + userInfo);

        given().accept(ContentType.JSON)
                .and().queryParams(userInfo)
                .when().get("/sign")
                .then().spec(responseSpec) // status code, contentType
                .and().body("accessToken", not(blankOrNullString()));


    }













    public static List<Map<String,String>> getUserCredentials(){

        String failPath = "src/test/resources/BookItQa3.xlsx";
        ExcelUtil excelUtil = new ExcelUtil(failPath, "QA3");
        List<Map<String,String>> data = excelUtil.getDataList(); // sore Excel in the List , every row will  added a new Map because  List<Map<String,String>>

        return data;

    }

    @Test
    public void loginTestUsingLoop(){
        List<Map<String,String>> allCredentials  = getUserCredentials();
        for (Map<String,String> userInfo : allCredentials){
            System.out.println("userInfo " + userInfo);
try {

  // This for loop test, better is  @ParameterizedTest
            given().accept(ContentType.JSON)
                    .and().queryParams(userInfo)
                    .when().get("/sign")
                    .then().spec(responseSpec) // status code, contentType
                    .and().body("accessToken", not(blankOrNullString()));
}catch (Throwable e){
    System.out.println(e.getMessage());
}
        }


    }















}
