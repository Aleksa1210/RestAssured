package com.cydeo.homework;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Rooms{
    private String id;
    private List<Rooms> clusters;
    private String location;
}
