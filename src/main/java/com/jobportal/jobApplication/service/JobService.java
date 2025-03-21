package com.jobportal.jobApplication.service;

import com.jobportal.jobApplication.model.Job;
import com.jobportal.jobApplication.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

  @Autowired
  private JobRepository jobRepository;

  public Job createJob(Job job) {
    return jobRepository.save(job);
  }

  public Job updateJob(Long id, Job jobDetails) {
    Job job = jobRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Job not found"));

    job.setTitle(jobDetails.getTitle());
    job.setDescription(jobDetails.getDescription());
    job.setLocation(jobDetails.getLocation());
    job.setJobType(jobDetails.getJobType());
    job.setExperienceLevel(jobDetails.getExperienceLevel());
    job.setSalary(jobDetails.getSalary());
    job.setSkills(jobDetails.getSkills());
    job.setRequirements(jobDetails.getRequirements());
    job.setResponsibilities(jobDetails.getResponsibilities());
    job.setBenefits(jobDetails.getBenefits());
    job.setDeadline(jobDetails.getDeadline());
    job.setActive(jobDetails.isActive());

    return jobRepository.save(job);
  }

  public Job getJobById(Long id) {
    return jobRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Job not found"));
  }

  public List<Job> getAllJobs() {
    return jobRepository.findAll();
  }

  public List<Job> getJobsByCompany(Long companyId) {
    return jobRepository.findByCompanyId(companyId);
  }

  public List<Job> searchJobs(String location, String jobType, String experienceLevel) {
    if (location != null && !location.isEmpty()) {
      return jobRepository.findByLocationContainingIgnoreCase(location);
    }
    if (jobType != null && !jobType.isEmpty()) {
      return jobRepository.findByJobType(jobType);
    }
    if (experienceLevel != null && !experienceLevel.isEmpty()) {
      return jobRepository.findByExperienceLevel(experienceLevel);
    }
    return jobRepository.findByIsActiveTrue();
  }

  public List<Job> searchJobsByKeyword(String keyword) {
    return jobRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);
  }

  public void deleteJob(Long id) {
    Job job = jobRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Job not found"));
    jobRepository.delete(job);
  }

  public List<Job> getActiveJobs() {
    return jobRepository.findByIsActiveTrue();
  }
}