package com.g2.examinationservice.application.impl;

import com.g2.examinationservice.api.rest.course.CourseRequest;
import com.g2.examinationservice.application.CourseService;
import com.g2.examinationservice.domain.Course;
import com.g2.examinationservice.infrastructure.db.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository repository;
    @Override
    public Course create(CourseRequest request) {
        try{
            val course = Course.builder().courseCode(request.getCourseCode()).name(request.getName()).build();
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
