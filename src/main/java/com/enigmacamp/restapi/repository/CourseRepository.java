package com.enigmacamp.restapi.repository;

import com.enigmacamp.restapi.model.Course;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CourseRepository extends JpaRepository<Course, String> {
    @Query("SELECT c FROM Course c WHERE c.title LIKE %?1%")
    List<Course> findByTitle(String title);

    @Query("SELECT c FROM Course c WHERE c.description LIKE %?1%")
    List<Course> findByDescription(String description);

    @Query(value = "SELECT * FROM t_course ORDER BY title ASC LIMIT 0 OFFSET 1",nativeQuery = true)
    List<Course> findByPagination(int page, int pageSize,  String order, String direction);

    List<Course> findAll(Specification specification);
}
