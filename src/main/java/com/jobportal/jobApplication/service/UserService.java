package com.jobportal.jobApplication.service;

import com.jobportal.jobApplication.model.User;
import com.jobportal.jobApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;

  public User registerUser(User user) {
    if (userRepository.existsByEmail(user.getEmail())) {
      throw new RuntimeException("Email already exists");
    }
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  public User updateUser(Long id, User userDetails) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found"));

    user.setFirstName(userDetails.getFirstName());
    user.setLastName(userDetails.getLastName());
    user.setPhoneNumber(userDetails.getPhoneNumber());
    user.setLocation(userDetails.getLocation());
    user.setSkills(userDetails.getSkills());
    user.setExperience(userDetails.getExperience());
    user.setEducation(userDetails.getEducation());
    user.setResumeUrl(userDetails.getResumeUrl());
    user.setDateOfBirth(userDetails.getDateOfBirth());

    return userRepository.save(user);
  }

  public User getUserById(Long id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found"));
  }

  public User getUserByEmail(String email) {
    return userRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("User not found"));
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public void deleteUser(Long id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found"));
    userRepository.delete(user);
  }

  public boolean existsByEmail(String email) {
    return userRepository.existsByEmail(email);
  }
}