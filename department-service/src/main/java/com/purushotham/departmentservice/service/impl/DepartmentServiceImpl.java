package com.purushotham.departmentservice.service.impl;

import com.purushotham.departmentservice.dto.DepartmentDTO;
import com.purushotham.departmentservice.entity.Department;
import com.purushotham.departmentservice.exception.DepartmentAlreadyExistException;
import com.purushotham.departmentservice.exception.ResourceNotFoundException;
import com.purushotham.departmentservice.repository.DepartmentRepository;
import com.purushotham.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;
    private ModelMapper modelMapper;
    @Override
    public DepartmentDTO saveDepartment(DepartmentDTO departmentDTO) {
        Optional<Department> optionalDepartment = departmentRepository.findByDepartmentName(departmentDTO.getDepartmentName());
        if(optionalDepartment.isPresent()){
            throw new DepartmentAlreadyExistException("Department Already Exist");
        }
        Department department = modelMapper.map(departmentDTO, Department.class);
        Department savedDepartment = departmentRepository.save(department);
        return modelMapper.map(savedDepartment, DepartmentDTO.class);
    }

    @Override
    public DepartmentDTO getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Department", "id", id)
        );
        DepartmentDTO departmentDTO = modelMapper.map(department, DepartmentDTO.class);
        return departmentDTO;
    }

    @Override
    public List<DepartmentDTO> getAllDepartment() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream().map((department -> modelMapper.map(department, DepartmentDTO.class)))
                .collect(Collectors.toList());
    }
}
