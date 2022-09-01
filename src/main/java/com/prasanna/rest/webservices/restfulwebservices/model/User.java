package com.prasanna.rest.webservices.restfulwebservices.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class User {
    public User(int id,String name, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.dob = dob;
    }

    private int id;
    @Size(min = 2,message = "Name should have at least 2 characters.")
    @JsonProperty("user_name")
    private String name;
    @Past(message = "DOB should be in the past.")
    @JsonProperty("birth_date")
    private LocalDate dob;
}
