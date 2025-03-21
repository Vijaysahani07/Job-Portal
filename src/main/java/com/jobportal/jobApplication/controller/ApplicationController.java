package com.jobportal.jobApplication.controller;

import com.jobportal.jobApplication.model.Application;
import com.jobportal.jobApplication.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "*")
public class ApplicationController {

  @Autowired
  private ApplicationService applicationService;

  @PostMapping
  public ResponseEntity<Application> submitApplication(@RequestBody Application application) {
    return ResponseEntity.ok(applicationService.submitApplication(application));
  }

  @PutMapping("/{id}/status")
  public ResponseEntity<Application> updateApplicationStatus(
      @PathVariable Long id,
      @RequestParam String status) {
    return ResponseEntity.ok(applicationService.updateApplicationStatus(id, status));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Application> getApplicationById(@PathVariable Long id) {
    return ResponseEntity.ok(applicationService.getApplicationById(id));
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<Application>> getApplicationsByUser(@PathVariable Long userId) {
    return ResponseEntity.ok(applicationService.getApplicationsByUser(userId));
  }

  @GetMapping("/job/{jobId}")
  public ResponseEntity<List<Application>> getApplicationsByJob(@PathVariable Long jobId) {
    return ResponseEntity.ok(applicationService.getApplicationsByJob(jobId));
  }

  @GetMapping("/company/{companyId}")
  public ResponseEntity<List<Application>> getApplicationsByCompany(@PathVariable Long companyId) {
    return ResponseEntity.ok(applicationService.getApplicationsByCompany(companyId));
  }

  @GetMapping("/check")
  public ResponseEntity<Boolean> hasApplied(
      @RequestParam Long userId,
      @RequestParam Long jobId) {
    return ResponseEntity.ok(applicationService.hasApplied(userId, jobId));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {
    applicationService.deleteApplication(id);
    return ResponseEntity.ok().build();
  }
}