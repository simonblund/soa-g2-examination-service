package com.g2.examinationservice.application;

import com.g2.examinationservice.domain.Course;

public interface CourseService {
    Course create(Course course);
    Course findFromCourseCode(String courseCode);
}
