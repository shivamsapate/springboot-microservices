package com.shivam.employeeservice.service;

import com.shivam.employeeservice.dto.APIResponseDto;
import com.shivam.employeeservice.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    APIResponseDto getEmployeeById(Long id);
}
