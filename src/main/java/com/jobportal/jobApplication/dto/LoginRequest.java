package com.jobportal.jobApplication.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) for login requests
 * This class represents the structure of login credentials sent from the client
 */
@Data
public class LoginRequest {
  // Email address of the user/company trying to login
  private String email;

  // Password of the user/company trying to login
  private String password;
}