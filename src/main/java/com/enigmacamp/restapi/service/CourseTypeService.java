package com.enigmacamp.restapi.service;

import com.enigmacamp.restapi.exception.EntityExistException;
import com.enigmacamp.restapi.exception.NotFoundException;
import com.enigmacamp.restapi.model.Course;
import com.enigmacamp.restapi.model.CourseType;
import com.enigmacamp.restapi.repository.CourseTypeRepository;
import com.enigmacamp.restapi.repository.spec.CourseTypeSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseTypeService implements ICourseTypeService{

    @Autowired
    private CourseTypeRepository courseTypeRepository;

    @Override
    public CourseType create(CourseType courseType){
        try {
            return courseTypeRepository.save(courseType);
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistException();
        }
    }

    @Override
    public CourseType get(String id) {
        Optional<CourseType> courseType = courseTypeRepository.findById(id);
        if (courseType.isEmpty()) {
            throw new NotFoundException("Course type not found");
        }
        return courseType.get();
    }

    @Override
    public Page<CourseType> getAll(int page, int pageSize, String direction, String sortBy) throws Exception {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction),sortBy);
        Pageable pageable = PageRequest.of((page-1), pageSize, sort);
        Page<CourseType> courseTypes = courseTypeRepository.findAll(pageable);
        if (courseTypes.isEmpty()) {
            throw new NotFoundException();
        }
        return courseTypes;
    }

    @Override
    public List<Course> getCourseById(String id) {
        Optional<CourseType> courseId = courseTypeRepository.findById(id);
        return courseId.get().getCourseList().stream().collect(Collectors.toList());

    }

    @Override
    public List<CourseType> findCourseType(String name) {
        Specification<CourseType> spec = new CourseTypeSpec().typeNameLike(name);
        List<CourseType> courseTypeList = courseTypeRepository.findAll(spec);
        if (courseTypeList.isEmpty()) {
            throw new NotFoundException("Course type is not found");
        }
        return courseTypeList;

    }
}
