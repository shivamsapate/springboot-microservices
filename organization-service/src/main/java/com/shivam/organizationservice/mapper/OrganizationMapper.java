package com.shivam.organizationservice.mapper;

import com.shivam.organizationservice.dto.OrganizationDto;
import com.shivam.organizationservice.entity.Organization;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class OrganizationMapper {

    private ModelMapper modelMapper;

    public OrganizationDto mapToOrganizationDto(Organization organization){
        return modelMapper.map(organization, OrganizationDto.class);
    }

    public Organization mapToOrganizationEntity(OrganizationDto organizationDto){
        return modelMapper.map(organizationDto, Organization.class);
    }
}
