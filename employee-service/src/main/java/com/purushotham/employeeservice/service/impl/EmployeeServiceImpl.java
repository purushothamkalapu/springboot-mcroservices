package com.purushotham.employeeservice.service.impl;

import com.purushotham.employeeservice.dto.EmployeeDTO;
import com.purushotham.employeeservice.entity.Employee;
import com.purushotham.employeeservice.exception.EmailAlreadyExistException;
import com.purushotham.employeeservice.repository.EmployeeRepository;
import com.purushotham.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    private ModelMapper modelMapper;
    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        Optional<Employee> optionalEmployee = employeeRepository.findByEmail(employeeDTO.getEmail());
        if(optionalEmployee.isPresent()){
            throw new EmailAlreadyExistException("Employee Email already registered....");
        }
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        Employee saveEmployee = employeeRepository.save(employee);
        return modelMapper.map(saveEmployee, EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).get();
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();

        return employees.stream().map((employee -> modelMapper.map(employee, EmployeeDTO.class)))
                .collect(Collectors.toList());
    }
}
