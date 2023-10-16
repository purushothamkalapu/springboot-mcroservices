package com.purushotham.departmentservice.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {
    private Long id;
    @NotEmpty(message = "Department Name should not be null or Empty")
    private String departmentName;
    @NotEmpty(message = "Department Description should not be null or Empty")
    private String departmentDescription;
    @NotEmpty(message = "Department Code should not be null or Empty")
    private String departmentCode;
}
