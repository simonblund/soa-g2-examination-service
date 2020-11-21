package com.g2.examinationservice.api.rest.course;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.g2.examinationservice.api.rest.examination.ExaminationCollectionResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CourseResponse {
    private long courseId;
    private String name;
    private String courseCode;
    private List<ExaminationCollectionResponse> examinations;
}
