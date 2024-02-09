package com.shivam.organizationservice.services;

import com.shivam.organizationservice.dto.OrganizationDto;

public interface OrganizationService {
    OrganizationDto saveOrganization(OrganizationDto organizationDto);
    OrganizationDto getOrganization(String organizationCode);

}
