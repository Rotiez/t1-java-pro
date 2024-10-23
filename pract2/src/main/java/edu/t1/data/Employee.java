package edu.t1.data;

import java.time.LocalDate;

public record Employee(
        String name,
        LocalDate dateOfBirth,
        JobTitle jobTitle
) {
    @Override
    public String toString() {
        return String.join(", ", name, dateOfBirth.toString(), jobTitle.getJobTitleName());
    }
}