package com.company.billaris2.services;

import com.company.billaris2.entities.Company;
import com.company.billaris2.repositories.CompanyRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public Company createCompany(Company company){
        if(companyRepository.findByName(company.getName()) != null){
            throw new EntityExistsException("Company with this name already exists !!");
        }

        Company createdCompany = Company.builder()
                .name(company.getName())
                .email(company.getEmail())
                .logo(company.getLogo())
                .phoneNumber(company.getPhoneNumber())
                .description(company.getDescription())
                .build();
        return companyRepository.save(createdCompany);

    }

    public Optional<Company> findCompanyById(Long id){
        return companyRepository.findById(id);
    }

    public List<Company> findAllCompanies(){
        return companyRepository.findAll();
    }

    public Company updateCompany(Company company){
        Company existingCompany = companyRepository.findByName(company.getName());
        if (existingCompany != null && !existingCompany.getId().equals(company.getId())){
            throw new EntityExistsException("Company with this name already exists !!");
        }


        return companyRepository.save(company);
    }

    public void deleteCompany(Long id){
        if (!companyRepository.existsById(id)){
            throw new EntityExistsException("No Company found with this ID");
        }
        companyRepository.deleteById(id);
    }



}
