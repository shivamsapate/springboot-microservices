package com.shivam.organizationservice.controller;

import com.shivam.organizationservice.dto.OrganizationDto;
import com.shivam.organizationservice.entity.Organization;
import com.shivam.organizationservice.services.OrganizationService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/organizations")
@Slf4j
public class OrganizationController {

    private OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<OrganizationDto> saveOrganization(@RequestBody OrganizationDto organizationDto) {
        log.info("saveOrganization() called");
        return new ResponseEntity<>(organizationService.saveOrganization(organizationDto), HttpStatus.CREATED);
    }

    @GetMapping("{organizationCode}")
    public ResponseEntity<OrganizationDto> getOrganization(@PathVariable String organizationCode){
        log.info("getOrganization() called");
        return new ResponseEntity<>(organizationService.getOrganization(organizationCode), HttpStatus.OK);
    }

}
