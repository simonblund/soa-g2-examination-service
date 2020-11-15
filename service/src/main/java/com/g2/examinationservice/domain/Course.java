package com.g2.examinationservice.domain;

import com.g2.examinationservice.api.rest.StudentResponse;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Builder
public class Course {
    String name;
    String code;
    List<Examination> examinations;
    List<StudentResponse> students;
}
