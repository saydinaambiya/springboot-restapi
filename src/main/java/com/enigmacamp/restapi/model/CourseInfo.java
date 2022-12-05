package com.enigmacamp.restapi.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "t_course_info")
public class CourseInfo {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Getter
    @Setter
    private String courseInfoId;

    @Column(name = "duration", nullable = false, length = 100)
    @Getter
    @Setter
    private String duration;

    @Column(name = "level", nullable = false, length = 100)
    @Getter
    @Setter
    private String level;
}
