package com.passenger.payment.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.passenger.payment.model.PassengerInfo;
import com.passenger.payment.model.PaymentInfo;
import com.passenger.payment.repo.PassengerInfoRepository;
import com.passenger.payment.repo.PaymentInfoRepostory;
import com.passenger.payment.response.FlightBookingAcknowledgement;
import com.passenger.payment.response.FlightBookingRequest;
import com.passenger.payment.utils.PaymentUtils;
import jakarta.transaction.Transactional;

@Service
public class FlightBookngService {

	@Autowired
	private PassengerInfoRepository passengerInfoRepository;

	@Autowired
	private PaymentInfoRepostory paymentInfoRepository;

	@Transactional
	public FlightBookingAcknowledgement bookFlightTicket(FlightBookingRequest request) {

		PassengerInfo passengerInfo = request.getPassengerInfo();
		passengerInfo = passengerInfoRepository.save(passengerInfo);

		PaymentInfo paymentInfo = request.getPaymentInfo();

		PaymentUtils.validateCreditLimit(paymentInfo.getAccountNo(), passengerInfo.getFare());

		paymentInfo.setPassengerId(passengerInfo.getPId());
		paymentInfo.setAmount(passengerInfo.getFare());
		paymentInfoRepository.save(paymentInfo);
		return new FlightBookingAcknowledgement("SUCCESS", passengerInfo.getFare(),
				UUID.randomUUID().toString().split("-")[0], passengerInfo);

	}

	@Transactional
	public FlightBookingAcknowledgement updateFlightTicket(Long ticketId, FlightBookingRequest request) {
	    Optional<PassengerInfo> optionalPassengerInfo = passengerInfoRepository.findById(ticketId);

	    if (optionalPassengerInfo.isPresent()) {
	        PassengerInfo passengerInfo = optionalPassengerInfo.get();
	        passengerInfo.setArrivalTime(request.getPassengerInfo().getArrivalTime());
	        passengerInfo.setDestination(request.getPassengerInfo().getDestination());
	        passengerInfo.setEmail(request.getPassengerInfo().getEmail());
	        passengerInfo.setFare(request.getPassengerInfo().getFare());
	        passengerInfo.setSource(request.getPassengerInfo().getSource());
	        passengerInfo.setName(request.getPassengerInfo().getName());
	        passengerInfo.setTravelDate(request.getPassengerInfo().getTravelDate());
	        PassengerInfo updatedPassengerInfo = passengerInfoRepository.save(passengerInfo);
	        
	        PaymentInfo paymentInfo = request.getPaymentInfo();

			PaymentUtils.validateCreditLimit(paymentInfo.getAccountNo(), updatedPassengerInfo.getFare());
			
			paymentInfo.setPassengerId(updatedPassengerInfo.getPId());
			paymentInfo.setAmount(updatedPassengerInfo.getFare());
			paymentInfoRepository.save(paymentInfo);

	        // Map the updated PassengerInfo to FlightBookingAcknowledgement
	        return FlightBookingAcknowledgement.mapFromPassengerInfo(updatedPassengerInfo, "Success", request.getPassengerInfo().getFare(), UUID.randomUUID().toString().split("-")[0]);
	    } else {
	        // Throw an exception if PassengerInfo is not found
	        throw new IllegalArgumentException("PassengerInfo with ID " + ticketId + " is not present");
	    }
	}


	
	public FlightBookingAcknowledgement getFlightTicket(Long ticketId) {
		 Optional<PassengerInfo> optionalPassengerInfo = passengerInfoRepository.findById(ticketId);
	        
	        if (optionalPassengerInfo.isPresent()) {
	            PassengerInfo passengerInfo = optionalPassengerInfo.get();
	            return FlightBookingAcknowledgement.mapFromPassengerInfo(passengerInfo, "Success", passengerInfo.getFare(), "PNR" + ticketId);
	        } else {
	            throw new IllegalArgumentException("PassengerInfo with ID " + ticketId + " not found");
	        } 
		
	}

	
	public String cancelFlightTicket(Long ticketId) {
		  Optional<PassengerInfo> optionalPassengerInfo = passengerInfoRepository.findById(ticketId);
	        
	        if (optionalPassengerInfo.isPresent()) {
	            passengerInfoRepository.deleteById(ticketId);
	            return "Flight ticket with ID " + ticketId + " canceled successfully";
	        } else {
	            throw new IllegalArgumentException("PassengerInfo with ID " + ticketId + " not found");
	        }
	}

}
