package com.enigmacamp.restapi.controller;

import com.enigmacamp.restapi.model.Course;
import com.enigmacamp.restapi.model.request.CourseRequest;
import com.enigmacamp.restapi.model.response.PagingResponse;
import com.enigmacamp.restapi.model.response.SuccessResponse;
import com.enigmacamp.restapi.repository.spec.SearchCriteria;
import com.enigmacamp.restapi.service.ICourseService;
import com.enigmacamp.restapi.util.QueryOperator;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;


@RestController
@RequestMapping("/courses")
@Validated
public class CourseController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ICourseService courseService;


    //    @GetMapping
//    public ResponseEntity getAllCourse() {
//        try {
//            List<Course> courseList = courseService.list();
//            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success Get All Course", courseList));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X01", e.getMessage()));
//        }
//    }
//
//    @PostMapping
//    public ResponseEntity createCourse(@Valid @RequestBody CourseRequest course) throws Exception {
//        Course newCourse = modelMapper.map(course, Course.class);
//        Course result = courseService.create(newCourse);
//        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Succes Created Course", result));
//
//    }
    @PostMapping
    public ResponseEntity createCourse(@Valid @RequestBody CourseRequest courseRequest) throws Exception {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Course newCourse = modelMapper.map(courseRequest, Course.class);
        Course result = courseService.create(newCourse);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success created a new course", result));

    }

    @GetMapping("/{id}")
    public ResponseEntity getCourseById(@PathVariable("id") String id) throws Exception {
        Course course = courseService.get(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success Find Course By ID", course));
    }

    @GetMapping(params = {"keyword", "value"})
    public ResponseEntity getBy(@RequestParam @NotBlank(message = "{invalid.keyword.required}") String keyword, @RequestParam @NotBlank(message = "{invalid.value.required}") String value) throws Exception {
        List<Course> courses = courseService.getBy(keyword, value);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Succes Get Course By " + keyword, courses));

    }

//    @GetMapping(params = {"page", "pageSize", "order", "direction"})
//    public ResponseEntity getByPagination(@RequestParam int page, @RequestParam int pageSize, @RequestParam String order, @RequestParam String direction){
//        List<Course> courseList= courseService.getByPagination(page, pageSize, order, direction);
//        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Suxxess", courseList));
//    }

    @GetMapping
    public ResponseEntity getByPagination(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "ASC") String direction,
            @RequestParam(defaultValue = "title") String sortBy) throws Exception {
        Page<Course> courses = courseService.getByPagination(page, pageSize, direction, sortBy);
        return ResponseEntity.status(HttpStatus.OK).body(new PagingResponse<>("Success get pagination", courses));
    }

    @GetMapping("/search")
    public ResponseEntity getBySeacrh(
            @RequestParam(defaultValue = "LIKE") String operator,
            @RequestParam(defaultValue = "title") String key,
            @RequestParam(defaultValue = "Java") String value
    ) throws Exception {
//        SearchCriteria searchCriteria = new SearchCriteria();
//        searchCriteria.setKey(key);
//        searchCriteria.setOperator(QueryOperator.valueOf(operator));
//        searchCriteria.setValue(value);
        SearchCriteria searchCriteria = new SearchCriteria.Builder()
                .setKey(key)
                .setOperator(QueryOperator.valueOf(operator))
                .setValue(value)
                .build();
        List<Course> courses = courseService.getBySearch(searchCriteria);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get by search", courses));
    }

    @DeleteMapping("/{id}")
    public void deleteCourseById(@PathVariable("id") String id) throws Exception {
        courseService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCourseById(@Valid @RequestBody CourseRequest course, @PathVariable("id") String id) throws Exception {
        Course existingCourse = modelMapper.map(course, Course.class);
        courseService.update(existingCourse, id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success update course", course));
    }
}
