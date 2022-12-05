package com.enigmacamp.restapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "t_course_type")
public class CourseType {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Getter@Setter
    private String courseTypeId;

    @Getter@Setter
    @Column(name = "type_name", nullable = false, unique = true, length = 100)
    private String courseTypeName;

    @OneToMany(mappedBy = "courseType", fetch = FetchType.EAGER)
    @Getter@Setter
    @JsonManagedReference
    private Set<Course> courseList = new HashSet<>();
}
