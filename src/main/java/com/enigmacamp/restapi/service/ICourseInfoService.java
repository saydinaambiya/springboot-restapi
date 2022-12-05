package com.enigmacamp.restapi.service;

import com.enigmacamp.restapi.model.CourseInfo;
import org.springframework.data.domain.Page;

public interface ICourseInfoService {
    CourseInfo create(CourseInfo courseInfo) throws Exception;
    CourseInfo get(String id) throws Exception;
    Page<CourseInfo> getAll(int page, int pageSize, String direction, String sortBy) throws Exception;
}
