package com.chirayu.flowgrid.service;

import com.chirayu.flowgrid.dto.OwnerDTO;

/**
 * @author chirayu
 * @created 2026-05-01
 */

public interface OwnerService {
    OwnerDTO getOwnerById(Long ownerId);
}
