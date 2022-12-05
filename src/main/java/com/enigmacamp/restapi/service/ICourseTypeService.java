package com.enigmacamp.restapi.service;

import com.enigmacamp.restapi.model.Course;
import com.enigmacamp.restapi.model.CourseType;
import com.enigmacamp.restapi.repository.spec.CourseTypeSpec;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;


public interface ICourseTypeService {
    CourseType create(CourseType courseType) throws Exception;
    CourseType get(String id) throws Exception;
    Page<CourseType> getAll(int page, int pageSize, String direction, String sortBy) throws Exception;
    List<Course> getCourseById(String id);
    List<CourseType> findCourseType(String name);
}
