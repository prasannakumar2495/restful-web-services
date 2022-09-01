package com.prasanna.rest.webservices.restfulwebservices.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
@JsonFilter("SomeBeanFilter")
public class SomeBean {

    public SomeBean(String v1, String v2, String v3, String v4, String v5, String v6) {
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.v4 = v4;
        this.v5 = v5;
        this.v6 = v6;
    }

    private String v1;
    @JsonIgnore
    private String v2;
    private String v3;
    private String v4;
    private String v5;
    private String v6;
}
