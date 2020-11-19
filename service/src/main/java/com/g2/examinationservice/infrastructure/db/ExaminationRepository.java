package com.g2.examinationservice.infrastructure.db;

import com.g2.examinationservice.domain.Examination;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExaminationRepository extends CrudRepository<Examination, Long> {
    Examination findByModuleCode(String moduleCode);
}
