package com.example.demo.beans;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PageVO {

    private String appCode;

    private String pageCode;

    private String pageName;

    private String description;

    private String schema;

    private String assets;

}
