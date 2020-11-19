package com.g2.examinationservice.infrastructure.db;

import com.g2.examinationservice.domain.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
    Course findOneByCourseCode(String courseCode);
}
