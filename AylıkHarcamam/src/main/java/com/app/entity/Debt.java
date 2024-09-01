package com.app.entity;

import com.app.common.enums.DebtType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Month;

@Data
@Entity
@Table(name = "DEBT")
public class Debt {

    @Id
    @SequenceGenerator(name = "debtSequence", sequenceName = "DEBT_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "debtSequence")
    private Long id;

    @Column(nullable = false)
    private DebtType debtType;

    @ColumnDefault("0")
    private BigDecimal debtAmount;

    @Column(nullable = false)
    private Month debtMonth;

    @Column(nullable = false)
    private short debtYear;

    @ManyToOne(targetEntity = Citizen.class, fetch = FetchType.LAZY)
    private Citizen citizen;
}
