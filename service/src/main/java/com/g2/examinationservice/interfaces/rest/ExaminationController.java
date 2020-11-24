package com.g2.examinationservice.interfaces.rest;

import com.g2.examinationservice.api.rest.examination.ExaminationCollectionResponse;
import com.g2.examinationservice.api.rest.UrlPaths;
import com.g2.examinationservice.api.rest.examination.ExaminationRequest;
import com.g2.examinationservice.api.rest.examination.ExaminationResponse;
import com.g2.examinationservice.application.ExaminationService;
import com.g2.examinationservice.domain.DomainObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ExaminationController{

    private final ExaminationService service;

    @GetMapping(UrlPaths.EXAMINATION_RESOURCE)
    public ResponseEntity<ExaminationCollectionResponse> getAll(){
        try {
            log.info("Endpoint {} hit with GET",UrlPaths.EXAMINATION_RESOURCE);
            val examinations = service.getAll()
                    .stream()
                    .map(it-> DomainObjectMapper.toExaminationResponse(it))
                    .collect(Collectors.toList());

            if(examinations.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(ExaminationCollectionResponse.builder().examinations(examinations).build());
        }catch (Exception e){
            throw e;
        }
    }

    @GetMapping(UrlPaths.EXAMINATION_RESOURCE+"/{moduleCode}")
    public ResponseEntity<ExaminationResponse> getOne(@PathVariable String moduleCode){
        try {
            log.info("Endpoint {} {}hit with GET",UrlPaths.SUBMISSION_RESOURCE, moduleCode);
            val examination = service.getExamination(moduleCode);
            if(examination.getModuleCode().isBlank()){
                return ResponseEntity.notFound().build();
            }

            val response = DomainObjectMapper.toExaminationResponse(examination);

            return ResponseEntity.ok(response);
        }catch (Exception e){
            throw e;
        }
    }

    @PostMapping(UrlPaths.EXAMINATION_RESOURCE)
    public ResponseEntity<Response> create(ExaminationRequest request){
        try {
            val examination = service.create(request);

            return ResponseEntity.status(200).build();
        }catch (Exception e){
            throw e;
        }
    }

}
