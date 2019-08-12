package com.taxis.corp.recall.controller;

import com.taxis.corp.recall.services.RecallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Sample controller to recall
 */
@RestController
@RequestMapping("recall")
public class RecallController {

    /**
     * Recall service
     */
    private final RecallService recallService;

    /**
     * Controller constructor with final params
     * The @Autowired annotation is not necessary
     * @param recallService bean of recall service
     */
    @Autowired
    public RecallController(final RecallService recallService) {
        this.recallService = recallService;
    }

    /**
     * Post method to required recall
     * @param rideId Canceled rideId
     * @return The new rideId
     */
    @PostMapping("{rideId}")
    public HttpEntity<String> recall(@PathVariable("rideId") final String rideId) {
        // Call the recall method in service
        String newRideId = recallService.recall(rideId);

        return ResponseEntity.ok(newRideId);
    }
}
