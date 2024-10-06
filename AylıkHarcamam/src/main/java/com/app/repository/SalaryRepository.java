package com.app.repository;

import com.app.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Month;

public interface SalaryRepository extends JpaRepository<Salary, Long> {

    boolean existsBySalaryMonthAndSalaryYear(Month salaryMonth, int salaryYear);

    Salary findByCitizenId(String citizenId);
}
