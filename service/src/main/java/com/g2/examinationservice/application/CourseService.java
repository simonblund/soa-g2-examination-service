package com.g2.examinationservice.application;

import com.g2.examinationservice.api.rest.course.CourseRequest;
import com.g2.examinationservice.domain.Course;

public interface CourseService {
    Course create(CourseRequest course);
    Course findFromCourseCode(String courseCode);
}
