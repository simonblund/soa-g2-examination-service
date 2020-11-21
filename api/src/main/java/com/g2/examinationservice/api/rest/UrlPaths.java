package com.g2.examinationservice.api.rest;

public class UrlPaths {
    public static final String BASE_URI = "/examination-service";
    public static final String V1 = "/V1";
    public static final String EXAMINATION_RESOURCE = BASE_URI + V1 + "/examination";
    public static final String EXAMINATION = EXAMINATION_RESOURCE + "/{moduleCode}";
    public static final String EXAMINATION_SUBMISSIONS = EXAMINATION + "/submissions";


    public static final String SUBMISSION_RESOURCE = BASE_URI + V1 + "/submission";
    public static final String SUBMISSION = SUBMISSION_RESOURCE + "/{submissionId}";
    public static final String SUBMISSION_VERIFY = SUBMISSION + "/verify";

    public static final String COURSE_RESOURCE = BASE_URI + V1 + "/course";
}

