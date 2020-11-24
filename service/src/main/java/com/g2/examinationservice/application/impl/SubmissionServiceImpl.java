package com.g2.examinationservice.application.impl;

import com.g2.examinationservice.api.rest.submission.Grade;
import com.g2.examinationservice.api.rest.submission.SubmissionRequest;
import com.g2.examinationservice.application.ExaminationService;
import com.g2.examinationservice.application.SubmissionService;
import com.g2.examinationservice.domain.Submission;
import com.g2.examinationservice.infrastructure.db.SubmissionRepository;
import com.g2.examinationservice.infrastructure.rest.CanvasClient;
import com.g2.examinationservice.infrastructure.rest.LadokClient;
import com.g2.examinationservice.infrastructure.rest.StudentServiceClient;
import com.g2.studentservice.api.mock.ladok.ResultResponse;
import com.g2.studentservice.api.rest.SsnFromStudentUserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubmissionServiceImpl implements SubmissionService {
    private final ExaminationService examinationService;
    private final SubmissionRepository repository;
    private final CanvasClient canvas;
    private final StudentServiceClient studentClient;
    private final LadokClient ladok;

    @Value("${integration.db-enabled}")
    private boolean isDbEnabled;

    @Value("${integration.mock-submission-fixed}")
    private boolean isMockFixed;

    public Submission create(SubmissionRequest request) {
        if (isDbEnabled) {
            val submission = Submission.builder()
                    .examination(examinationService.getExamination(request.getModuleCode()))
                    .grade(request.getGrade())
                    .studentId(request.getStudentId())
                    .teacherId(request.getTeacherId())
                    .build();

            return repository.save(submission);

        } else {
            log.error("Submission creating is not enabled without db");
            throw new RuntimeException("No DB active");
        }


    }

    @Override
    public List<Submission> getAll() {

        List<Submission> submissions = new ArrayList<>();
        if (isDbEnabled) {
            repository.findAll().forEach(submissions::add);
        } else {

            canvas.getAssignments().getBody().forEach(it -> {
                submissions.add(Submission.builder()
                        .moduleCode(it.getModuleId())
                        .studentId(it.getStudentId())
                        .teacherId(it.getTeacherId())
                        .submissionId(it.getAssignmentId())
                        .grade(Grade.valueOf(it.getGrade().name()))
                        .createdAt(it.getCreatedAt())
                        .build());

            });
        }
        return submissions;
    }

    @Override
    public List<Submission> getSubmissionsForExamination(String examinationCode) {

        List<Submission> submissions = new ArrayList<>();

        if (isMockFixed) {
            List<Submission> finalSubmissions = submissions;
            canvas.getAssignmentsForExamination(examinationCode).getBody()
                    .forEach(it -> {
                        finalSubmissions.add(Submission.builder()
                                .moduleCode(it.getModuleId())
                                .studentId(it.getStudentId())
                                .teacherId(it.getTeacherId())
                                .submissionId(it.getAssignmentId())
                                .grade(Grade.valueOf(it.getGrade().name()))
                                .createdAt(it.getCreatedAt())
                                .build());

                    });
            submissions = finalSubmissions;
        } else {
            val all = getAll();
            submissions = all.stream().filter(it -> it.getModuleCode().contains(examinationCode)).collect(Collectors.toList());


        }
        return submissions;
    }

    @Override
    public Submission getOne(String submissionId) {

        val response =canvas.getAssignment(submissionId).getBody().get(0);



        return Submission.builder()
                .moduleCode(response.getModuleId())
                .studentId(response.getStudentId())
                .teacherId(response.getTeacherId())
                .submissionId(response.getAssignmentId())
                .grade(Grade.valueOf(response.getGrade().name()))
                .createdAt(response.getCreatedAt())
                .build();
    }

    @Override
    public Submission verify(String submissionId) {

        val submission = getOne(submissionId);
        val ssn = studentClient.getSsnFromStudentUser(SsnFromStudentUserRequest
                .builder().studentUser(submission.getStudentId()).build()).getBody().getSsn();
        ResultResponse request = ResultResponse.builder()
                .module(submission.getModuleCode())
                .date(submission.getCreatedAt().toString())
                .grade(com.g2.studentservice.api.mock.ladok.Grade.valueOf(submission.getGrade().name()))
                .ssn(ssn)
                .build();


        ladok.newResult(request);
        submission.setVerified(true);
        return submission;
    }


}
