package com.g2.examinationservice.application;

import com.g2.examinationservice.api.rest.submission.SubmissionRequest;
import com.g2.examinationservice.domain.Submission;

import java.util.List;

public interface SubmissionService {
    Submission create(SubmissionRequest request);
    List<Submission> getAll();
  Submission verify(String submissionId);
  Submission getOne(String submissionId);
}
