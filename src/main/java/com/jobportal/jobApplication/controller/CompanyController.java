package com.jobportal.jobApplication.controller;

import com.jobportal.jobApplication.model.Company;
import com.jobportal.jobApplication.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
@CrossOrigin(origins = "*")
public class CompanyController {

  @Autowired
  private CompanyService companyService;

  @PostMapping("/register")
  public ResponseEntity<Company> registerCompany(@RequestBody Company company) {
    return ResponseEntity.ok(companyService.registerCompany(company));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Company> updateCompany(@PathVariable Long id, @RequestBody Company companyDetails) {
    return ResponseEntity.ok(companyService.updateCompany(id, companyDetails));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
    return ResponseEntity.ok(companyService.getCompanyById(id));
  }

  @GetMapping("/email/{email}")
  public ResponseEntity<Company> getCompanyByEmail(@PathVariable String email) {
    return ResponseEntity.ok(companyService.getCompanyByEmail(email));
  }

  @GetMapping
  public ResponseEntity<List<Company>> getAllCompanies() {
    return ResponseEntity.ok(companyService.getAllCompanies());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
    companyService.deleteCompany(id);
    return ResponseEntity.ok().build();
  }
}