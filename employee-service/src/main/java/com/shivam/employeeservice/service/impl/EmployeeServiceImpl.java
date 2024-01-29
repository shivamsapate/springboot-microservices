package com.shivam.employeeservice.service.impl;

import com.shivam.employeeservice.dto.APIResponseDto;
import com.shivam.employeeservice.dto.DepartmentDto;
import com.shivam.employeeservice.dto.EmployeeDto;
import com.shivam.employeeservice.entity.Employee;
import com.shivam.employeeservice.exception.ResourceNotFoundException;
import com.shivam.employeeservice.repository.EmployeeRepository;
import com.shivam.employeeservice.service.APIClient;
import com.shivam.employeeservice.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private EmployeeRepository employeeRepository;

    private ModelMapper modelMapper;

//    private RestTemplate restTemplate;
    private WebClient webClient;
//    private APIClient apiClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = mapToEntity(employeeDto);

        Employee savedEmployee = employeeRepository.save(employee);

        return mapToDto(savedEmployee);
    }

    @Override
//    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment" )
    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    public APIResponseDto getEmployeeById(Long id) {
        logger.info("getEmployeeById() called");
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Employee","id", id.toString()));

//        //Api call for department service
//        ResponseEntity<DepartmentDto> responseDto = restTemplate.getForEntity("http://localhost:8081/api/departments/" + employee.getDepartmentCode(),
//                DepartmentDto.class);

        DepartmentDto departmentDto = webClient.get()
                .uri("http://localhost:8081/api/departments/" + employee.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();

//        DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(mapToDto(employee));
        apiResponseDto.setDepartment(departmentDto);
        return apiResponseDto;
    }

    //converted entity to dto
    private EmployeeDto mapToDto(Employee employee) {
        return modelMapper.map(employee, EmployeeDto.class);
    }

    private Employee mapToEntity(EmployeeDto employeeDto) {
        return modelMapper.map(employeeDto, Employee.class);
    }

    public APIResponseDto getDefaultDepartment(Long id,Exception exception) {
        logger.info("getDefaultDepartment() called");

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Employee","id", id.toString()));

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("R&D Department");
        departmentDto.setDepartmentCode("RD001");
        departmentDto.setDepartmentDescription("Research and Development Department");

        APIResponseDto apiResponseDto = new APIResponseDto();
    apiResponseDto.setEmployee(mapToDto(employee));
        apiResponseDto.setDepartment(departmentDto);
        return apiResponseDto;
    }
}
