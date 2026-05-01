package com.chirayu.flowgrid.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chirayu
 * @created 2026-05-01
 */

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(OwnerNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleOwnerNotFoundException(OwnerNotFoundException e) {
        log.error("OwnerNotFoundException: {}", e.getMessage());
        HashMap<String, String> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("message", e.getMessage());
        objectObjectHashMap.put("status", HttpStatus.NOT_FOUND.toString());
        objectObjectHashMap.put("timestamp", LocalDateTime.now().toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(objectObjectHashMap);
    }
}
