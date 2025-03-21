package com.jobportal.jobApplication.controller;

import com.jobportal.jobApplication.dto.LoginRequest;
import com.jobportal.jobApplication.dto.LoginResponse;
import com.jobportal.jobApplication.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for handling authentication-related endpoints
 * This controller exposes endpoints for login operations
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // Allows requests from any origin
public class AuthController {

  // Service that handles authentication logic
  @Autowired
  private AuthService authService;

  /**
   * Endpoint for user/company login
   * 
   * @param request Login credentials (email and password)
   * @return ResponseEntity containing the login response with token and
   *         user/company details
   */
  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
    return ResponseEntity.ok(authService.login(request));
  }
}