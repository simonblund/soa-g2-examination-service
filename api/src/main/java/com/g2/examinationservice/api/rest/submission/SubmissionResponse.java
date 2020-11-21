package com.g2.examinationservice.api.rest.submission;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.g2.examinationservice.api.rest.examination.ExaminationResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SubmissionResponse {
    private String submissionId;
    private ExaminationResponse examination;

    private String studentId;
    private String teacherId;
    private LocalDate createdAt;
    private String moduleCode;

    private Grade grade;
    private boolean verified;
}
