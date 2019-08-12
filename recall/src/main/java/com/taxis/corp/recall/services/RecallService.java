package com.taxis.corp.recall.services;

import com.taxis.corp.recall.domain.RideAddressModel;
import com.taxis.corp.recall.domain.RideRequest;
import com.taxis.corp.recall.domain.RideResponse;
import com.taxis.corp.recall.domain.RideStatus;
import com.taxis.corp.recall.exceptions.RecallNotAvailableException;
import com.taxis.corp.recall.exceptions.RideNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Service to do the recall
 */
@Service
public class RecallService {
    /**
     * API Key to identify and login you API
     */
    private static final String API_KEY = "YOUR_API_KEY";
    /**
     * Company ID to distinct company if your login has more than one company
     */
    private static final String COMPANY_ID = "YOUR_COMPANY_ID";
    /**
     * Ride URL to call
     */
    private static final String URL = "https://api.corp.99taxis.com/v2/rides";
    /**
     * States available to do a recall
     */
    private static final List<String> RECALL_AVAILABLE_STATUS = Arrays.asList("DRIVERS_REJECTED",
            "COULDNT_FIND_AVAILABLE_DRIVERS",
            "CANCELED_BY_DRIVER");

    /**
     * Method validate recall a ride
     *
     * @param rideId Id of the original ride
     * @return The new Id
     */
    public String recall(final String rideId) {
        // Get the ride status
        RideStatus ride = getRideById(rideId);

        // Verify if ride exists
        if (ride == null) {
            throw new RideNotFoundException("Ride not found");
        }
        // Validate ride status
        if (!RECALL_AVAILABLE_STATUS.contains(ride.getStatus())) {
            throw new RecallNotAvailableException("Recall is not available for this ride");
        }
        // Build the request
        RideRequest rideRequest = buildRideRequest(ride);

        // Create the new ride and return the id
        return createRide(rideRequest);
    }

    /**
     * Method to get ride information
     *
     * @param rideId id of the ride
     * @return
     */
    private RideStatus getRideById(final String rideId) {
        // Create restTemplate object
        RestTemplate restTemplate = new RestTemplate();
        // Create headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-api-key", API_KEY);
        // x-company-id is optional
        headers.add("x-company-id", COMPANY_ID);
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        // Call get ride method
        ResponseEntity<RideStatus> ride = restTemplate.exchange(URL + "/{rideId}", HttpMethod.GET,
                httpEntity, RideStatus.class, rideId);

        return ride.getBody();
    }

    /**
     * Method to create a new ride
     *
     * @param rideRequest Request of the new ride
     * @return the new id
     */
    private String createRide(final RideRequest rideRequest) {
        // Create restTemplate object
        RestTemplate restTemplate = new RestTemplate();
        // Create headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-api-key", API_KEY);
        // x-company-id is optional
        headers.add("x-company-id", COMPANY_ID);
        HttpEntity<?> httpEntity = new HttpEntity<>(rideRequest, headers);
        // Call post ride method
        ResponseEntity<RideResponse> ride = restTemplate.exchange(URL, HttpMethod.POST,
                httpEntity, RideResponse.class);

        return ride.getBody().getRideId();
    }

    /**
     * Method to build the request to require new ride
     *
     * @param ride The canceled ride
     * @return Request ride
     */
    private RideRequest buildRideRequest(final RideStatus ride) {
        // Create the request object
        RideRequest rideRequest = new RideRequest();
        // Copy attributes
        rideRequest.setEmployeeID(ride.getEmployeeID());
        rideRequest.setFrom(new RideAddressModel(ride.getFrom().getLatitude(),
                ride.getFrom().getLongitude(), ride.getFrom().getStreet()));
        rideRequest.setTo(new RideAddressModel(ride.getTo().getLatitude(),
                ride.getTo().getLongitude(), ride.getTo().getStreet()));
        rideRequest.setPhoneNumber(ride.getPhoneNumber());
        rideRequest.setCostCenterID(ride.getCostCenterID());
        rideRequest.setCategoryID(ride.getCategoryID());
        rideRequest.setProjectID(ride.getProjectID());
        rideRequest.setOptionals(ride.getOptionals());

        return rideRequest;
    }
}
