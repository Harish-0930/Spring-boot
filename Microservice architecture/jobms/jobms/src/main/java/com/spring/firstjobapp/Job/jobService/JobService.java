package com.spring.firstjobapp.Job.jobService;

import com.spring.firstjobapp.Job.job.Job;
import com.spring.firstjobapp.Job.dto.JobDTO;

import java.util.List;

public interface JobService {
    List<JobDTO> findAll();
    void createJob(Job job);

    JobDTO getById(Long id);

    boolean deleteJobById(Long id);

    boolean updateJob(Long id, Job updatedJob);
}
