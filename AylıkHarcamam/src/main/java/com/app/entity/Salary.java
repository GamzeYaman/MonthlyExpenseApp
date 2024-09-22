package com.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Month;

@Entity
@Getter
@Setter
@Table(name = "SALARY")
public class Salary {
    @Id
    @SequenceGenerator(name = "salarySequence", sequenceName = "SALARY_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "salarySequence")
    private Long id;

    @ColumnDefault("0")
    @Column(nullable = false)
    private BigDecimal salaryAmount;

    @Column(nullable = false)
    private int salaryYear;

    @Column(nullable = false)
    private Month salaryMonth;

    @ManyToOne(targetEntity = Citizen.class, fetch = FetchType.LAZY)
    private Citizen citizen;
}
