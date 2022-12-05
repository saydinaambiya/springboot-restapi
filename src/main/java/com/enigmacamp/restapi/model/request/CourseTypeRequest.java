package com.enigmacamp.restapi.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CourseTypeRequest {
    @NotBlank
    @Getter
    @Setter
    @Size(min = 3, max = 100)
    private String courseTypeName;

}
