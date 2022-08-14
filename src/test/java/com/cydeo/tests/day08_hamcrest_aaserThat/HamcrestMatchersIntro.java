package com.cydeo.tests.day08_hamcrest_aaserThat;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.containsString;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class HamcrestMatchersIntro {
    @Test
    public void numbersTest(){
        //MatcherAssert.assertThat(1+3, Matchers.is(4));

        assertThat(1 + 3 ,is(4));
        assertThat(5 + 5, equalTo(10));
        assertThat(10 + 5, is(equalTo(15)));
        assertThat(100 + 4, is(greaterThan(99)));
        // asserTrue(1004 + 4 > 99) --> JUnit
        int num = 10;
        assertThat(num, is(not(0)));
        assertThat(1 + 3, is(4));

    }
    @Test
    public void StringTest(){
        String word = "wooden spoon";
        assertThat(word, is("wooden spoon"));
        assertThat(word, equalTo("wooden spoon"));

        assertThat(word, startsWith("wo"));
        assertThat(word, startsWithIgnoringCase("WOOD"));
        assertThat(word, endsWith("on"));
        assertThat(word, endsWithIgnoringCase("ON"));
        assertThat("endsWith 'n'", word, endsWith("n"));

        //containsString  contain
        assertThat(word, containsString("den"));


        //empty String
        String str = " ";
        assertThat(str, is(blankString())); // include space
       // assertThat(str,is(emptyOrNullString())); // here was space because fail
        assertThat(str.replace(" ", ""), is(emptyOrNullString()));
        assertThat(str.trim(), is(emptyOrNullString()));
    }

    @Test
    public void listTest(){

       // List<Integer> nums1 = Arrays.asList(5,20,1,54);
        List<Integer> nums = List.of(5,20,1,54);

        List<String> words = Arrays.asList("java", "selenium", "git", "maven", "api");

        //size
        assertThat(nums, hasSize(4));
        assertThat(words, hasSize(5));

        assertThat(nums, hasItem(5));

        //contains
        assertThat(words, hasItems("git",  "api"));
        assertThat(words, hasItems("git","java"));
        assertThat(nums, containsInAnyOrder(54, 1, 20, 5));

        //everyitem
        assertThat(nums, everyItem(greaterThanOrEqualTo(0)));
        assertThat(words, everyItem(not(blankString())));






    }











}
