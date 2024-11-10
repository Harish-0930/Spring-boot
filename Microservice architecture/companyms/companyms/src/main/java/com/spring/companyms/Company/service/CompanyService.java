package com.spring.companyms.Company.service;



import com.spring.companyms.Company.company.Company;
import com.spring.companyms.Company.dto.ReviewMessage;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();
    boolean updateCompany(Company company, Long id);
    void createCompany(Company company);
    boolean deleteCompanyById(Long id);
    Company getCompanyById(Long id);

    public void updateCompanyRating(ReviewMessage reviewMessage);
}
