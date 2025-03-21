package com.jobportal.jobApplication.repository;

import com.jobportal.jobApplication.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByCompanyId(Long companyId);
    List<Job> findByLocationContainingIgnoreCase(String location);
    List<Job> findByJobType(String jobType);
    List<Job> findByExperienceLevel(String experienceLevel);
    List<Job> findByIsActiveTrue();
    List<Job> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title, String description);
} 