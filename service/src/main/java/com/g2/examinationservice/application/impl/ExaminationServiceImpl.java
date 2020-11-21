package com.g2.examinationservice.application.impl;

import com.g2.examinationservice.api.rest.examination.ExaminationRequest;
import com.g2.examinationservice.api.rest.examination.ExaminationStatus;
import com.g2.examinationservice.application.CourseService;
import com.g2.examinationservice.application.ExaminationService;
import com.g2.examinationservice.domain.Examination;
import com.g2.examinationservice.infrastructure.db.ExaminationRepository;
import com.g2.examinationservice.infrastructure.rest.EpokClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExaminationServiceImpl implements ExaminationService {
    private final ExaminationRepository repository;
    private final CourseService courseService;
    private final EpokClient epokClient;


    @Value("${integration.db-enabled}")
    private boolean isDbEnabled;

    @Override
    public List<Examination> getAll() {
        List<Examination> examinations = new ArrayList<Examination>();
        if(isDbEnabled){
            repository.findAll().forEach(examinations::add);
        }else {
            epokClient.getAll().getBody().forEach(it->{
                    examinations.add(
                            Examination.builder()
                            .moduleCode(it.getCode())
                            .description(it.getDescription())
                            .status(ExaminationStatus.valueOf(it.getStatus().name()))
                            .build()
                    );}
            );
        }
        return examinations;
    }

    @Override
    public Examination create(ExaminationRequest exam) {
        return repository.save(Examination.builder()
                .course(courseService.findFromCourseCode(exam.getCourseCode()))
                .description(exam.getDescription())
                .moduleCode(generateModuleId(exam.getCourseCode()))
                .teacherId(exam.getTeacherId())
                .startTime(exam.getStartTime())
                .endTime(exam.getEndTime())
                .location(exam.getLocation())
                .type(exam.getType())
                .status(exam.getStatus())
                .build()
        );
    }

    @Override
    public Examination getExamination(String examinationCode) {
        return repository.findByModuleCode(examinationCode);
    }

    @Override
    public Examination getExaminationsFromCourse(String courseCode) {
        return null;
    }

    private String generateModuleId(String courseCode){
        return courseCode + LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
