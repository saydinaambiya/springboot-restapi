package com.enigmacamp.restapi.repository;

import com.enigmacamp.restapi.model.Course;

import java.util.List;
import java.util.Optional;

public interface ICourseArrayRepository {
    List<Course> getAll() throws Exception;
    Optional<List<Course>> getBy(String keyword, String value) throws Exception;
    Course create(Course course) throws Exception;
    Optional<Course> findById(String id) throws Exception;
    void update(Course course, String id) throws Exception;
    void delete(String id) throws Exception;
    void addToBucket(String keyword, String value, List<Course> bucket, Course course);
}
