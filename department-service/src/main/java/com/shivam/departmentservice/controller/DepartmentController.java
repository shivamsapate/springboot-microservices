package com.shivam.departmentservice.controller;

import com.shivam.departmentservice.dto.DepartmentDto;
import com.shivam.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/departments")
@AllArgsConstructor // constructor based dependancy injection
public class DepartmentController {

    private DepartmentService departmentService;

    // build save department rest api
    @PostMapping
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto){
        DepartmentDto savedDepartment = departmentService.saveDepartment(departmentDto);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    //Build get department rest api
    @GetMapping("{department-code}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable(value = "department-code") String departmentCode) {
        DepartmentDto departmentByCode = departmentService.getDepartmentByCode(departmentCode);
        return new ResponseEntity<>(departmentByCode, HttpStatus.OK);
    }
}
