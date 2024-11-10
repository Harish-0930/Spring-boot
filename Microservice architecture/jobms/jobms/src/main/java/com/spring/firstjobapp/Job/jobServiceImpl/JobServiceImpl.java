package com.spring.firstjobapp.Job.jobServiceImpl;

import com.spring.firstjobapp.Job.clients.CompanyClient;
import com.spring.firstjobapp.Job.clients.ReviewClient;
import com.spring.firstjobapp.Job.external.Company;
import com.spring.firstjobapp.Job.external.Review;
import com.spring.firstjobapp.Job.job.Job;
import com.spring.firstjobapp.Job.jobService.JobService;
import com.spring.firstjobapp.Job.mapper.JobMapper;
import com.spring.firstjobapp.Job.repository.JobRespository;
import com.spring.firstjobapp.Job.dto.JobDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {
    // private List<Job> jobs = new ArrayList<>();
    JobRespository jobRespository;

    @Autowired
    private RestTemplate restTemplate;

    private CompanyClient companyClient;
    private ReviewClient reviewClient;

    int attempt=0;

    public JobServiceImpl(JobRespository jobRespository,CompanyClient companyClient, ReviewClient reviewClient)
    {
        this.jobRespository =jobRespository;
        this.companyClient=companyClient;
        this.reviewClient=reviewClient;
    }
    private Long nextId=1L;
    @Override
   // @CircuitBreaker(name="companyBreaker",fallbackMethod = "companyBreakerFallback")
    // @Retry(name="companyBreaker",fallbackMethod = "companyBreakerFallback")
    @RateLimiter(name="companyBreaker",
            fallbackMethod = "companyBreakerFallback")
    public List<JobDTO> findAll() {
        System.out.println(
                "Attempt:"+ ++attempt
        );
        List<Job> jobs = jobRespository.findAll();
        List<JobDTO> jobDTOS = new ArrayList<>();

        //return jobs;
        return jobs.stream()
                .map(this::convertToDto).collect(Collectors.toList());
    }

    public List<String> companyBreakerFallback(Exception e) {
        List<String> list=new ArrayList<>();
        list.add("Dummy");
        return list;
    }

    private JobDTO convertToDto(Job job){

            //JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
            //jobWithCompanyDTO.setJob(job);

//            Company company=restTemplate.getForObject(
//                    "http://company-microservice:8081/companies/"+job.getCompanyId(), Company.class);

//            ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange("http://review-microservice:8083/reviews?companyId=" + job.getCompanyId(),
//                    HttpMethod.GET,
//                    null,
//                    new ParameterizedTypeReference<List<Review>>() {
//            });
        Company company = companyClient.getCompany(job.getCompanyId());

        List<Review> reviews= reviewClient.getReviews(job.getCompanyId());

           // List<Review> reviews = reviewResponse.getBody();

            JobDTO jobDTO = JobMapper.
                    mapToJobWithCompanyDto(job,company,reviews);
            //jobDTO.setCompany(company);
            return jobDTO;
    }
    @Override
    public void createJob(Job job) {
        job.setId(nextId++);
        //jobs.add(job);
        jobRespository.save(job);
    }

    @Override
    public JobDTO getById(Long id) {
//        for(Job job:jobs){
//            if(job.getId().equals(id)){
//                return job;
//            }
//        }
        Job job= jobRespository.findById(id).orElse(null);
        return convertToDto(job);
    }

    @Override
    public boolean deleteJobById(Long id) {
//        Iterator<Job> iterator = jobs.iterator();
//        while(iterator.hasNext()){
//            Job job = iterator.next();
//            if(job.getId().equals(id)){
//                iterator.remove();
//                return true;
//            }
//        }
        try
        {
            jobRespository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
        //return false;
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
//        Iterator<Job> iterator = jobs.iterator();
//        while(iterator.hasNext()){
//            Job job = iterator.next();
//            if(job.getId().equals(id)){
//                job.setTitle(updatedJob.getTitle());
//                job.setDescription(updatedJob.getDescription());
//                job.setMaxSalary(updatedJob.getMaxSalary());
//                job.setMinSalary(updatedJob.getMinSalary());
//                job.setLocation(updatedJob.getLocation());
//                return true;
//            }
//        }
        Optional<Job> jobOptional = jobRespository.findById(id);
            if(jobOptional.isPresent()){
                Job job = jobOptional.get();
                job.setTitle(updatedJob.getTitle());
                job.setDescription(updatedJob.getDescription());
                job.setMaxSalary(updatedJob.getMaxSalary());
                job.setMinSalary(updatedJob.getMinSalary());
                job.setLocation(updatedJob.getLocation());
                jobRespository.save(job);
                return true;
            }

        return false;
    }
}
