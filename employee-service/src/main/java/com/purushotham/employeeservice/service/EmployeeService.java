package com.purushotham.employeeservice.service;

import com.purushotham.employeeservice.dto.APIResponseDTO;
import com.purushotham.employeeservice.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);
    APIResponseDTO getEmployeeById(Long employeeId);
    List<EmployeeDTO> getAllEmployees();
}
