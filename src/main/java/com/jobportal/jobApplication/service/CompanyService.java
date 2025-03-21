package com.jobportal.jobApplication.service;

import com.jobportal.jobApplication.model.Company;
import com.jobportal.jobApplication.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Service
public class CompanyService {

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public Company registerCompany(Company company) {
    if (companyRepository.existsByEmail(company.getEmail())) {
      throw new RuntimeException("Email already exists");
    }
    company.setPassword(passwordEncoder.encode(company.getPassword()));
    return companyRepository.save(company);
  }

  public Company updateCompany(Long id, Company companyDetails) {
    Company company = companyRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Company not found"));

    company.setName(companyDetails.getName());
    company.setDescription(companyDetails.getDescription());
    company.setWebsite(companyDetails.getWebsite());
    company.setLocation(companyDetails.getLocation());
    company.setIndustry(companyDetails.getIndustry());
    company.setCompanySize(companyDetails.getCompanySize());
    company.setLogoUrl(companyDetails.getLogoUrl());

    return companyRepository.save(company);
  }

  public Company getCompanyById(Long id) {
    return companyRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Company not found"));
  }

  public Company getCompanyByEmail(String email) {
    return companyRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("Company not found"));
  }

  public List<Company> getAllCompanies() {
    return companyRepository.findAll();
  }

  public void deleteCompany(Long id) {
    Company company = companyRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Company not found"));
    companyRepository.delete(company);
  }

  public boolean existsByEmail(String email) {
    return companyRepository.existsByEmail(email);
  }
}