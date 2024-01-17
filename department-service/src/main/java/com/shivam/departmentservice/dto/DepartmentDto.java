package com.shivam.departmentservice.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {
    private Long id;
    private String departmentName;
    private String departmentDescription;
    private String departmentCode;
}
