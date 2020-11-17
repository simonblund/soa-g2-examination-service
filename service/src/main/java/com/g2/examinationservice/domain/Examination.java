package com.g2.examinationservice.domain;


import com.g2.examinationservice.api.rest.examination.ExaminationStatus;
import com.g2.examinationservice.api.rest.examination.ExaminationType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Examination {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long examinationId;

    String moduleCode;

    @ManyToOne
    @JoinColumn(name="course_id")
    Course course;

    @OneToMany(mappedBy = "examination")
    List<Submission> submissions;

    String teacherId;
    ExaminationType type;
    ExaminationStatus status;
    Instant startTime;
    Instant endTime;
    String location;
    String description;

}
