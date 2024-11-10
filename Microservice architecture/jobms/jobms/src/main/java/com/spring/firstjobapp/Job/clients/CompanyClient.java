package com.spring.firstjobapp.Job.clients;

import com.spring.firstjobapp.Job.external.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="company-microservice")
public interface CompanyClient
{
    @GetMapping("/companies/{id}")
    Company getCompany(@PathVariable("id") Long id);
}
