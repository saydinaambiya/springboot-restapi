package com.enigmacamp.restapi.service;


import com.enigmacamp.restapi.exception.EntityExistException;
import com.enigmacamp.restapi.exception.NotFoundException;
import com.enigmacamp.restapi.model.CourseInfo;
import com.enigmacamp.restapi.repository.CourseInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseInfoService implements ICourseInfoService {

    @Autowired
    private CourseInfoRepository courseInfoRepository;

    @Override
    public CourseInfo create(CourseInfo courseInfo) {
        try {

            return courseInfoRepository.save(courseInfo);
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistException();
        }
    }

    @Override
    public CourseInfo get(String id) {
        Optional<CourseInfo> courseInfo = courseInfoRepository.findById(id);
        if (courseInfo.isEmpty()) {
            throw new NotFoundException("Course info not found");
        }
        return courseInfo.get();
    }

    @Override
    public Page<CourseInfo> getAll(int page, int pageSize, String direction, String sortBy) throws Exception {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), sortBy);
        Pageable pageable = PageRequest.of((page-1), pageSize, sort);
        Page<CourseInfo> result = courseInfoRepository.findAll(pageable);
        if (result.isEmpty()) {
            throw new NotFoundException();
        }
        return result;
    }
}
