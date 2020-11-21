package com.g2.examinationservice.infrastructure.rest;

import com.g2.studentservice.api.mock.epok.EpokModulResource;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "epok-client", url = "${integration.services.mock-service.url}")
public interface EpokClient extends EpokModulResource {
}
