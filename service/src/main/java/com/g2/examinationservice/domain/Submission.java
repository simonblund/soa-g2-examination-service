package com.g2.examinationservice.domain;

import com.g2.examinationservice.api.rest.submission.Grade;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long submissionId;

    @ManyToOne
    @JoinColumn(name = "examination_id")
    Examination examination;

    String studentId;
    String teacherId;

    Grade grade;
    boolean verified;

}
