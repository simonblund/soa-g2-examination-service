package com.g2.examinationservice.interfaces.rest;

import com.g2.examinationservice.api.rest.StudentResource;
import com.g2.examinationservice.api.rest.StudentResponse;
import com.g2.examinationservice.api.rest.UrlPaths;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ExaminationController implements StudentResource{

    @Override
    @GetMapping(UrlPaths.STUDENT_RESOURCE)
    public ResponseEntity<StudentResponse> getStudent(@PathVariable("id") long studentId){
        log.debug("getStudent hit with request studentId: {}", studentId);
        if(studentId==1l){
            val student = StudentResponse.builder().firstName("Svanmon").email("svan@mon.ax").ssn("121294-193R").studentId(""+studentId).build();
            return ResponseEntity.ok(student);
        }

        throw new RuntimeException();

    }

}
