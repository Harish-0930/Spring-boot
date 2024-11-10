package com.spring.firstjobapp.Job.mapper;

import com.spring.firstjobapp.Job.dto.JobDTO;
import com.spring.firstjobapp.Job.external.Company;
import com.spring.firstjobapp.Job.external.Review;
import com.spring.firstjobapp.Job.job.Job;

import java.util.List;

public class JobMapper {
    public static JobDTO mapToJobWithCompanyDto(
            Job job,
            Company company,
            List<Review> reviews
    ){
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setMaxSalary(job.getMaxSalary());
        jobDTO.setMinSalary(job.getMinSalary());
        jobDTO.setCompany(company);
        jobDTO.setReviews(reviews);

        return jobDTO;
    }
}
