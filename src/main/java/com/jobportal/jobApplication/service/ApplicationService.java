package com.jobportal.jobApplication.service;

import com.jobportal.jobApplication.model.Application;
import com.jobportal.jobApplication.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

  @Autowired
  private ApplicationRepository applicationRepository;

  public Application submitApplication(Application application) {
    if (applicationRepository.existsByUserIdAndJobId(application.getUser().getId(), application.getJob().getId())) {
      throw new RuntimeException("You have already applied for this job");
    }
    return applicationRepository.save(application);
  }

  public Application updateApplicationStatus(Long id, String status) {
    Application application = applicationRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Application not found"));

    application.setStatus(status);
    return applicationRepository.save(application);
  }

  public Application getApplicationById(Long id) {
    return applicationRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Application not found"));
  }

  public List<Application> getApplicationsByUser(Long userId) {
    return applicationRepository.findByUserId(userId);
  }

  public List<Application> getApplicationsByJob(Long jobId) {
    return applicationRepository.findByJobId(jobId);
  }

  public List<Application> getApplicationsByCompany(Long companyId) {
    return applicationRepository.findByJobCompanyId(companyId);
  }

  public void deleteApplication(Long id) {
    Application application = applicationRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Application not found"));
    applicationRepository.delete(application);
  }

  public boolean hasApplied(Long userId, Long jobId) {
    return applicationRepository.existsByUserIdAndJobId(userId, jobId);
  }
}