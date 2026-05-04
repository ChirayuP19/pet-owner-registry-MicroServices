package com.chirayu.flowgrid.controller;

import com.chirayu.flowgrid.dto.OwnerDTO;
import com.chirayu.flowgrid.dto.UpdatePetDTO;
import com.chirayu.flowgrid.exception.OwnerNotFoundException;
import com.chirayu.flowgrid.service.OwnerService;
import com.chirayu.flowgrid.util.OwnerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    private final OwnerMapper ownerMapper;

    @GetMapping("/{ownerId}")
    public ResponseEntity<OwnerDTO> getOwnerById(@PathVariable Long ownerId){
        log.info("REST request to get owner with ID: {}", ownerId);
        OwnerDTO ownerDTO = ownerService.getOwnerById(ownerId);
        log.info("Successfully retrieved owner with ID: {}", ownerId);
        return ResponseEntity.status(HttpStatus.OK).body(ownerDTO);
    }

    @PostMapping
    public ResponseEntity<Long> saveOwner(@RequestBody OwnerDTO ownerDTO) {
        Long ownerId = ownerService.saveOwner(ownerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ownerId);
    }

    @PatchMapping("/{ownerId}")
    public ResponseEntity<Void> updatePetDetails(@PathVariable Long ownerId, @RequestBody UpdatePetDTO updatePetDTO)
            throws OwnerNotFoundException {
        ownerService.updatePetDetails(ownerId, updatePetDTO.name());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{ownerId}")
    public ResponseEntity<Void> deleteOwner(@PathVariable Long ownerId) throws OwnerNotFoundException {
        ownerService.deleteOwner(ownerId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<Page<OwnerDTO>> findAllOwners(Pageable pageable) {
        Page<OwnerDTO> ownerDTOPage = ownerService.findAllOwners(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(ownerDTOPage);
    }
}
