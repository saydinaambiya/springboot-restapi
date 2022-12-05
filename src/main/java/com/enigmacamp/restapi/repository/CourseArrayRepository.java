package com.enigmacamp.restapi.repository;

import com.enigmacamp.restapi.model.Course;
import com.enigmacamp.restapi.util.IRandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseArrayRepository implements ICourseArrayRepository {
    @Autowired
    IRandomStringGenerator randomStringGenerator;
    private List<Course> courses = new ArrayList<>();

    @Override
    public List<Course> getAll() throws Exception {
        return courses;
    }

    @Override
    public void addToBucket(String keyword, String value, List<Course> bucket, Course course) {
        if (keyword.toLowerCase().contains(value)) {
            bucket.add(course);
        }
    }

    @Override
    public Optional<List<Course>> getBy(String keyword, String value) throws Exception {
        List<Course> resultCourse = new ArrayList<>();
        for (Course course : courses) {
            switch (keyword) {
                case "courseId":
                    addToBucket(course.getCourseId(), value.toLowerCase(), resultCourse, course);
                    break;
                case "title":
                    addToBucket(course.getTitle(), value.toLowerCase(), resultCourse, course);
                    break;
                case "description":
                    addToBucket(course.getDescription(), value.toLowerCase(), resultCourse, course);
                    break;
                case "link":
                    addToBucket(course.getLink(), value.toLowerCase(), resultCourse, course);
                    break;
            }
        }
        return resultCourse.isEmpty()  ? Optional.empty() : Optional.of(resultCourse);
    }

    @Override
    public Course create(Course course) throws Exception {
        course.setCourseId(randomStringGenerator.random());
        courses.add(course);
        return course;
    }

    @Override
    public Optional<Course> findById(String id) throws Exception {
        for (Course course : courses) {
            if (course.getCourseId().equals(id)) {
                return Optional.of(course);
            }
        }
        return Optional.empty();
    }

    @Override
    public void update(Course course, String id) throws Exception {
        for (Course existingCourse : courses) {
            if (existingCourse.getCourseId().equals(id)) {
                existingCourse.setTitle(course.getTitle());
                existingCourse.setDescription(course.getDescription());
                existingCourse.setLink(course.getLink());
                break;
            }
        }
    }

    @Override
    public void delete(String id) throws Exception {
        for (Course course : courses) {
            if (course.getCourseId().equals(id)) {
                courses.remove(course);
                break;
            }
        }
    }
}