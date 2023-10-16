package com.purushotham.departmentservice.service;

import com.purushotham.departmentservice.dto.DepartmentDTO;

public interface DepartmentService {
    DepartmentDTO saveDepartment(DepartmentDTO departmentDTO);

    DepartmentDTO getDepartmentByCode(String departmentCode);
}
