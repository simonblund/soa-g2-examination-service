package com.g2.examinationservice.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/*
This class belongs in the fictional course service and is only here to provide some logical illusions.
 */
@Data
@NoArgsConstructor
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long courseId;
    private String name;
    private String courseCode;

    @OneToMany(mappedBy = "course")
    private List<Examination> examinations;

}
