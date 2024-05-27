package com.app.Weather.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Weather.constants.Constants;
import com.app.Weather.service.CSVService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;




/**
 * CSVController handles HTTP requests for loading CSV data.
 * Provides an endpoint to trigger the data loading process.
 */
@RestController
@RequestMapping(Constants.csv.CSV_PATH)

public class CSVController {

    private static final Logger logger = LoggerFactory.getLogger(CSVController.class);

    @Autowired
    private CSVService csvService;

    
    /**
     * Handles the HTTP GET request to load CSV data.
     * 
     * @return ResponseEntity containing the status and message of the operation.
     *         - HTTP 200 status with a success message if the operation is successful.
     *         - HTTP 500 status with an error message if the operation fails.
     */
    @GetMapping(Constants.csv.DIGEST)
    public ResponseEntity<?> loadCSVData() {
        try {
            csvService.loadCSVData();
            return ResponseEntity.ok(Constants.csv.DIGEST_SUCCESS);
        } catch (Exception e) {
            logger.error(Constants.csv.DIGEST_FAILED, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Constants.csv.DIGEST_FAILED + e.getMessage());
        }
    }
}
