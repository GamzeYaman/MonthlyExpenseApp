package com.app.dto;

import java.time.Month;

public record FileNameRequest(String username, int salarayYear, Month salaryMonth) {
}
