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
    long courseId;
    String name;
    String courseCode;

    @OneToMany(mappedBy = "course")
    List<Examination> examinations;

}
