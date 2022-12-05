package com.enigmacamp.restapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "t_course")
@ToString
public class Course {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Getter
    @Setter
    private String courseId;

    @Column(name = "title", nullable = false, length = 150, unique = true)
    @Getter
    @Setter
    private String title;

    @Column(name = "description", nullable = false, length = 250)
    @Getter
    @Setter
    private String description;

    @Column(name = "link", nullable = false, length = 200)
    @Getter
    @Setter
    private String link;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_info_id")
    @Getter
    @Setter
    private CourseInfo courseInfo;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_type_id")
    @Getter@Setter
    @JsonBackReference
    private CourseType courseType;

}
