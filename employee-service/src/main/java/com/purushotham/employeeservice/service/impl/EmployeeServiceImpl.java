package com.purushotham.employeeservice.service.impl;

import com.purushotham.employeeservice.dto.EmployeeDTO;
import com.purushotham.employeeservice.entity.Employee;
import com.purushotham.employeeservice.repository.EmployeeRepository;
import com.purushotham.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    private ModelMapper modelMapper;
    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        Employee saveEmployee = employeeRepository.save(employee);
        return modelMapper.map(saveEmployee, EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).get();
        return modelMapper.map(employee, EmployeeDTO.class);
    }
}
