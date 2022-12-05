package com.enigmacamp.restapi.service;

import com.enigmacamp.restapi.exception.NotFoundException;
import com.enigmacamp.restapi.model.Course;
import com.enigmacamp.restapi.repository.ICourseArrayRepository;
import com.enigmacamp.restapi.repository.spec.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseArrayService implements ICourseService {
    @Override
    public Page<Course> getByPagination(int page, int pageSize, String order, String direction) {
        return null;
    }

    @Value("3")
    Integer dataLength;
    @Autowired
    private ICourseArrayRepository courseRepository;

//    @Override
//    public Page<Course> findByPagination(Pageable pageable) throws Exception {
//        return null;
//    }

    @Override
    public List<Course> list() throws Exception {
        List<Course> courseList = courseRepository.getAll();
        if (courseList.isEmpty()) {
            throw new NotFoundException();
        }
        return courseList;
    }

    @Override
    public List<Course> getBy(String keyword, String value) throws Exception {
        Optional<List<Course>> courseList = courseRepository.getBy(keyword, value);
        if (courseList.isEmpty()) {
            throw new NotFoundException("Course not found");
        }
        return courseList.get();
    }

    @Override
    public Course create(Course course) throws Exception {
        if (!(courseRepository.getAll().size() < dataLength)) {
            throw new Exception("Data is full");
        }
        return courseRepository.create(course);
    }

    @Override
    public Course get(String id) throws Exception {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isEmpty()) {
            throw new NotFoundException();
        }
        return course.get();
    }

    @Override
    public List<Course> getBySearch(SearchCriteria searchCriteria) {
        return null;
    }

    @Override
    public void update(Course course, String id) {
        try {
            Course existingCourse = get(id);
            courseRepository.update(course, existingCourse.getCourseId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String id) {
        try {
            courseRepository.delete(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}