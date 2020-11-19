package com.g2.examinationservice.application;

import com.g2.examinationservice.api.rest.examination.ExaminationRequest;
import com.g2.examinationservice.domain.Examination;

import java.util.List;

public interface ExaminationService {
    List<Examination> getAll();
    Examination create(ExaminationRequest exam);
    Examination getExamination(String examinationCode);
    Examination getExaminationsFromCourse(String courseCode);
}
