package com.enigmacamp.restapi.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CourseInfoRequest {
    @NotBlank
    @Size(min = 5, max = 100)
    @Getter
    @Setter
    private String duration;

    @NotBlank
    @Size(min = 5, max = 100)
    @Getter
    @Setter
    private String level;

}
