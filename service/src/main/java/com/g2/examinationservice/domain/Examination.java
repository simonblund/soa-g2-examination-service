package com.g2.examinationservice.domain;


import com.g2.examinationservice.api.rest.ExaminationStatus;
import com.g2.examinationservice.api.rest.ExaminationType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@Builder
public class Examination {

    long id;
    String examinationId;
    Course course;
    ExaminationType type;
    ExaminationStatus status;
    Instant startTime;
    Instant endTime;
    String location;
    String description;

}
