package com.g2.examinationservice.interfaces.rest;

import com.g2.examinationservice.api.rest.examination.ExaminationCollectionResponse;
import com.g2.examinationservice.api.rest.UrlPaths;
import com.g2.examinationservice.api.rest.examination.ExaminationRequest;
import com.g2.examinationservice.application.ExaminationService;
import com.g2.examinationservice.domain.DomainObjectMapper;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class ExaminationController{

    private ExaminationService service;

    @GetMapping(UrlPaths.EXAMINATION_RESOURCE)
    public ResponseEntity<ExaminationCollectionResponse> getAll(){
        try {
            val examinations = service.getAll()
                    .stream()
                    .map(it-> DomainObjectMapper.toExaminationResponse(it)).collect(Collectors.toList());

            return ResponseEntity.ok(ExaminationCollectionResponse.builder().examinations(examinations).build());
        }catch (Exception e){
            throw e;
        }
    }

    @PostMapping(UrlPaths.EXAMINATION_RESOURCE)
    public ResponseEntity<Response> create(ExaminationRequest request){
        try {
            val examination = service.save(request);

            return ResponseEntity.status(200).build();
        }catch (Exception e){
            throw e;
        }
    }

}
