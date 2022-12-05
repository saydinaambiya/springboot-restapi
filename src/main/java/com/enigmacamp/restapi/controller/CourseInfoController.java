package com.enigmacamp.restapi.controller;

import com.enigmacamp.restapi.model.CourseInfo;
import com.enigmacamp.restapi.model.request.CourseInfoRequest;
import com.enigmacamp.restapi.model.response.PagingResponse;
import com.enigmacamp.restapi.model.response.SuccessResponse;
import com.enigmacamp.restapi.service.ICourseInfoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/course-info")
@Validated
public class CourseInfoController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ICourseInfoService infoService;

    @PostMapping
    public ResponseEntity createInfo(@Valid @RequestBody CourseInfoRequest courseInfo) throws Exception{
        CourseInfo newCourseInfo = modelMapper.map(courseInfo, CourseInfo.class);
        CourseInfo result = infoService.create(newCourseInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success created new course info", result));
    }

    @GetMapping ResponseEntity getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "ASC") String direction,
            @RequestParam(defaultValue = "level") String sortBy
    ) throws Exception{
        Page<CourseInfo> courseInfos = infoService.getAll(page, pageSize, direction, sortBy);
        return ResponseEntity.status(HttpStatus.OK).body(new PagingResponse<>("Success get all course info", courseInfos));
    }
}
