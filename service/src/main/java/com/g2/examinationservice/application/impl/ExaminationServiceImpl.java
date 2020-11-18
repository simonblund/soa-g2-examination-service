package com.g2.examinationservice.application.impl;

import com.g2.examinationservice.api.rest.examination.ExaminationRequest;
import com.g2.examinationservice.api.rest.examination.ExaminationResponse;
import com.g2.examinationservice.application.ExaminationService;
import com.g2.examinationservice.domain.DomainObjectMapper;
import com.g2.examinationservice.domain.Examination;
import com.g2.examinationservice.infrastructure.db.ExaminationRepository;

import java.util.ArrayList;
import java.util.List;

public class ExaminationServiceImpl implements ExaminationService {
    private ExaminationRepository repository;
    @Override
    public List<Examination> getAll() {
        List<Examination> examinations = new ArrayList<Examination>();
        repository.findAll().forEach(examinations::add);
        return examinations;
    }

    @Override
    public Examination save(ExaminationRequest exam) {
        repository.save(Examination.builder()
                .course())
    }

    @Override
    public Examination getExamination(String examinationCode) {
        return null;
    }

    @Override
    public Examination getExaminationsFromCourse(String courseCode) {
        return null;
    }
}
