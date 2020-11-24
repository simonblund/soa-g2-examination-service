package com.g2.examinationservice.interfaces.rest;

import com.g2.examinationservice.api.rest.UrlPaths;
import com.g2.examinationservice.api.rest.submission.SubmissionCollectionResponse;
import com.g2.examinationservice.api.rest.submission.SubmissionRequest;
import com.g2.examinationservice.api.rest.submission.SubmissionResponse;
import com.g2.examinationservice.api.rest.submission.SubmissionVerificationRequest;
import com.g2.examinationservice.application.SubmissionService;
import com.g2.examinationservice.domain.DomainObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SubmissionController {
    private final SubmissionService service;


    @GetMapping(UrlPaths.SUBMISSION_RESOURCE)
    public ResponseEntity<SubmissionCollectionResponse> getAll(){
        try {
            log.info("Endpoint {} hit with GET",UrlPaths.SUBMISSION_RESOURCE);
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
            log.info("Endpoint {} {}hit with GET",UrlPaths.SUBMISSION_RESOURCE, examinationCode);
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
    public ResponseEntity<SubmissionResponse> verify(@RequestBody SubmissionVerificationRequest request){

        log.info("Endpoint {} {}hit with POST",UrlPaths.SUBMISSION_VERIFY, request.getSubmissionId());
        try {
            val submission = service.verify(request.getSubmissionId());
            return ResponseEntity.ok(DomainObjectMapper.toSubmissionResponse(submission));
        }catch (Exception e){
            throw e;
        }
    }

}
