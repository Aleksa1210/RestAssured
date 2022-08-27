package com.cydeo.tests.day16_methodsource_mocks;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import com.cydeo.utils.ConfigurationReader;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.*;

public class MethodSourceTest {


    /**
    Data provider method
     */

    public static List<String> getCountries(){
        List <String> countries = Arrays.asList("Canada", "USA", "France", "Turkey", "Mexico", "Egypt", "Germany");

        return countries;
    }

    @ParameterizedTest
    @MethodSource("getCountries")
    public void countriesTest(String countryName){
        System.out.println("countryName " + countryName);


    }

}
