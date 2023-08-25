package com.company.billaris2.controllers;

import com.company.billaris2.entities.Company;
import com.company.billaris2.services.CompanyService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {

    private  final CompanyService companyService;

    @PostMapping("/create")
    public ResponseEntity<Company> createCompany(@RequestBody Company company){
        Company createdCompany = companyService.createCompany(company);
        return new ResponseEntity<>(createdCompany, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id){
        Company company = companyService.findCompanyById(id)
                .orElseThrow(() -> new EntityNotFoundException("No Company found with this ID !!"));
        return ResponseEntity.ok(company);
    }

    @GetMapping("/all-Company")
    public ResponseEntity<Company> getAllCompanies(){
        List<Company> companies = companyService.findAllCompanies();
        if (companies.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body((Company) Collections.emptyList());
        }
        return ResponseEntity.ok((Company) companies);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable Long id, @RequestBody Company company){
        companyService.findCompanyById(id)
                .orElseThrow(() -> new EntityNotFoundException("No Company found with this ID !!"));

        company.setId(id);
        Company updateCompany = companyService.updateCompany(company);
        return ResponseEntity.ok(updateCompany);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id){
        companyService.findCompanyById(id)
                .orElseThrow(() -> new EntityNotFoundException("No Company found with this ID !!"));

        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }
}
