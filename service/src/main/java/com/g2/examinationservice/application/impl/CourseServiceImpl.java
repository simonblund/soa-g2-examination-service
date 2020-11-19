package com.g2.examinationservice.application.impl;

import com.g2.examinationservice.application.CourseService;
import com.g2.examinationservice.domain.Course;
import com.g2.examinationservice.infrastructure.db.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository repository;
    @Override
    public Course create(Course course) {
        try{
            return repository.save(course);

        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public Course findFromCourseCode(String courseCode) {
        return repository.findOneByCourseCode(courseCode);
    }
}
