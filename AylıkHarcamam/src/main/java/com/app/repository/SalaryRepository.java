package com.app.repository;

import com.app.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Month;

public interface SalaryRepository extends JpaRepository<Salary, Long> {

    boolean existsBySalaryMonthAndSalaryYearAndCitizen_Id(Month salaryMonth, int salaryYear, String citizenId);

    Salary findByCitizenId(String citizenId);
}
