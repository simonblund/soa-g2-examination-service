package com.g2.examinationservice.interfaces.rest;

import com.g2.examinationservice.api.rest.UrlPaths;
import com.g2.examinationservice.api.rest.submission.SubmissionCollectionResponse;
import com.g2.examinationservice.api.rest.submission.SubmissionRequest;
import com.g2.examinationservice.api.rest.submission.SubmissionResponse;
import com.g2.examinationservice.api.rest.submission.SubmissionVerificationRequest;
import com.g2.examinationservice.application.SubmissionService;
import com.g2.examinationservice.domain.DomainObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class SubmissionController {
    private final SubmissionService service;


    @GetMapping(UrlPaths.SUBMISSION_RESOURCE)
    public ResponseEntity<SubmissionCollectionResponse> getAll(){
        try {
            val submissions = service.getAll()
                    .stream()
                    .map(it -> DomainObjectMapper.toSubmissionResponse(it))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(SubmissionCollectionResponse.builder().submissions(submissions).build());

        }catch (Exception e){
            throw e;
        }
    }

    @GetMapping(UrlPaths.SUBMISSION_RESOURCE+"/{examinationCode}")
    public ResponseEntity<SubmissionCollectionResponse> getSubmissionsForExamination(@PathVariable String examinationCode){
        try {
            val submissions = service.getSubmissionsForExamination(examinationCode)
                    .stream()
                    .map(it -> DomainObjectMapper.toSubmissionResponse(it))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(SubmissionCollectionResponse.builder().submissions(submissions).build());

        }catch (Exception e){
            throw e;
        }
    }

    @PostMapping(UrlPaths.SUBMISSION_RESOURCE)
    public ResponseEntity<Void> create(SubmissionRequest request){

        try {
            val submission = service.create(request);
            return ResponseEntity.status(200).build();
        }catch (Exception e){
            throw e;
        }
    }

    @PostMapping(UrlPaths.SUBMISSION_VERIFY)
    public ResponseEntity<SubmissionResponse> verify(SubmissionVerificationRequest request){

        try {
            val submission = service.verify(request.getSubmissionId());
            return ResponseEntity.ok(DomainObjectMapper.toSubmissionResponse(submission));
        }catch (Exception e){
            throw e;
        }
    }

}
