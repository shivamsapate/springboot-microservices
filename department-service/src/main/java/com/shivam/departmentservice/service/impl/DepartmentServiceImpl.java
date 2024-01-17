package com.shivam.departmentservice.service.impl;

import com.shivam.departmentservice.dto.DepartmentDto;
import com.shivam.departmentservice.entity.Department;
import com.shivam.departmentservice.repository.DepartmentRepository;
import com.shivam.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository repository;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        //convert department dto to department entity
        Department department = new Department(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription(),
                departmentDto.getDepartmentCode()
        );
        Department saveDepartment = repository.save(department);

        //convert entity to dto
        return new DepartmentDto(
                saveDepartment.getId(),
                saveDepartment.getDepartmentName(),
                saveDepartment.getDepartmentDescription(),
                saveDepartment.getDepartmentCode()
        );
    }

    @Override
    public DepartmentDto getDepartmentByCode(String code) {
        Department department = repository.findByDepartmentCode(code);
        return new DepartmentDto(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription(),
                department.getDepartmentCode()
        );
    }
}
