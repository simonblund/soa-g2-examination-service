package com.g2.examinationservice.domain;

import com.g2.examinationservice.api.rest.Grade;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@Builder
public class Submission {
    UUID submissionId;
    Examination examination;
    String studentId;
    String teacherId;
    Grade grade;
    boolean verified;

}
