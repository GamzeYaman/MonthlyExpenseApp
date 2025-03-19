package com.app.dto;

import java.time.Month;

public record FileNameRequest(String citizenId, String username, Long salaryId, int salarayYear, Month salaryMonth) {
}
