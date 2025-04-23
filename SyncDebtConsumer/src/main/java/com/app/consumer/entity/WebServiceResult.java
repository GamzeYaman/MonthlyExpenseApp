package com.app.consumer.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "WEB_SERVICE_RESULT")
public class WebServiceResult {

    @Id
    @SequenceGenerator(name = "webServiceResultSequence", sequenceName = "WEB_SERVICE_RESULT_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "webServiceResultSequence")
    private Long id;

    @Column(length = 11)
    private Long citizenNo;

    private LocalDate queryDate;

    private String result;

}
