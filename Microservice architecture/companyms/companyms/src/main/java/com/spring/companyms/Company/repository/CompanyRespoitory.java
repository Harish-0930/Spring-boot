package com.spring.companyms.Company.repository;


import com.spring.companyms.Company.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRespoitory extends JpaRepository<Company,Long> {
}
