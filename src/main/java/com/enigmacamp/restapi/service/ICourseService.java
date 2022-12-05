package com.enigmacamp.restapi.service;

import com.enigmacamp.restapi.model.Course;
import com.enigmacamp.restapi.repository.spec.SearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICourseService {
    List<Course> list() throws Exception;
    List<Course> getBy(String keyword, String value) throws Exception;
    Page<Course> getByPagination(int page, int pageSize, String direction, String sortBy) throws Exception;
    Course create(Course course) throws Exception;
    Course get(String id) throws Exception;
    List<Course> getBySearch(SearchCriteria searchCriteria);
    void update(Course course, String id) throws Exception;
    void delete(String id) throws Exception;
}
