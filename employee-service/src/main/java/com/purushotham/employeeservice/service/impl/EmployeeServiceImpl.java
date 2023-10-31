package com.purushotham.employeeservice.service.impl;

import com.purushotham.employeeservice.dto.APIResponseDTO;
import com.purushotham.employeeservice.dto.DepartmentDTO;
import com.purushotham.employeeservice.dto.EmployeeDTO;
import com.purushotham.employeeservice.entity.Employee;
import com.purushotham.employeeservice.exception.EmailAlreadyExistException;
import com.purushotham.employeeservice.repository.EmployeeRepository;
import com.purushotham.employeeservice.service.APIClient;
import com.purushotham.employeeservice.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;
    private APIClient apiClient;
    private WebClient webClient;
    //private RestTemplate restTemplate;
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

    //@CircuitBreaker(name = "EMPLOYEE-SERVICE", fallbackMethod = "getDefaultDepartment")
    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Override
    public APIResponseDTO getEmployeeById(Long employeeId) {
        LOGGER.info("Inside getEmployeeById() method");

        Employee employee = employeeRepository.findById(employeeId).get();
        /*ResponseEntity<DepartmentDTO> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/departmentCode/"+employee.getDepartmentCode(),
                DepartmentDTO.class);
                DepartmentDTO departmentDTO = responseEntity.getBody();*/
        DepartmentDTO departmentDTO = webClient.get()
                .uri("http://localhost:8080/api/departments/departmentCode/"+employee.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDTO.class)
                .block();

        //DepartmentDTO departmentDTO = apiClient.getDepartmentByDepartmentCode(employee.getDepartmentCode());
        EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
        APIResponseDTO apiResponseDTO = new APIResponseDTO();
        apiResponseDTO.setEmployee(employeeDTO);
        apiResponseDTO.setDepartment(departmentDTO);
        return apiResponseDTO;
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();

        return employees.stream().map((employee -> modelMapper.map(employee, EmployeeDTO.class)))
                .collect(Collectors.toList());
    }
    public APIResponseDTO getDefaultDepartment(Long employeeId, Exception exception) {
        LOGGER.info("Inside getDefaultDepartment() method");
        Employee employee = employeeRepository.findById(employeeId).get();
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setDepartmentCode("R&D Department");
        departmentDTO.setDepartmentCode("RD001");
        departmentDTO.setDepartmentDescription("Research and Development Department");
        EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
        APIResponseDTO apiResponseDTO = new APIResponseDTO();
        apiResponseDTO.setEmployee(employeeDTO);
        apiResponseDTO.setDepartment(departmentDTO);
        return apiResponseDTO;

    }
}
