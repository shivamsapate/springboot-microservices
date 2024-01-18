package com.shivam.employeeservice.service.impl;

import com.shivam.employeeservice.dto.APIResponseDto;
import com.shivam.employeeservice.dto.DepartmentDto;
import com.shivam.employeeservice.dto.EmployeeDto;
import com.shivam.employeeservice.entity.Employee;
import com.shivam.employeeservice.exception.ResourceNotFoundException;
import com.shivam.employeeservice.repository.EmployeeRepository;
import com.shivam.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    private ModelMapper modelMapper;

    private RestTemplate restTemplate;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = mapToEntity(employeeDto);

        Employee savedEmployee = employeeRepository.save(employee);

        return mapToDto(savedEmployee);
    }

    @Override
    public APIResponseDto getEmployeeById(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Employee","id", id.toString()));

        //Api call for department service
        ResponseEntity<DepartmentDto> responseDto = restTemplate.getForEntity("http://localhost:8081/api/departments/" + employee.getDepartmentCode(),
                DepartmentDto.class);

        DepartmentDto responseDepartmentDto = responseDto.getBody();

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(mapToDto(employee));
        apiResponseDto.setDepartment(responseDepartmentDto);
        return apiResponseDto;
    }

    //converted entity to dto
    private EmployeeDto mapToDto(Employee employee) {
        return modelMapper.map(employee, EmployeeDto.class);
    }

    private Employee mapToEntity(EmployeeDto employeeDto) {
        return modelMapper.map(employeeDto, Employee.class);
    }
}
