package com.g2.examinationservice.domain;

import com.g2.examinationservice.api.rest.examination.ExaminationResponse;

public class DomainObjectMapper {
    public static ExaminationResponse toExaminationResponse(Examination examination){
        return ExaminationResponse.builder()
                .courseCode(examination.getCourse().getCourseCode())
                .moduleCode(examination.getModuleCode())
                .description(examination.getDescription())
                .startTime(examination.getStartTime())
                .endTime(examination.getEndTime())
                .status(examination.getStatus())
                .teacherId(examination.getTeacherId())
                .type(examination.getType())
                .location(examination.getLocation())
                .examinationId(examination.getExaminationId())
                .build();

    }
}
