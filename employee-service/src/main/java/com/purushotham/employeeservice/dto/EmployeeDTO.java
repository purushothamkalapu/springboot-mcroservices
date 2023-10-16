package com.purushotham.employeeservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Long id;
    @NotEmpty(message = "Employee First Name should not be null or Empty")
    private String firstName;
    @NotEmpty(message = "Employee Last Name should not be null or Empty")
    private String lastName;
    @NotEmpty(message = "Employee email should not be null or Empty")
    @Email(message = "Email id should be Valid format")
    private String email;
}
