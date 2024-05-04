package com.passenger.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.passenger.payment.response.FlightBookingAcknowledgement;
import com.passenger.payment.response.FlightBookingRequest;
import com.passenger.payment.service.FlightBookngService;

@RestController
public class PaymentController {
	

	@Autowired
	private FlightBookngService service;
	
	@PostMapping("/bookFlghtTicket")
	public FlightBookingAcknowledgement bookFlghtTicket(@RequestBody FlightBookingRequest request) {
		return service.bookFlightTicket(request);
		
	}
	  // PUT method to update a flight ticket
    @PutMapping("/updateFlightTicket/{ticketId}")
    public FlightBookingAcknowledgement updateFlightTicket(@PathVariable Long ticketId, @RequestBody FlightBookingRequest request) {
        return service.updateFlightTicket(ticketId, request);
    }

    // GET method to retrieve a flight ticket
    @GetMapping("/getFlightTicket/{ticketId}")
    public FlightBookingAcknowledgement getFlightTicket(@PathVariable Long ticketId) {
        return service.getFlightTicket(ticketId);
    }

    // DELETE method to cancel a flight ticket
    @DeleteMapping("/cancelFlightTicket/{ticketId}")
    public String cancelFlightTicket(@PathVariable Long ticketId) {
        return service.cancelFlightTicket(ticketId);
    }
}
