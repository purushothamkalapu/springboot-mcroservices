package com.purushotham.employeeservice.controller;

import com.purushotham.employeeservice.dto.APIResponseDTO;
import com.purushotham.employeeservice.dto.EmployeeDTO;
import com.purushotham.employeeservice.entity.Employee;
import com.purushotham.employeeservice.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employees")
@AllArgsConstructor
public class EmployeeController {
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDTO> saveEmployee(
            @Valid @RequestBody EmployeeDTO employeeDTO){
        EmployeeDTO saveEmployee = employeeService.saveEmployee(employeeDTO);
        return new ResponseEntity<>(saveEmployee, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<APIResponseDTO> getEmployeeById(@PathVariable("id") Long employeeId){
        APIResponseDTO apiResponseDTO = employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(apiResponseDTO, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

}
