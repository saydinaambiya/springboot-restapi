package com.enigmacamp.restapi.service;

import com.enigmacamp.restapi.exception.EntityExistException;
import com.enigmacamp.restapi.exception.NotFoundException;
import com.enigmacamp.restapi.model.Course;
import com.enigmacamp.restapi.model.CourseType;
import com.enigmacamp.restapi.repository.CourseRepository;
import com.enigmacamp.restapi.repository.CourseTypeRepository;
import com.enigmacamp.restapi.repository.spec.CourseSpec;
import com.enigmacamp.restapi.repository.spec.SearchCriteria;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService implements ICourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseTypeRepository courseTypeRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Course> list() throws Exception {
        List<Course> courseList = courseRepository.findAll();
        if (courseList.isEmpty()) {
            throw new NotFoundException();
        }
        return courseList;
    }


    @Override
    public Course create(Course course){
        try {
            Optional<CourseType> courseType = courseTypeRepository.findById(course.getCourseType().getCourseTypeId());
            if (courseType.isEmpty()) {
                throw new NotFoundException("Course type not found");
            }
            course.setCourseType(courseType.get());
            return courseRepository.save(course);
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistException();
        }
    }

    @Override
    public Course get(String id) throws Exception {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isEmpty()) {
            throw new NotFoundException("Course not found");
        }
        return course.get();
    }

    List<Course> findByTitle(String value){
        List<Course> courseList = courseRepository.findByTitle(value);
        if (courseList.isEmpty()) {
            throw new NotFoundException("Course with title "+value+" is not found");
        }
        return courseList;
    }

    List<Course> findByDescription(String value){
        List<Course> courseList = courseRepository.findByDescription(value);
        if (courseList.isEmpty()) {
            throw new NotFoundException("Course with description "+value+" is not found");
        }
        return courseList;
    }

    @Override
    public List<Course> getBy(String keyword, String value){
        switch (keyword){
            case "title":
                return findByTitle(value);
            case "description":
                return findByDescription(value);
            default:
                return courseRepository.findAll();
        }
    }

    @Override
    public Page<Course> getByPagination(int page, int pageSize, String direction, String sortBy) {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), sortBy);
        Pageable pageable = PageRequest.of((page - 1), pageSize, sort);
        return courseRepository.findAll(pageable);
    }

    //    @Override
//    public Page<Course> findByPagination(Pageable pageable) throws Exception {
//        return courseRepository.findByPagination(pageable);
//    }

    @Override
    public void update(Course course, String id){
        try {
            Course existingCourse = get(id);
            course.setCourseId(existingCourse.getCourseId());
            courseRepository.save(course);
        } catch (Exception e) {
            throw new NotFoundException("Update failed, Course ID is not found");
        }
    }

    @Override
    public void delete(String id){
        try {
            Course existingCourse = get(id);
            courseRepository.delete(existingCourse);
        } catch (Exception e) {
            throw new NotFoundException("Delete failed, Course ID is not found");
        }
    }

    @Override
    public List<Course> getBySearch(SearchCriteria searchCriteria) {
        Specification spec = new CourseSpec().findBy(searchCriteria);
        List<Course> courseList = courseRepository.findAll(spec);
        if (courseList.isEmpty()) {
            throw new NotFoundException("Course not found");
        }
        return courseList;
    }
}
