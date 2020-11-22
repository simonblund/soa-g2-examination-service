package com.g2.examinationservice.api.rest.submission;

import com.g2.examinationservice.api.rest.UrlPaths;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public interface SubmissionResource {
    @GetMapping(UrlPaths.SUBMISSION_RESOURCE)
    ResponseEntity<SubmissionCollectionResponse> getAll();

    @GetMapping(UrlPaths.SUBMISSION_RESOURCE+"/{examinationCode}")
    ResponseEntity<SubmissionCollectionResponse> getSubmissionsForExamination(@PathVariable String examinationCode);

    @PostMapping(UrlPaths.SUBMISSION_RESOURCE)
    ResponseEntity<Void> create(SubmissionRequest request);

    @PostMapping(UrlPaths.SUBMISSION_VERIFY)
    ResponseEntity<SubmissionResponse> verify(SubmissionVerificationRequest request);
}
