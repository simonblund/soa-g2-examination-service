package com.g2.examinationservice.interfaces.rest;

import com.g2.examinationservice.api.rest.StudentResource;
import com.g2.examinationservice.api.rest.examination.ExaminationCollectionResponse;
import com.g2.examinationservice.api.rest.examination.ExaminationResponse;
import com.g2.examinationservice.api.rest.UrlPaths;
import com.g2.examinationservice.application.ExaminationService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ExaminationController implements StudentResource{

    private ExaminationService service;

    @GetMapping(UrlPaths.EXAMINATION_RESOURCE)
    public ResponseEntity<ExaminationCollectionResponse> getAll(){
        try {
            val examinations = service.getAll();


            return ResponseEntity.ok(ExaminationCollectionResponse.builder().examinations().build())
        }catch (Exception e){
            throw e;
        }



    }

}
