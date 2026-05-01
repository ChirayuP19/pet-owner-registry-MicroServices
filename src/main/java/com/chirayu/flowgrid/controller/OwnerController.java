package com.chirayu.flowgrid.controller;

import com.chirayu.flowgrid.dto.OwnerDTO;
import com.chirayu.flowgrid.service.OwnerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chirayu
 * @created 2026-05-01
 */

@RestController
@RequestMapping("/api/v1/owners")
@RequiredArgsConstructor
@Slf4j
public class OwnerController {

    private final OwnerService ownerService;

    @GetMapping("/{ownerId}")
    public ResponseEntity<OwnerDTO> getOwnerById(@PathVariable("ownerId") Long ownerId){
        log.info("REST request to get owner with ID: {}", ownerId);
        OwnerDTO ownerDTO = ownerService.getOwnerById(ownerId);
        log.info("Successfully retrieved owner with ID: {}", ownerId);
        return ResponseEntity.status(HttpStatus.OK).body(ownerDTO);
    }
}
