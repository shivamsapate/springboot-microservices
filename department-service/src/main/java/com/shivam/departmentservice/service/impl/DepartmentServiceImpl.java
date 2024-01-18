package com.shivam.departmentservice.service.impl;

import com.shivam.departmentservice.dto.DepartmentDto;
import com.shivam.departmentservice.entity.Department;
import com.shivam.departmentservice.repository.DepartmentRepository;
import com.shivam.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository repository;
    private ModelMapper modelMapper;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        //convert department dto to department entity
        Department department = mapToEntity(departmentDto);
        Department saveDepartment = repository.save(department);

        return mapToDto(saveDepartment);
    }

    @Override
    public DepartmentDto getDepartmentByCode(String code) {
        Department department = repository.findByDepartmentCode(code);
        return mapToDto(department);
    }

    private DepartmentDto mapToDto(Department department) {
        return modelMapper.map(department, DepartmentDto.class);
    }

    private Department mapToEntity(DepartmentDto departmentDto) {
        return modelMapper.map(departmentDto, Department.class);
    }
}
