package com.purushotham.departmentservice.service.impl;

import com.purushotham.departmentservice.dto.DepartmentDTO;
import com.purushotham.departmentservice.entity.Department;
import com.purushotham.departmentservice.repository.DepartmentRepository;
import com.purushotham.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;
    private ModelMapper modelMapper;
    @Override
    public DepartmentDTO saveDepartment(DepartmentDTO departmentDTO) {
        Department department = modelMapper.map(departmentDTO, Department.class);
        Department savedDepartment = departmentRepository.save(department);
        return modelMapper.map(savedDepartment, DepartmentDTO.class);
    }

    @Override
    public DepartmentDTO getDepartmentByCode(String departmentCode) {
        Department department = modelMapper.map(departmentRepository.findByDepartmentCode(departmentCode), Department.class);
        DepartmentDTO departmentDTO = modelMapper.map(department, DepartmentDTO.class);

        return departmentDTO;
    }
}
