package com.jobportal.jobApplication.service;

import com.jobportal.jobApplication.dto.LoginRequest;
import com.jobportal.jobApplication.dto.LoginResponse;
import com.jobportal.jobApplication.model.Company;
import com.jobportal.jobApplication.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for handling authentication-related operations
 * This includes user/company login and token generation
 */
@Service
public class AuthService {

  // Manages authentication process
  @Autowired
  private AuthenticationManager authenticationManager;

  // Service for user-related operations
  @Autowired
  private UserService userService;

  // Service for company-related operations
  @Autowired
  private CompanyService companyService;

  /**
   * Handles the login process for both users and companies
   * 
   * @param request Login credentials (email and password)
   * @return LoginResponse containing token and user/company details
   * @throws RuntimeException if authentication fails
   */
  public LoginResponse login(LoginRequest request) {
    try {
      // Attempt to authenticate the user/company
      Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

      String email = authentication.getName();
      String role = "USER";
      Long id = null;

      // Determine if the authenticated entity is a user or company
      try {
        // Try to find a user with the email
        User user = userService.getUserByEmail(email);
        id = user.getId();
      } catch (Exception e) {
        // If not a user, try to find a company
        Company company = companyService.getCompanyByEmail(email);
        id = company.getId();
        role = "COMPANY";
      }

      // Generate a token (currently a dummy implementation)
      // In production, this would be a proper JWT token
      String token = "dummy-token-" + System.currentTimeMillis();

      // Return the login response with all necessary information
      return new LoginResponse(token, id, email, role);
    } catch (AuthenticationException e) {
      throw new RuntimeException("Invalid email or password");
    }
  }
}