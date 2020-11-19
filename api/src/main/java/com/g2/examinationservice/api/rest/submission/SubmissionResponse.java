package com.g2.examinationservice.api.rest.submission;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.g2.examinationservice.api.rest.examination.ExaminationResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SubmissionResponse {
    long submissionId;
    ExaminationResponse examination;

    String studentId;
    String teacherId;
    Instant createdAt;

    Grade grade;
    boolean verified;
}
