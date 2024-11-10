package com.spring.companyms.Company.impl;


import com.spring.companyms.Company.company.Company;
import com.spring.companyms.Company.dto.ReviewMessage;
import com.spring.companyms.Company.repository.CompanyRespoitory;
import com.spring.companyms.Company.service.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    private CompanyRespoitory companyRespoitory;

    public CompanyServiceImpl(CompanyRespoitory companyRespoitory){
        this.companyRespoitory=companyRespoitory;
    }
    @Override
    public List<Company> getAllCompanies() {
        return companyRespoitory.findAll();
    }

    @Override
    public boolean updateCompany(Company company,Long id) {
        Optional<Company> companyOptional=companyRespoitory.findById(id);
        if(companyOptional.isPresent()){
            Company companyToUpdate = companyOptional.get();
            companyToUpdate.setDescription(company.getDescription());
            companyToUpdate.setName(company.getName());
//            companyToUpdate.setJobs(company.getJobs());
            companyRespoitory.save(companyToUpdate);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void createCompany(Company company) {
        companyRespoitory.save(company);
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        if(companyRespoitory.existsById(id)) {
            companyRespoitory.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRespoitory.findById(id).orElse(null);
    }

    @Override
    public void updateCompanyRating(ReviewMessage reviewMessage) {

    }
}
