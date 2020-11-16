package com.g2.examinationservice.application;

import com.g2.examinationservice.domain.Examination;

public interface ExaminationService {
    Examination save(Examination exam);
    Examination getExamination(String examinationCode);
    Examination getExaminationsFromCourse(String courseCode);
}
