package com.cydeo.pojo_models_beans_dataObject;

import lombok.Data;


    @Data // this will add getter and setter automaticly
    public class Spartan {
/**

 Given accept type is application/json
 And Path param id = 10
 When i send GET request to /api/spartans
 Then status code is 200
 And content type is json
 And spartan data matching:
 id > 10
 name>Lorenza
 gender >Female
 phone >3312820936
 */

    private int id;
    private String name;
    private String gender;
    private long phone;







}
