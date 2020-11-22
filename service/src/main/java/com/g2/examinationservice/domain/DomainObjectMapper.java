package com.g2.examinationservice.domain;

import com.g2.examinationservice.api.rest.examination.ExaminationResponse;
import com.g2.examinationservice.api.rest.submission.SubmissionResponse;

public class DomainObjectMapper {
    public static ExaminationResponse toExaminationResponse(Examination examination){
        return ExaminationResponse.builder()
                //.courseCode(examination.getCourse().getCourseCode())
                .moduleCode(examination.getModuleCode())
                .description(examination.getDescription())
                .startTime(examination.getStartTime())
                .endTime(examination.getEndTime())
                .status(examination.getStatus())
                .teacherId(examination.getTeacherId())
                .type(examination.getType())
                .location(examination.getLocation())
                .examinationId(examination.getExaminationId())
                .courseCode(examination.getCourseCode())
                .build();

    }

    public static SubmissionResponse toSubmissionResponse(Submission submission){
        return SubmissionResponse.builder()
                .submissionId(submission.getSubmissionId())
                .moduleCode(submission.getModuleCode())
                .studentId(submission.getStudentId())
                .teacherId(submission.getTeacherId())
                .grade(submission.getGrade())
                .verified(submission.isVerified())
                .createdAt(submission.getCreatedAt())
                .build();
    }
}
