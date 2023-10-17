package com.purushotham.departmentservice.service;

import com.purushotham.departmentservice.dto.DepartmentDTO;

import java.util.List;

public interface DepartmentService {
    DepartmentDTO saveDepartment(DepartmentDTO departmentDTO);

    DepartmentDTO getDepartmentById(Long id);
    DepartmentDTO getDepartmentByDepartmentCode(String departmentCode);
    List<DepartmentDTO> getAllDepartment();
}
