package com.shivam.organizationservice.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrganizationDto {
    private Long id;
    private String organizationName;
    private String organizationDescription;
    private String organizationCode;
    private LocalDateTime creationDate;
}
