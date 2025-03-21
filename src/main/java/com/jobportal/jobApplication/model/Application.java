package com.jobportal.jobApplication.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "applications")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    @Column(nullable = false)
    private LocalDate appliedDate;

    private String status; // PENDING, REVIEWING, SHORTLISTED, REJECTED, HIRED

    private String coverLetter;

    private String resumeUrl;

    @PrePersist
    protected void onCreate() {
        appliedDate = LocalDate.now();
        if (status == null) {
            status = "PENDING";
        }
    }
} 