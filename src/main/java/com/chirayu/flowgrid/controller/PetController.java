package com.chirayu.flowgrid.controller;

import com.chirayu.flowgrid.dto.PetStatisticDTO;
import com.chirayu.flowgrid.service.PetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chirayu
 * @created 2026-05-03
 */

@RestController
@RequestMapping("/api/v1/pets")
@RequiredArgsConstructor
@Slf4j
public class PetController {

    private final PetService petService;

    @GetMapping("/stats")
    public ResponseEntity<PetStatisticDTO> getStatistics() {
        PetStatisticDTO statistics = petService.getStatistics();
        return ResponseEntity.status(HttpStatus.OK).body(statistics);
    }
}
