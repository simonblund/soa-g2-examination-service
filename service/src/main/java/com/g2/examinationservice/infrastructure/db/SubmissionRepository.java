package com.g2.examinationservice.infrastructure.db;

import com.g2.examinationservice.domain.Submission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionRepository extends CrudRepository<Submission, String> {
}
