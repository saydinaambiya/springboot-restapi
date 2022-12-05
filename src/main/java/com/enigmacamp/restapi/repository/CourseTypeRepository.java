package com.enigmacamp.restapi.repository;

import com.enigmacamp.restapi.model.CourseType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseTypeRepository extends JpaRepository<CourseType, String> {
//    List<CourseType> findAllByTypeNameLike(String name);
    List<CourseType> findAll(Specification specification);
}
