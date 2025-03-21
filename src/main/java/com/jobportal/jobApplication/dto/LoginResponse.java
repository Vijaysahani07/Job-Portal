package com.jobportal.jobApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for login responses
 * This class represents the structure of data sent back to the client after
 * successful login
 */
@Data
@AllArgsConstructor
public class LoginResponse {
  // Authentication token for subsequent requests
  private String token;

  // ID of the logged-in user or company
  private Long id;

  // Email of the logged-in user or company
  private String email;

  // Role of the logged-in entity (either "USER" or "COMPANY")
  private String role;
}