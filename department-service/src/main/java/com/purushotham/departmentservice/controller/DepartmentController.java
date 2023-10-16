package com.purushotham.departmentservice.controller;

import com.purushotham.departmentservice.dto.DepartmentDTO;
import com.purushotham.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/departments")
@AllArgsConstructor
public class DepartmentController {
    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentDTO> saveDepartment(@RequestBody DepartmentDTO departmentDTO){
      DepartmentDTO saveDepartment = departmentService.saveDepartment(departmentDTO);
      return new ResponseEntity<>(saveDepartment, HttpStatus.CREATED);
    }

    @GetMapping("{departmentCode}")
    public ResponseEntity<DepartmentDTO> getDepartmentByDepartmentCode(@PathVariable("departmentCode") String departmentCode){
        DepartmentDTO departmentDTO = departmentService.getDepartmentByCode(departmentCode);
        return new ResponseEntity<>(departmentDTO, HttpStatus.OK);
    }
}
