package com.g2.examinationservice.application.impl;

import com.g2.examinationservice.api.rest.examination.ExaminationRequest;
import com.g2.examinationservice.application.CourseService;
import com.g2.examinationservice.application.ExaminationService;
import com.g2.examinationservice.domain.Examination;
import com.g2.examinationservice.infrastructure.db.ExaminationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExaminationServiceImpl implements ExaminationService {
    private ExaminationRepository repository;
    private CourseService courseService;

    @Override
    public List<Examination> getAll() {
        List<Examination> examinations = new ArrayList<Examination>();
        repository.findAll().forEach(examinations::add);
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
