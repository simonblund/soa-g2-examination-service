package com.g2.examinationservice.api.rest.examination;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
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
public class ExaminationResponse {

    private long examinationId;
    private String moduleCode;
    private String courseCode;
    private String teacherId;
    private ExaminationType type;
    private ExaminationStatus status;
    private Instant startTime;
    private Instant endTime;
    private String location;
    private String description;
}
