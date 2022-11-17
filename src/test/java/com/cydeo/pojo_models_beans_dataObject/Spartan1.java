package com.cydeo.pojo_models_beans_dataObject;
import lombok.Data;


@Data // this will add getter and setter automaticly

public class Spartan1 {


    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private String data;
    private int id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;
}
