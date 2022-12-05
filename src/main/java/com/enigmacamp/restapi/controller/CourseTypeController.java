package com.enigmacamp.restapi.controller;

import com.enigmacamp.restapi.model.Course;
import com.enigmacamp.restapi.model.CourseType;
import com.enigmacamp.restapi.model.request.CourseTypeRequest;
import com.enigmacamp.restapi.model.response.PagingResponse;
import com.enigmacamp.restapi.model.response.SuccessResponse;
import com.enigmacamp.restapi.service.ICourseTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/course-type")
@Validated
public class CourseTypeController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ICourseTypeService typeService;

    @PostMapping
    public ResponseEntity createType(@Valid @RequestBody CourseTypeRequest courseType)throws Exception{
        CourseType newCourseType = modelMapper.map(courseType, CourseType.class);
        CourseType result = typeService.create(newCourseType);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success created new course type", result));
    }

    @GetMapping
    public ResponseEntity getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "ASC") String direction,
            @RequestParam(defaultValue = "courseTypeName") String sortBy
    ) throws Exception{
        Page<CourseType> courseTypes = typeService.getAll(page, pageSize, direction, sortBy);
        return ResponseEntity.status(HttpStatus.OK).body(new PagingResponse<>("Success get all course type", courseTypes));
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity getCourseById(@PathVariable("id") String id){
        List<Course> courseList = typeService.getCourseById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Succes get courses list",courseList));
    }

    @GetMapping("/{name}")
    public ResponseEntity getCourseTypeByName(@PathVariable("name") String name){
        List<CourseType> result = typeService.findCourseType(name);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Succes get by name", result));
    }
}
