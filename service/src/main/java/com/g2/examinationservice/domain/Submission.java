package com.g2.examinationservice.domain;

import com.g2.examinationservice.api.rest.submission.Grade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder

public class Submission {


    @Id
    private String submissionId;

    @ManyToOne
    @JoinColumn(name = "examination_id")
    private Examination examination;
    private String moduleCode;

    private String studentId;
    private String teacherId;
    private LocalDate createdAt;

    private Grade grade;
    private boolean verified;

}
