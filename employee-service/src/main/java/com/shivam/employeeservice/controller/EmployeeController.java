package com.shivam.employeeservice.controller;

import com.shivam.employeeservice.dto.APIResponseDto;
import com.shivam.employeeservice.dto.EmployeeDto;
import com.shivam.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    //Build save employee rest api
    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto savedEmployeeDto = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployeeDto, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<APIResponseDto> getEmployee(@PathVariable Long id){
        APIResponseDto apiResponse = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
