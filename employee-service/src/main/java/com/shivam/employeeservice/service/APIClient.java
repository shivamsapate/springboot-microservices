package com.shivam.employeeservice.service;

import com.shivam.employeeservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DEPARTMENT-SERVICE") //service register on eureka client
public interface APIClient {

    //Build get department rest api
    @GetMapping("api/departments/{department-code}")
    DepartmentDto getDepartment(@PathVariable(value = "department-code") String departmentCode);
}
