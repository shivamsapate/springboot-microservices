package com.shivam.organizationservice.services.impl;

import com.shivam.organizationservice.dto.OrganizationDto;
import com.shivam.organizationservice.entity.Organization;
import com.shivam.organizationservice.mapper.OrganizationMapper;
import com.shivam.organizationservice.repository.OrganizationRepository;
import com.shivam.organizationservice.services.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class OrganizationServiceImpl implements OrganizationService {

    private OrganizationRepository organizationRepository;
    private OrganizationMapper organizationMapper;

    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {
        Organization savedOrganization = organizationRepository.save(organizationMapper.mapToOrganizationEntity(organizationDto));
        return organizationMapper.mapToOrganizationDto(savedOrganization);
    }

    @Override
    public OrganizationDto getOrganization(String organizationCode) {
        Organization organizationEntity = organizationRepository.findByOrganizationCode(organizationCode);
        return organizationMapper.mapToOrganizationDto(organizationEntity);
    }
}
