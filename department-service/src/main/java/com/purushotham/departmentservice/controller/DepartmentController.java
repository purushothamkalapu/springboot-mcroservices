package com.purushotham.departmentservice.controller;

import com.purushotham.departmentservice.dto.DepartmentDTO;
import com.purushotham.departmentservice.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/departments")
@AllArgsConstructor
public class DepartmentController {
    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentDTO> saveDepartment(@Valid @RequestBody DepartmentDTO departmentDTO){
      DepartmentDTO saveDepartment = departmentService.saveDepartment(departmentDTO);
      return new ResponseEntity<>(saveDepartment, HttpStatus.CREATED);
    }

    @GetMapping("{departmentCode}")
    public ResponseEntity<DepartmentDTO> getDepartmentByDepartmentCode(@PathVariable("departmentCode") Long id){
        DepartmentDTO departmentDTO = departmentService.getDepartmentById(id);
        return new ResponseEntity<>(departmentDTO, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments(){
        List<DepartmentDTO> departments = departmentService.getAllDepartment();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }
}
