package com.enigmacamp.restapi.model.request;

import com.enigmacamp.restapi.model.CourseInfo;
import com.enigmacamp.restapi.model.CourseType;
import com.enigmacamp.restapi.repository.CourseInfoRepository;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ToString
public class CourseRequest {
    @NotBlank(message = "{invalid.title.required}")
    @Getter
    @Setter
    private String title;

    @Size(min = 10, max = 250, message = "{invalid.description.size}")
    @Getter
    @Setter
    private String description;

    @NotBlank(message = "{invalid.link.required}")
    @Getter
    @Setter
    private String link;

    @Getter
    @Setter
    private CourseInfoRequest courseInfo;

    @Getter
    @Setter
    private CourseTypeIdRequest courseType;
}
