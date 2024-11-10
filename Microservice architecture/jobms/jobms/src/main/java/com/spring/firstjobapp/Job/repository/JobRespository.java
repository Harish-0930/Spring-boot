package com.spring.firstjobapp.Job.repository;

import com.spring.firstjobapp.Job.job.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRespository extends JpaRepository<Job,Long> {
}
