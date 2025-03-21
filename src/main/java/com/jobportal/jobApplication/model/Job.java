package com.jobportal.jobApplication.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "jobs")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    private String location;
    
    private String jobType; // FULL_TIME, PART_TIME, CONTRACT, etc.
    
    private String experienceLevel;
    
    private String salary;
    
    private String skills;
    
    private String requirements;
    
    private String responsibilities;
    
    private String benefits;
    
    @Column(nullable = false)
    private LocalDate postedDate;
    
    private LocalDate deadline;
    
    private boolean isActive;
    
    @PrePersist
    protected void onCreate() {
        postedDate = LocalDate.now();
        isActive = true;
    }
} 