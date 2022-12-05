package com.enigmacamp.restapi.repository.spec;

import com.enigmacamp.restapi.model.CourseType;
import org.springframework.data.jpa.domain.Specification;

public class CourseTypeSpec {
    public Specification<CourseType> typeNameLike(String name) {
       return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("courseTypeName"), "%" + name + "%");
    }
}
