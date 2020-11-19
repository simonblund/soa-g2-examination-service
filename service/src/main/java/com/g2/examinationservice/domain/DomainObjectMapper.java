package com.g2.examinationservice.domain;

import com.g2.examinationservice.api.rest.examination.ExaminationResponse;
import com.g2.examinationservice.api.rest.submission.SubmissionResponse;

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

    public static SubmissionResponse toSubmissionResponse(Submission submission){
        return SubmissionResponse.builder()
                .submissionId(submission.getSubmissionId())
                .examination(toExaminationResponse(submission.getExamination()))
                .studentId(submission.getStudentId())
                .teacherId(submission.getTeacherId())
                .grade(submission.getGrade())
                .verified(submission.isVerified())
                .createdAt(submission.getCreatedAt())
                .build();
    }
}
