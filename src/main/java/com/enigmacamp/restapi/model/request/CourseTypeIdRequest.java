package com.enigmacamp.restapi.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

public class CourseTypeIdRequest {
    @NotBlank
    @Getter@Setter
    private String courseTypeId;
}
