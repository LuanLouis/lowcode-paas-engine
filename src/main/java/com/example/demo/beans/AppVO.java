package com.example.demo.beans;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AppVO {

    private String appCode;

    private String appName;

}
