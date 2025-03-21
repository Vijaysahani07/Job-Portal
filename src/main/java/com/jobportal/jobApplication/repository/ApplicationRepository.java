package com.jobportal.jobApplication.repository;

import com.jobportal.jobApplication.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByUserId(Long userId);
    List<Application> findByJobId(Long jobId);
    List<Application> findByJobCompanyId(Long companyId);
    boolean existsByUserIdAndJobId(Long userId, Long jobId);
} 