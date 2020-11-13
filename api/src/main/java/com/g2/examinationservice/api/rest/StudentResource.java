package com.g2.examinationservice.api.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface StudentResource {
    @RequestMapping(method = RequestMethod.GET, path = UrlPaths.STUDENT_RESOURCE)
    ResponseEntity<StudentResponse> getStudent(@PathVariable long studentId);
}
