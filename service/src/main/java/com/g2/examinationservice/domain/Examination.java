package com.g2.examinationservice.domain;


import com.g2.examinationservice.api.rest.examination.ExaminationStatus;
import com.g2.examinationservice.api.rest.examination.ExaminationType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Builder
public class Examination {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long examinationId;

    private String moduleCode;

    @ManyToOne
    @JoinColumn(name="course_id")
    private Course course;

    @OneToMany(mappedBy = "examination")
    private List<Submission> submissions;

    private String teacherId;
    private ExaminationType type;
    private ExaminationStatus status;
    private Instant startTime;
    private Instant endTime;
    private String location;
    private String description;

}
