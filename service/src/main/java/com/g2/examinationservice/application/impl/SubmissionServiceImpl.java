package com.g2.examinationservice.application.impl;

import com.g2.examinationservice.api.rest.submission.SubmissionRequest;
import com.g2.examinationservice.application.ExaminationService;
import com.g2.examinationservice.application.SubmissionService;
import com.g2.examinationservice.domain.Submission;
import com.g2.examinationservice.infrastructure.db.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubmissionServiceImpl implements SubmissionService {
    private ExaminationService examinationService;
    private SubmissionRepository repository;

    public Submission create(SubmissionRequest request){
        val submission = Submission.builder()
                .examination(examinationService.getExamination(request.getModuleCode()))
                .grade(request.getGrade())
                .studentId(request.getStudentId())
                .teacherId(request.getTeacherId())
                .build();

        repository.save(submission);
        return submission;
    }

    @Override
    public List<Submission> getAll(){
        List<Submission> submissions = new ArrayList<>();
        repository.findAll().forEach(submissions::add);
        return submissions;
    }

    public Submission update(Submission submission){
        return submission;
    }


}
