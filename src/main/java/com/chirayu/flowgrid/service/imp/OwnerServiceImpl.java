package com.chirayu.flowgrid.service.imp;

import com.chirayu.flowgrid.dto.OwnerDTO;
import com.chirayu.flowgrid.exception.OwnerNotFoundException;
import com.chirayu.flowgrid.repository.OwnerRepository;
import com.chirayu.flowgrid.service.OwnerService;
import com.chirayu.flowgrid.util.OwnerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author chirayu
 * @created 2026-05-01
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final OwnerMapper ownerMapper;
    @Value( "${owner.not.found}")
    private String ownerNotFoundMessage;

    @Override
    public OwnerDTO getOwnerById(Long ownerId) {
        log.info("Fetching owner with ID: {}", ownerId);
        return ownerRepository.findById(ownerId)
                .map(ownerMapper::ownerToOwnerDTO)
                .orElseThrow(() -> {
                    log.warn("Owner not found with ID: {}", ownerId);
                    return new OwnerNotFoundException(String.format(ownerNotFoundMessage, ownerId));
                });
    }
}
