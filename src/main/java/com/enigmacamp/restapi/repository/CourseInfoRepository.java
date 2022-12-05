package com.enigmacamp.restapi.repository;

import com.enigmacamp.restapi.model.CourseInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseInfoRepository extends JpaRepository<CourseInfo, String> {
}
