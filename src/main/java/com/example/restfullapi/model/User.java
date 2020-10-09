package com.example.restfullapi.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@ToString
@ApiModel(description = "All detail about user.")
//@JsonIgnoreProperties(value = "brithday")
//@JsonFilter("UserFilter")
public class User    {

    // == constructors ==
    public User(Integer id, String name, Date brithday) {
        this.id = id;
        this.name = name;
        this.brithday = brithday;
    }

    public User() {
    }

    // == fields ==
    @Positive
//    @JsonIgnore
    private Integer id;
    @Size(min = 2)
    @ApiModelProperty(notes = "Name should have atleast 2 words")
    private String name;
    @Past
    @ApiModelProperty(notes = "Birth date should bi in the past")
    private Date brithday;
}
