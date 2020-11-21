package com.g2.examinationservice.interfaces.rest;

import com.g2.examinationservice.api.rest.UrlPaths;
import com.g2.examinationservice.api.rest.course.CourseRequest;
import com.g2.examinationservice.api.rest.examination.ExaminationCollectionResponse;
import com.g2.examinationservice.api.rest.examination.ExaminationRequest;
import com.g2.examinationservice.application.CourseService;
import com.g2.examinationservice.domain.DomainObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class TestCourseController {

    private final CourseService service;

    @PostMapping(UrlPaths.COURSE_RESOURCE)
    public ResponseEntity<Response> create(CourseRequest request){
        try {
            service.create(request);

            return ResponseEntity.status(200).build();
        }catch (Exception e){
            throw e;
        }
    }
}
