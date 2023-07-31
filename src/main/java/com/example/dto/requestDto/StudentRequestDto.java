package com.example.dto.requestDto;

import com.example.enums.StudentStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentRequestDto {

    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;
    private Integer schemeId;
    private StudentStatus studentStatus;
}
