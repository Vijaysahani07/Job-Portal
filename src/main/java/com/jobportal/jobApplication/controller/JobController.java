package com.jobportal.jobApplication.controller;

import com.jobportal.jobApplication.model.Job;
import com.jobportal.jobApplication.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "*")
public class JobController {

  @Autowired
  private JobService jobService;

  @PostMapping
  public ResponseEntity<Job> createJob(@RequestBody Job job) {
    return ResponseEntity.ok(jobService.createJob(job));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Job> updateJob(@PathVariable Long id, @RequestBody Job jobDetails) {
    return ResponseEntity.ok(jobService.updateJob(id, jobDetails));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Job> getJobById(@PathVariable Long id) {
    return ResponseEntity.ok(jobService.getJobById(id));
  }

  @GetMapping
  public ResponseEntity<List<Job>> getAllJobs() {
    return ResponseEntity.ok(jobService.getAllJobs());
  }

  @GetMapping("/company/{companyId}")
  public ResponseEntity<List<Job>> getJobsByCompany(@PathVariable Long companyId) {
    return ResponseEntity.ok(jobService.getJobsByCompany(companyId));
  }

  @GetMapping("/search")
  public ResponseEntity<List<Job>> searchJobs(
      @RequestParam(required = false) String location,
      @RequestParam(required = false) String jobType,
      @RequestParam(required = false) String experienceLevel) {
    return ResponseEntity.ok(jobService.searchJobs(location, jobType, experienceLevel));
  }

  @GetMapping("/search/keyword")
  public ResponseEntity<List<Job>> searchJobsByKeyword(@RequestParam String keyword) {
    return ResponseEntity.ok(jobService.searchJobsByKeyword(keyword));
  }

  @GetMapping("/active")
  public ResponseEntity<List<Job>> getActiveJobs() {
    return ResponseEntity.ok(jobService.getActiveJobs());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
    jobService.deleteJob(id);
    return ResponseEntity.ok().build();
  }
}